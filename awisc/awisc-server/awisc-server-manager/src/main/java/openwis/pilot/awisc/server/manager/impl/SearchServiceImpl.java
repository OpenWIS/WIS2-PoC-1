package openwis.pilot.awisc.server.manager.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import openwis.pilot.awisc.server.common.dto.DatasetDTO;
import openwis.pilot.awisc.server.common.dto.LdshDTO;
import openwis.pilot.awisc.server.common.dto.LdshFullDTO;
import openwis.pilot.awisc.server.common.dto.SearchDTO;
import openwis.pilot.awisc.server.common.dto.SearchResultDTO;
import openwis.pilot.awisc.server.common.dto.SearchResultsDTO;
import openwis.pilot.awisc.server.common.dto.WmoCodeDTO;
import openwis.pilot.awisc.server.manager.es.AwiscElasticsearchService;
import openwis.pilot.awisc.server.manager.service.SearchService;
import openwis.pilot.awisc.server.manager.util.Util;
import openwis.pilot.awisc.server.manager.util.es.ElasticseaerchResultEntry;
import openwis.pilot.awisc.server.manager.util.es.ElasticsearchHit;
import openwis.pilot.awisc.server.manager.util.es.ElasticsearchInnerHit;
import openwis.pilot.awisc.server.manager.util.es.ElasticsearchResponse;
import openwis.pilot.awisc.server.manager.util.es.ElasticsearchResultsSorter;
import openwis.pilot.awisc.server.manager.util.es.ElasticsearchUtil;

@Singleton
@Transactional
@OsgiServiceProvider(classes = { SearchService.class })
public class SearchServiceImpl implements SearchService {

	private static final Logger logger = Logger.getLogger(SearchServiceImpl.class.getName());

	private static String PLACEHOLDER_QUERY_STRING = "@QUERY_STRING@";
	private static String PLACEHOLDER_WMO_CODE_FRAGMENTS = "@WMO_CODE_FRAGMENTS@";
	private static String PLACEHOLDER_WMO_CODE = "@WMO_CODE@";
	private static String PLACEHOLDER_BOOST = "@BOOST@";

	private static String QUERY_SIMPLE_SEARCH_WMO_CODE_JSON_PATH = "/query.simple-search.wmo-code.json";
	private static String QUERY_SIMPLE_SEARCH_LDSH_DATASETS_JSON_PATH = "/query.simple-search.ldsh.datasets.json";
	private static String QUERY_SIMPLE_SEARCH_LDSH_ALL_DATASETS_JSON_PATH = "/query.simple-search.ldsh.all-datasets.json";
	private static String QUERY_SIMPLE_SEARCH_LDSH_WMO_FRAGMENT_JSON_PATH = "/query.simple-search.ldsh.wmo-fragment.json";

	/**
	 * @return the JSON providers for a CXF client call
	 */
	private List<JSONProvider<?>> getProviders() {
		List<JSONProvider<?>> providers = new ArrayList<JSONProvider<?>>();
		JSONProvider<?> jsonProvider = new JSONProvider<>();
		jsonProvider.setDropRootElement(true);
		jsonProvider.setDropCollectionWrapperElement(true);
		jsonProvider.setSerializeAsArray(true);
		jsonProvider.setSupportUnwrapped(true);
		providers.add(jsonProvider);

		return providers;
	}

