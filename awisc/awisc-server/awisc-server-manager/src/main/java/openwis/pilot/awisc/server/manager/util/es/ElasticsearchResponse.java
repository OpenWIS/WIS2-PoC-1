package openwis.pilot.awisc.server.manager.util.es;

public class ElasticsearchResponse<T> {

	private ElasticsearchHits<T> hits;

	public ElasticsearchHits<T> getHits() {
		return hits;
	}

	public void setHits(ElasticsearchHits<T> hits) {
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "ElasticsearchResponse [hits=" + hits + "]";
	}

}
