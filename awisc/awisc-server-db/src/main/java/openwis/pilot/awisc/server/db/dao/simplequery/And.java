package openwis.pilot.awisc.server.db.dao.simplequery;

import java.util.ArrayList;
import java.util.List;

public class And implements QueryFragment{
	
	private List<QueryFragment> fragments = new ArrayList<QueryFragment>();
	
	public QueryFragmentType getType() {
		return QueryFragmentType.AND;
	}

	public List<QueryFragment> getFragments() {
		return fragments;
	}

	public void setFragments(List<QueryFragment> fragments) {
		this.fragments = fragments;
	}

}
