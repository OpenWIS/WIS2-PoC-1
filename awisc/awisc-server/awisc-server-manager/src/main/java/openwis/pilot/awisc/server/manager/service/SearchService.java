package openwis.pilot.awisc.server.manager.service;

import openwis.pilot.awisc.server.common.dto.SearchResultsDTO;

/**
 * Provides search functionality
 * @author george
 *
 */
public interface SearchService {
	
	
	/**
	 * The simple search implementation
	 * @param searchString the search string entered by the end-user
	 * @return the search results
	 * @throws Exception declared to be thrown, to be caught and handled by interceptor later on 
	 */
	SearchResultsDTO simpleSearch(String searchString) throws Exception;

}
