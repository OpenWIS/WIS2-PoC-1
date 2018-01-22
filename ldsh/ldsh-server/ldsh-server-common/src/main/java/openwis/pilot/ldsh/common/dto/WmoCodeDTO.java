package openwis.pilot.ldsh.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WmoCodeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1152760499500586253L;

	@XmlElement
	private long id;

	@XmlElement
	private String code;

	@XmlElement
	private String name;

	@XmlElement
	private String continent;

	@XmlElement
	private String uri;

	public WmoCodeDTO() {

	}

	public WmoCodeDTO(long id, String code, String name, String continent,
			String uri) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.continent = continent;
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "WmoCodeDTO [id=" + id + ", code=" + code + ", name=" + name
				+ ", continent=" + continent + ", uri=" + uri + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

}
