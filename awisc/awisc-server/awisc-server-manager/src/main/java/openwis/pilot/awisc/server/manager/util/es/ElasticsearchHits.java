package openwis.pilot.awisc.server.manager.util.es;

import java.util.List;

public class ElasticsearchHits<T> {

	private List<ElasticsearchHit<T>> hits;

	public List<ElasticsearchHit<T>> getHits() {
		return hits;
	}

	public void setHits(List<ElasticsearchHit<T>> hits) {
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "ElasticsearchHits [hits=" + hits + "]";
	}

}
