package openwis.pilot.ldsh.manager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ldsh_remote_system")
public class RemoteSystem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5379470486327016244L;



	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "token")
	private String token;

	@Column(name = "url")
	private String url;

	// Is Registered/true false?
	@Column(name = "status")
	private boolean status;

	@Column(name = "type")
	private SystemType type;
	
	public enum SystemType {
		AWISC, RDSH
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public SystemType getType() {
		return type;
	}

	public void setType(SystemType type) {
		this.type = type;
	}

}
