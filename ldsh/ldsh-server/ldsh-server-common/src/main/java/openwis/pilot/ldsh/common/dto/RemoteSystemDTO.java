package openwis.pilot.ldsh.common.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RemoteSystemDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -410749397306003040L;

	
	public RemoteSystemDTO(){
		
	}
	
	public RemoteSystemDTO(Long id, String name, String token, String url,
			boolean status, String type) {
		super();
		this.id = id;
		this.name = name;
		this.token = token;
		this.url = url;
		this.status = status;
		this.type = type;
	}

	@XmlElement
	private Long id;

	@XmlElement
	private String name;
	
	@XmlElement
	private String token;

	@XmlElement
	private String url;

	@XmlElement
	private boolean status;

	@XmlElement
	private String type;


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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "RemoteSystemDTO [id=" + id + ", name=" + name + ", token="
				+ token + ", url=" + url + ", status=" + status + ", type="
				+ type + "]";
	}
	
}

