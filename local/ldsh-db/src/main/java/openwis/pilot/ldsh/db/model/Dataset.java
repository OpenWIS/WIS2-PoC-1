package openwis.pilot.ldsh.db.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Dataset")
public class Dataset implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ids_gen")
	 @SequenceGenerator(name = "ids_gen", sequenceName = "ids_sequence", allocationSize = 20)
	-- >> org.hibernate.dialect.MySQL5Dialect does not support sequences
	 */
	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "periodfrom")
	private Date periodFrom;

	@Column(name = "periodto")
	private Date periodTo;

	@Column(name = "license")
	private String license;

	@Column(name = "imageurl")
	private String imageUrl;

	@Column(name = "measurementunit")
	private String measurementUnit; // todo Enum


	@Column(name = "wmocodes")
	@OneToMany(targetEntity=WmoCode.class, mappedBy="code", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<WmoCode> wmoCodes;

	// Location Information

	@Column(name = "country")
	private String country;

	@Column(name = "state")
	private String state;

	@Column(name = "city")
	private String city;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "elevation")
	private String elevation;

	// Dissemination Information
	@Column(name = "relativeurl")
	private String relativeUrl;

	@Column(name = "filenameprefix")
	private String filenameprefix;

	@Column(name = "downloadUrl")
	private String downloadUrl;

	@Column(name = "subscriptionuri")
	private String subscriptionUri;

	@Column(name = "dataformat")
	private String dataformat; // Todo Enum

	@Column(name = "rdshdissenabled")
	private boolean rdshDissEnabled; // null true false? Notification Only
										// Notification + Data

	// Search Engine Metadata
	@Column(name = "jsonld")
	private String jsonLd;

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

	public List<WmoCode> getWmoCodes() {
		return wmoCodes;
	}

	public void setWmoCodes(List<WmoCode> wmoCodes) {
		this.wmoCodes = wmoCodes;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
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

	public String getDataformat() {
		return dataformat;
	}

	public void setDataformat(String dataformat) {
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

}
