package openwis.pilot.ldsh.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DatasetDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2038915410328204765L;

	@XmlElement
	private Long id;

	@XmlElement
	private String name;

	@XmlElement
	private String description;

	@XmlElement
	private Date periodFrom;

	@XmlElement
	private Date periodTo;

	@XmlElement
	private String license;

	@XmlElement
	private String imageUrl;

	@XmlElement
	private String measurementUnit;

	@XmlElement
	private Set<WmoCodeDTO> wmoCodes;

	@XmlElement
	private CountryDTO country;

	@XmlElement
	private String state;

	@XmlElement
	private String city;

	@XmlElement
	private String latitude;

	@XmlElement
	private String longitude;

	@XmlElement
	private String elevation;

	@XmlElement
	private String relativeUrl;

	@XmlElement
	private String filenameprefix;

	@XmlElement
	private String downloadUrl;

	@XmlElement
	private String subscriptionUri;

	@XmlElement
	private DataFormatDTO dataformat; 

	@XmlElement
	private boolean rdshDissEnabled; 
	
	@XmlElement
	private String jsonLd;

	@XmlElement
	private boolean sendData;
	
	@XmlElement
	private Date lastUpdate;
	

	public Long getId() {
		return id;
	}

	public DatasetDTO() {
	}

	public DatasetDTO(long id, String name, String description,
			Date periodFrom, Date periodTo, String license, String imageUrl,
			String measurementUnit, Set<WmoCodeDTO> wmoCodes, CountryDTO country,
			String state, String city, String latitude, String longitude,
			String elevation, String relativeUrl, String filenameprefix,
			String downloadUrl, String subscriptionUri, DataFormatDTO dataformat,
			boolean rdshDissEnabled, String jsonLd, boolean sendData,  Date lastUpdate ) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.license = license;
		this.imageUrl = imageUrl;
		this.measurementUnit = measurementUnit;
		this.wmoCodes = wmoCodes;
		this.country = country;
		this.state = state;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		this.relativeUrl = relativeUrl;
		this.filenameprefix = filenameprefix;
		this.downloadUrl = downloadUrl;
		this.subscriptionUri = subscriptionUri;
		this.dataformat = dataformat;
		this.rdshDissEnabled = rdshDissEnabled;
		this.jsonLd = jsonLd;
		this.sendData = sendData;
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "DatasetDTO [id=" + id + ", name=" + name + ", description="
				+ description + ", periodFrom=" + periodFrom + ", periodTo="
				+ periodTo + ", license=" + license + ", imageUrl=" + imageUrl
				+ ", measurementUnit=" + measurementUnit + ", wmoCodes="
				+ wmoCodes + ", country=" + country + ", state=" + state
				+ ", city=" + city + ", latitude=" + latitude + ", longitude="
				+ longitude + ", elevation=" + elevation + ", relativeUrl="
				+ relativeUrl + ", filenameprefix=" + filenameprefix
				+ ", downloadUrl=" + downloadUrl + ", subscriptionUri="
				+ subscriptionUri + ", dataformat=" + dataformat
				+ ", rdshDissEnabled=" + rdshDissEnabled + ", jsonLd=" + jsonLd 
				+", sendData="+sendData	+ "]";
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(Date periodFrom) {
		this.periodFrom = periodFrom;
	}

	public Date getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(Date periodTo) {
		this.periodTo = periodTo;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public Set<WmoCodeDTO> getWmoCodes() {
		return wmoCodes;
	}

	public void setWmoCodes(Set<WmoCodeDTO> wmoCodes) {
		this.wmoCodes = wmoCodes;
	}

	public CountryDTO getCountry() {
		return country;
	}

	public void setCountry(CountryDTO country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getRelativeUrl() {
		return relativeUrl;
	}

	public void setRelativeUrl(String relativeUrl) {
		this.relativeUrl = relativeUrl;
	}

	public String getFilenameprefix() {
		return filenameprefix;
	}

	public void setFilenameprefix(String filenameprefix) {
		this.filenameprefix = filenameprefix;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getSubscriptionUri() {
		return subscriptionUri;
	}

	public void setSubscriptionUri(String subscriptionUri) {
		this.subscriptionUri = subscriptionUri;
	}

	public DataFormatDTO getDataformat() {
		return dataformat;
	}

	public void setDataformat(DataFormatDTO dataformat) {
		this.dataformat = dataformat;
	}

	public boolean isRdshDissEnabled() {
		return rdshDissEnabled;
	}

	public void setRdshDissEnabled(boolean rdshDissEnabled) {
		this.rdshDissEnabled = rdshDissEnabled;
	}

	public String getJsonLd() {
		return jsonLd;
	}

	public void setJsonLd(String jsonLd) {
		this.jsonLd = jsonLd;
	}
	
	public boolean isSendData() {
		return sendData;
	}

	public void setSendData(boolean sendData) {
		this.sendData = sendData;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
