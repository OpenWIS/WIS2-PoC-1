package openwis.pilot.awisc.server.common.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsDTO {
	
	private List<SearchResultDTO> searchResults = new ArrayList<SearchResultDTO>();

	public List<SearchResultDTO> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<SearchResultDTO> searchResults) {
		this.searchResults = searchResults;
	}

}
