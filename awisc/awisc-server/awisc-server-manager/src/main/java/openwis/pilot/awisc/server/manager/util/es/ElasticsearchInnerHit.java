package openwis.pilot.awisc.server.manager.util.es;

public class ElasticsearchInnerHit<N> {
	
	private double _score;
	private N _source;
	private String idProperty;
	
	public double get_score() {
		return _score;
	}

	public void set_score(double _score) {
		this._score = _score;
	}

	public N get_source() {
		return _source;
	}

	public void set_source(N _source) {
		this._source = _source;
	}
		
	@Override
	public String toString() {
		return "ElasticsearchHit [_score=" + _score + ", _source=" + _source + "]";
	}

	public String getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(String idProperty) {
		this.idProperty = idProperty;
	}

}
