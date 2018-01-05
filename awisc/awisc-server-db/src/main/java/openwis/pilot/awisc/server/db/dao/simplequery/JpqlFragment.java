package openwis.pilot.awisc.server.db.dao.simplequery;

import java.util.LinkedHashMap;
import java.util.Map;

public class JpqlFragment {

	Map<String, Object> parameters = new LinkedHashMap<String, Object>();
	String jpql;

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public String getJpql() {
		return jpql;
	}

	public void setJpql(String jpql) {
		this.jpql = jpql;
	}

}
