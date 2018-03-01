package openwis.pilot.awisc.server.manager.util.es;

import com.google.gson.JsonObject;

public class ElasticsearchHit<T> {
	
	private String _id;
	private double _score;
	private T _source;
	private JsonObject inner_hits;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public double get_score() {
		return _score;
	}

	public void set_score(double _score) {
		this._score = _score;
	}

	public T get_source() {
		return _source;
	}

	public void set_source(T _source) {
		this._source = _source;
	}
	
	public JsonObject getInner_hits() {
		return inner_hits;
	}

	public void setInner_hits(JsonObject inner_hits) {
		this.inner_hits = inner_hits;
	}
	
	@Override
	public String toString() {
		return "ElasticsearchHit [_id=" + _id + ", _score=" + _score + ", _source=" + _source + ", inner_hits="
				+ inner_hits + "]";
	}

}