	/**
	 * Executes a search against the WMO Codes and returns the results in a string
	 * that will be part of the query on LDSHs/Datasets
	 * 
	 * @param elasticearchService
	 * @param searchString
	 * @return
	 * @throws IOException
	 */
	private String getWmoCodeFragmentQueryString(AwiscElasticsearchService elasticearchService, String searchString)
			throws IOException {
		String wmoCodeQueryString = Util
				.readResource(QUERY_SIMPLE_SEARCH_WMO_CODE_JSON_PATH, StandardCharsets.UTF_8.name())
				.replace(PLACEHOLDER_QUERY_STRING, searchString);
		logger.info("wmoCodeQueryString: " + wmoCodeQueryString);

		String wmoCodeResponseString = elasticearchService.search("wmo-code", wmoCodeQueryString);
		logger.info("wmoCodeResponseString: " + wmoCodeResponseString);

		String wmoCodeFragmentQueryString = "";

		ElasticsearchResponse<WmoCodeDTO> wmoCodeResponse = ElasticsearchUtil.translateResponse(wmoCodeResponseString,
				WmoCodeDTO.class);
		List<ElasticsearchHit<WmoCodeDTO>> hits = ElasticsearchUtil.getHits(wmoCodeResponse);
		for (ElasticsearchHit<WmoCodeDTO> hit : hits) {
			WmoCodeDTO wmoCode = hit.get_source();
			String wmoCodeFragmentString = Util
					.readResource(QUERY_SIMPLE_SEARCH_LDSH_WMO_FRAGMENT_JSON_PATH, StandardCharsets.UTF_8.name())
					.replace(PLACEHOLDER_WMO_CODE, wmoCode.getCode())
					.replace(PLACEHOLDER_BOOST, String.valueOf(hit.get_score() + 1));
			wmoCodeFragmentQueryString += wmoCodeFragmentString;
		}

		return wmoCodeFragmentQueryString;
	}
	
	/**
	 * Takes as input the wmo codes selected in the advanced search, and returns a string
	 * that will be part of the query on LDSHs/Datasets
	 * @param wmoCodes
	 * @return
	 * @throws IOException
	 */
	private String getWmoCodeFragmentQueryString(List<String> wmoCodes)
			throws IOException {
		String wmoCodeFragmentQueryString = "";
		for (String wmoCode : wmoCodes) {
			String wmoCodeFragmentString = Util
					.readResource(QUERY_SIMPLE_SEARCH_LDSH_WMO_FRAGMENT_JSON_PATH, StandardCharsets.UTF_8.name())
					.replace(PLACEHOLDER_WMO_CODE, wmoCode)
					.replace(PLACEHOLDER_BOOST, "100");
			wmoCodeFragmentQueryString += wmoCodeFragmentString;
		}

		return wmoCodeFragmentQueryString;
	}

	/**
	 * Gets the LDSH/Dataset search hits
	 * 
	 * @param elasticearchService
	 * @param searchString
	 * @param wmoCodeFragmentQueryString
	 * @return
	 * @throws IOException
	 */
	private List<ElasticsearchHit<LdshDTO>> getLdshHits(AwiscElasticsearchService elasticearchService,
			String searchString, String wmoCodeFragmentQueryString) throws IOException {
		String ldshQueryString = Util
				.readResource(QUERY_SIMPLE_SEARCH_LDSH_DATASETS_JSON_PATH, StandardCharsets.UTF_8.name())
				.replace(PLACEHOLDER_QUERY_STRING, searchString)
				.replace(PLACEHOLDER_WMO_CODE_FRAGMENTS, wmoCodeFragmentQueryString);
		logger.info("ldshQueryString: " + ldshQueryString);

		String ldshResponseString = elasticearchService.search("ldsh", ldshQueryString);
		logger.info("ldshResponseString: " + ldshResponseString);

		ElasticsearchResponse<LdshDTO> ldshResponse = ElasticsearchUtil.translateResponse(ldshResponseString,
				LdshDTO.class);
		List<ElasticsearchHit<LdshDTO>> ldshHits = ElasticsearchUtil.getHits(ldshResponse);
		return ldshHits;
	}

