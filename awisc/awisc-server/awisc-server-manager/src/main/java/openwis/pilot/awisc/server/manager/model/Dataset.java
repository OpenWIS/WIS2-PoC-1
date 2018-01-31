package openwis.pilot.awisc.server.manager.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "awisc_dataset")
public class Dataset implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "url")
	private String url;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "elevation")
	private String elevation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "update_frequency_id", foreignKey = @ForeignKey(name="FK_awisc_dataset_update_frequency"))
	private UpdateFrequency updateFrequency;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "data_format_id", foreignKey = @ForeignKey(name="FK_awisc_dataset_data_format"))
	private DataFormat dataFormat;

	@Column(name = "last_update")
	private Date lastUpdate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ldsh_id", foreignKey = @ForeignKey(name="FK_awisc_dataset_ldsh"))
	private Ldsh ldsh;

	@ManyToMany
	@JoinTable(name = "dataset_codes", 
	joinColumns = @JoinColumn(name = "dataset_id", 
	referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "wmo_code_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="FK_awisc_wmo_code_dataset_codes")),
	foreignKey = @ForeignKey(name="FK_awisc_dataset_dataset_codes"))
	private List<WmoCode> wmoCodes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ldsh getLdsh() {
		return ldsh;
	}

	public void setLdsh(Ldsh ldsh) {
		this.ldsh = ldsh;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getElevation() {
		return elevation;
	}

	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

	public UpdateFrequency getUpdateFrequency() {
		return updateFrequency;
	}

	public void setUpdateFrequency(UpdateFrequency updateFrequency) {
		this.updateFrequency = updateFrequency;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public DataFormat getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(DataFormat dataFormat) {
		this.dataFormat = dataFormat;
	}

	public List<WmoCode> getWmoCodes() {
		return wmoCodes;
	}

	public void setWmoCodes(List<WmoCode> wmoCodes) {
		this.wmoCodes = wmoCodes;
	}

}
