package openwis.pilot.awisc.server.db.dao.simplequery;

public class Equals<T> implements QueryFragment{
	
	private String key;
	private T value;
	
	public Equals() {
	}
	
	public Equals(String key, T value) {
		this.key = key;
		this.value = value;
	}
	
	public QueryFragmentType getType() {
		return QueryFragmentType.EQUALS;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
