package openwis.pilot.awisc.server.manager.util.es;

public class ElasticsearchHit<T> {
	
	private String _id;
	private double _score;
	private T _source;

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

	@Override
	public String toString() {
		return "ElasticsearchHit [_id=" + _id + ", _score=" + _score + ", _source=" + _source + "]";
	}

}
