package openwis.pilot.awisc.server.manager.util.es;

public class ElasticseaerchResultEntry<T,N> {
	
	private T mainObject;
	private N nestedObject;
	private String id;
	private double score;

	public T getMainObject() {
		return mainObject;
	}

	public void setMainObject(T mainObject) {
		this.mainObject = mainObject;
	}

	public N getNestedObject() {
		return nestedObject;
	}

	public void setNestedObject(N nestedObject) {
		this.nestedObject = nestedObject;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
