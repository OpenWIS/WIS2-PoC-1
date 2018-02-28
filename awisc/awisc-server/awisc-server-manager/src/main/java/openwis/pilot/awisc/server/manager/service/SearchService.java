package openwis.pilot.awisc.server.manager.service;

import java.io.IOException;

import openwis.pilot.awisc.server.common.dto.SearchResultsDTO;

/**
 * Provides search functionality
 * @author george
 *
 */
public interface SearchService {
	
	/**
	 * The simple search
	 * @param searchString
	 */
	SearchResultsDTO simpleSearch(String searchString) throws IOException;

}
