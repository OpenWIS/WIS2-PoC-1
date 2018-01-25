package openwis.pilot.awisc.server.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DatasetDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String description;

	private String url;

	private String latitude;

	private String longitude;

	private String elevation;

	private UpdateFrequencyDTO updateFrequency;

	private DataFormatDTO dataFormat;

	private Date lastUpdate;

	private LdshDTO ldsh;

	private List<WmoCodeDTO> wmoCodes;

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

	public LdshDTO getLdsh() {
		return ldsh;
	}

	public void setLdsh(LdshDTO ldsh) {
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

	public UpdateFrequencyDTO getUpdateFrequency() {
		return updateFrequency;
	}

	public void setUpdateFrequency(UpdateFrequencyDTO updateFrequency) {
		this.updateFrequency = updateFrequency;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public DataFormatDTO getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(DataFormatDTO dataFormat) {
		this.dataFormat = dataFormat;
	}

	public List<WmoCodeDTO> getWmoCodes() {
		return wmoCodes;
	}

	public void setWmoCodes(List<WmoCodeDTO> wmoCodes) {
		this.wmoCodes = wmoCodes;
	}

}
