package openwis.pilot.ldsh.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "WmoCode")
public class WmoCode implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsw_gen")
	@SequenceGenerator(name = "idsw_gen", sequenceName = "ids_sequence", allocationSize = 20)
	-- >> org.hibernate.dialect.MySQL5Dialect does not support sequences
	*/
	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "continent")
	private String continent;

	@Column(name = "uri")
	private String uri;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
