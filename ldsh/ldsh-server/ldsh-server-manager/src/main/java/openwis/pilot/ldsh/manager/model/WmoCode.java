package openwis.pilot.ldsh.manager.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ldsh_wmo_code")
public class WmoCode implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	 *                          "idsw_gen")
	 * @SequenceGenerator(name = "idsw_gen", sequenceName = "ids_sequence",
	 *                         allocationSize = 20) -- >>
	 *                         org.hibernate.dialect.MySQL5Dialect does not
	 *                         support sequences
	 */
	@Id
	@Column(name = "wmo_code_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long wmocode_id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "continent")
	private String continent;


	@ManyToMany (mappedBy = "wmoCodes")
//	@JoinTable(name = "ldsh_dataset_wmo_code", joinColumns = { @JoinColumn(name = "wmocode_id", referencedColumnName = "wmo_code_id") },inverseJoinColumns = { @JoinColumn(name = "id", referencedColumnName = "id") })
	private Set<Dataset> datasets = new HashSet<>();

	@Column(name = "uri")
	private String uri;

	public long getWmocode_id() {
		return wmocode_id;
	}

	public void setWmocode_id(long wmocode_id) {
		this.wmocode_id = wmocode_id;
	}

	public Set<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(Set<Dataset> datasets) {
		this.datasets = datasets;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
