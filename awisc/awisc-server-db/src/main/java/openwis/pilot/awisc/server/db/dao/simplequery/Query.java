package openwis.pilot.awisc.server.db.dao.simplequery;

public class Query {
	
	public static <T> QueryFragment eq(String key, T value) {
		return new Equals<T>(key, value);
		
	}

	public static QueryFragment and(QueryFragment ... fragments ) {
		And and = new And();
		for(QueryFragment qf: fragments) {
			and.getFragments().add(qf);
		}
		
		return and;
	}
	
	public static QueryFragment or(QueryFragment ... fragments ) {
		Or or = new Or();
		for(QueryFragment qf: fragments) {
			or.getFragments().add(qf);
		}
		
		return or;
	}
}
