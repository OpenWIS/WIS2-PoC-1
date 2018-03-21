package openwis.pilot.awisc.server.manager.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "awisc_ldsh")
public class Ldsh implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "system_id")
	private String systemId;

	@Column(name = "token")
	private String token;

	@Column(name = "contact_email")
	private String contactEmail;

	@Column(name = "copyright")
	private String copyright;
	
	@Column(name = "registration_date")
	private Date registrationDate;
	
	@Column(name = "index_service_base_url")
	private String indexServiceBaseUrl;
	
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

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
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

}
