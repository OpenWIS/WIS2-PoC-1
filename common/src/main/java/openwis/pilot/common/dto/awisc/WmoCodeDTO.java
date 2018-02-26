package openwis.pilot.common.dto.awisc;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class WmoCodeDTO {

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
