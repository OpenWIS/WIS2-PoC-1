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

import openwis.pilot.awisc.server.common.dto.SearchResultsDTO;
import openwis.pilot.awisc.server.common.dto.WmoCodeDTO;
import openwis.pilot.awisc.server.manager.es.AwiscElasticsearchService;
import openwis.pilot.awisc.server.manager.service.SearchService;
import openwis.pilot.awisc.server.manager.util.Util;
import openwis.pilot.awisc.server.manager.util.es.ElasticsearchHit;
import openwis.pilot.awisc.server.manager.util.es.ElasticsearchResponse;
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

	static String WMO_CODE_SIMPLE_SEARCH_QUERY_JSON_PATH = "/wmo-code-simple-search-query.json";
	static String LDSH_SIMPLE_SEARCH_QUERY_JSON_PATH = "/ldsh-simple-search-query.json";
	static String LDSH_SIMPLE_SEARCH_QUERY_WMO_FRAGMENT_JSON_PATH = "/ldsh-simple-search-query-wmo-fragment.json";

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see openwis.pilot.awisc.server.manager.service.SearchService#simpleSearch(
	 * java. lang.String)
	 */
	@Override
	public SearchResultsDTO simpleSearch(String searchString) throws IOException {

		AwiscElasticsearchService esSearchService = JAXRSClientFactory.create("http://localhost:9200",
				AwiscElasticsearchService.class, getProviders(), true);

		String wmoCodeQueryString = Util
				.readResource(WMO_CODE_SIMPLE_SEARCH_QUERY_JSON_PATH, StandardCharsets.UTF_8.name())
				.replace(PLACEHOLDER_QUERY_STRING, searchString);
		logger.info("wmoCodeQueryString: " + wmoCodeQueryString);

		String wmoCodeResponseString = esSearchService.search("wmo-code", wmoCodeQueryString);
		logger.info("wmoCodeResponseString: " + wmoCodeResponseString);

		String wmoCodeFragments = "";

		ElasticsearchResponse<WmoCodeDTO> wmoCodeResponse = ElasticsearchUtil.translateResponse(wmoCodeResponseString,
				WmoCodeDTO.class);
		List<ElasticsearchHit<WmoCodeDTO>> hits = ElasticsearchUtil.getHits(wmoCodeResponse);
		for (ElasticsearchHit<WmoCodeDTO> hit : hits) {
			WmoCodeDTO wmoCode = hit.get_source();
			String wmoCodeFragmentString = Util
					.readResource(LDSH_SIMPLE_SEARCH_QUERY_WMO_FRAGMENT_JSON_PATH, StandardCharsets.UTF_8.name())
					.replace(PLACEHOLDER_WMO_CODE, wmoCode.getCode())
					.replace(PLACEHOLDER_BOOST, String.valueOf(hit.get_score() + 1));
			wmoCodeFragments += wmoCodeFragmentString;
		}

		String ldshQueryString = Util.readResource(LDSH_SIMPLE_SEARCH_QUERY_JSON_PATH, StandardCharsets.UTF_8.name())
				.replace(PLACEHOLDER_QUERY_STRING, searchString)
				.replace(PLACEHOLDER_WMO_CODE_FRAGMENTS, wmoCodeFragments);
		logger.info("ldshQueryString: " + ldshQueryString);
		
		String ldshResponseString = esSearchService.search("ldsh", ldshQueryString);
		logger.info("ldshResponseString: " + ldshResponseString);

		return new SearchResultsDTO();

	}

}
