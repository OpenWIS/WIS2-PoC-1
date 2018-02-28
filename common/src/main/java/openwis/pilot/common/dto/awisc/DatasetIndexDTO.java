package openwis.pilot.common.dto.awisc;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Dataset indexing information for AWISC
 *
 */
@XmlRootElement()
public class DatasetIndexDTO {
	
	public static class DissemitationType{
		public static String NOTIFICATION_ONLY = "NOTIFICATION_ONLY";
		public static String NOTIFICATION_AND_DATA = "NOTIFICATION_AND_DATA";
	}
	
	/**
	 * The dataset prefix
	 */
	private String prefix;

	/**
	 * The dataset name
	 */
	private String name;
	
	/**
	 * The dataset  description
	 */
	private String description;
	
	/**
	 * The starting date for the dataset contents
	 */
	private String periodFrom;
	
	/**
	 * The ending date for the dataset contents 
	 */
	private String periodTo;
	
	/**
	 * The last dataset update
	 */
	private String lastUpdate;
	
	/**
	 * The dataset license 
	 */
	private String license;
	
	/**
	 * The dataset update frequency
	 */
	private String updateFrequency;
	
	/**
	 * The dataset data format
	 */
	private String dataFormat;

	/**
	 * The dataset country
	 */
	private String country;
	
	/**
	 * The dataset region 
	 */
	private String region;
	
	/**
	 * The dataset city 
	 */
	private String city;
	
	/**
	 * The dataset latittude 
	 */
	private String latittude;
	
	/**
	 * The dataset longitude 
	 */
	private String longitude;
	
	/**
	 * The dataset elevation 
	 */
	private String elevation;

	/**
	 * The dataset download URL
	 */
	private String downloadUrl;
	
	/**
	 * The dataset subscription URI
	 */
	
	private String subscriptionUri;
	/**
	 *	The dataset subscription URI 
	 */
	private String dissemitationType;

	/**
	 * The dataset WMO codes 
	 */
	private List<WmoCodeDTO> wmoCodes = new ArrayList<WmoCodeDTO>();

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

	public String getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}

	public String getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getUpdateFrequency() {
		return updateFrequency;
	}

	public void setUpdateFrequency(String updateFrequency) {
		this.updateFrequency = updateFrequency;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLatittude() {
		return latittude;
	}

	public void setLatittude(String latittude) {
		this.latittude = latittude;
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

	public String getDissemitationType() {
		return dissemitationType;
	}

	public void setDissemitationType(String dissemitationType) {
		this.dissemitationType = dissemitationType;
	}

	public List<WmoCodeDTO> getWmoCodes() {
		return wmoCodes;
	}

	public void setWmoCodes(List<WmoCodeDTO> wmoCodes) {
		this.wmoCodes = wmoCodes;
	}

	public String getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
