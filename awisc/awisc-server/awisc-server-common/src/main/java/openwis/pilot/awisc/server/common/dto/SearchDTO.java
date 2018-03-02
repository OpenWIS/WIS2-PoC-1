package openwis.pilot.awisc.server.common.dto;

import java.util.List;

public class SearchDTO {
	
	private String searchText;
	private List<String> wmoCodes;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List<String> getWmoCodes() {
		return wmoCodes;
	}

	public void setWmoCodes(List<String> wmoCodes) {
		this.wmoCodes = wmoCodes;
	}

}
