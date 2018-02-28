package openwis.pilot.awisc.server.common.dto;

import java.util.Date;
import java.util.List;

public class LdshFullDTO {

	private Long id;
	private String name;
	private String systemId;
	private String token;
	private String contactEmail;
	private String copyright;
	private Date registrationDate;
	private String indexServiceBaseUrl;
	private List<DatasetDTO> datasets;

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

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getIndexServiceBaseUrl() {
		return indexServiceBaseUrl;
	}

	public void setIndexServiceBaseUrl(String indexServiceBaseUrl) {
		this.indexServiceBaseUrl = indexServiceBaseUrl;
	}

	public List<DatasetDTO> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DatasetDTO> datasets) {
		this.datasets = datasets;
	}

}