	/**
	 * Returns the ordered (descending score) results of the search. Note: Since
	 * elasticsearch only finds matches, this method retrieves all matched LDSHs,
	 * and fetches ALL their datasets. The ones missing will be appended at the
	 * bottom of the results that actually matched in the previous search, but a
	 * very small score
	 * 
	 * @param elasticearchService
	 * @param ldshHits
	 * @return
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	private List<ElasticseaerchResultEntry<LdshDTO, DatasetDTO>> getOrderedResults(
			AwiscElasticsearchService elasticearchService, List<ElasticsearchHit<LdshDTO>> ldshHits,
			String searchString, String wmoCodeFragmentQueryString) throws IOException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {

		ElasticsearchResultsSorter<LdshDTO, DatasetDTO> sorter = new ElasticsearchResultsSorter<LdshDTO, DatasetDTO>();

		for (ElasticsearchHit<LdshDTO> hit : ldshHits) {
			List<ElasticsearchInnerHit<DatasetDTO>> innerHits = ElasticsearchUtil.getInnerHits(hit, DatasetDTO.class,
					"datasets", "prefix");
			sorter.addHits(hit, innerHits);
		}

		String getDatasetsQueryString = Util
				.readResource(QUERY_SIMPLE_SEARCH_LDSH_ALL_DATASETS_JSON_PATH, StandardCharsets.UTF_8.name())
				.replace(PLACEHOLDER_QUERY_STRING, searchString)
				.replace(PLACEHOLDER_WMO_CODE_FRAGMENTS, wmoCodeFragmentQueryString);
		logger.info("getDatasetsQueryString: " + getDatasetsQueryString);

		String getDatasetsResponseString = elasticearchService.search("ldsh", getDatasetsQueryString);
		logger.info("getDatasetsResponseString: " + getDatasetsResponseString);

		ElasticsearchResponse<LdshFullDTO> getDatasetsResponse = ElasticsearchUtil
				.translateResponse(getDatasetsResponseString, LdshFullDTO.class);
		List<ElasticsearchHit<LdshFullDTO>> ldshsList = ElasticsearchUtil.getHits(getDatasetsResponse);
		for (ElasticsearchHit<LdshFullDTO> ldshHit : ldshsList) {
			sorter.addMissingNestedHits(ldshHit.get_source().getSystemId(), ldshHit.get_source().getDatasets(), "prefix");
		}

		return sorter.getOrderedHits();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see openwis.pilot.awisc.server.manager.service.SearchService#simpleSearch(
	 * java. lang.String)
	 */
	@Override
	public SearchResultsDTO simpleSearch(String searchString) throws Exception {

		AwiscElasticsearchService elasticearchService = JAXRSClientFactory.create("http://localhost:9200",
				AwiscElasticsearchService.class, getProviders(), true);

		String wmoCodeFragmentQueryString = getWmoCodeFragmentQueryString(elasticearchService, searchString);
		List<ElasticsearchHit<LdshDTO>> ldshHits = getLdshHits(elasticearchService, searchString,
				wmoCodeFragmentQueryString);

		List<ElasticseaerchResultEntry<LdshDTO, DatasetDTO>> orderedResults = getOrderedResults(elasticearchService,
				ldshHits, searchString, wmoCodeFragmentQueryString);

		SearchResultsDTO results = new SearchResultsDTO();
		for (ElasticseaerchResultEntry<LdshDTO, DatasetDTO> entry : orderedResults) {
			SearchResultDTO result = new SearchResultDTO();
			result.setLdsh(entry.getMainObject());
			result.setDataset(entry.getNestedObject());
			results.getSearchResults().add(result);
		}

		return results;

	}

	/* (non-Javadoc)
	 * @see openwis.pilot.awisc.server.manager.service.SearchService#advancedSearch(java.lang.String)
	 */
	@Override
	public SearchResultsDTO advancedSearch(SearchDTO searchDTO) throws Exception {
		AwiscElasticsearchService elasticearchService = JAXRSClientFactory.create("http://localhost:9200",
				AwiscElasticsearchService.class, getProviders(), true);
		
		String wmoCodeFragmentQueryString = getWmoCodeFragmentQueryString(searchDTO.getWmoCodes());
		List<ElasticsearchHit<LdshDTO>> ldshHits = getLdshHits(elasticearchService, searchDTO.getSearchText(),
				wmoCodeFragmentQueryString);
		
		List<ElasticseaerchResultEntry<LdshDTO, DatasetDTO>> orderedResults = getOrderedResults(elasticearchService,
				ldshHits, searchDTO.getSearchText(), wmoCodeFragmentQueryString);

		SearchResultsDTO results = new SearchResultsDTO();
		for (ElasticseaerchResultEntry<LdshDTO, DatasetDTO> entry : orderedResults) {
			SearchResultDTO result = new SearchResultDTO();
			result.setLdsh(entry.getMainObject());
			result.setDataset(entry.getNestedObject());
			results.getSearchResults().add(result);
		}

		return results;
	}

}
