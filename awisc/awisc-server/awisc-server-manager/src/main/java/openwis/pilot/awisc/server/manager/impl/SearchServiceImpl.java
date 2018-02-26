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
import openwis.pilot.awisc.server.manager.es.AwiscElasticsearchService;
import openwis.pilot.awisc.server.manager.service.SearchService;
import openwis.pilot.awisc.server.manager.util.Util;

@Singleton
@Transactional
@OsgiServiceProvider(classes = { SearchService.class })
public class SearchServiceImpl implements SearchService{
	
	private static final Logger logger = Logger.getLogger(SearchServiceImpl.class.getName());
	
	private static String PLACEHOLDER_WMO_CODE = "@WMO_CODE@";
	static String LDSH_SIMPLE_SEARCH_QUERY_JSON_PATH = "/ldsh-simple-search-query.json";

	/* (non-Javadoc)
	 * @see openwis.pilot.awisc.server.manager.service.SearchService#simpleSearch(java.lang.String)
	 */
	@Override
	public SearchResultsDTO simpleSearch(String searchString) throws IOException {
		
		String queryString = Util.readResource(LDSH_SIMPLE_SEARCH_QUERY_JSON_PATH, StandardCharsets.UTF_8.name()).replace(PLACEHOLDER_WMO_CODE, searchString);
		logger.info("queryString: " + queryString);
		
		List<JSONProvider<?>> providers = new ArrayList<JSONProvider<?>>();
		JSONProvider<?> jsonProvider = new JSONProvider<>();
		jsonProvider.setDropRootElement(true);
		jsonProvider.setDropCollectionWrapperElement(true);
		jsonProvider.setSerializeAsArray(true);
		jsonProvider.setSupportUnwrapped(true);
		providers.add(jsonProvider);

		AwiscElasticsearchService esSearchService = JAXRSClientFactory.create("http://localhost:9200",
				AwiscElasticsearchService.class, providers, true);

		String response = esSearchService.search("ldsh", queryString);
		logger.info("response: " + response);
		
		
		return new SearchResultsDTO();
		
	}

}
