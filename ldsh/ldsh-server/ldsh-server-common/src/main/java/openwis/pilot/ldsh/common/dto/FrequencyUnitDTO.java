package openwis.pilot.ldsh.common.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FrequencyUnitDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8103205596013370598L;

	public FrequencyUnitDTO() {
		
	}
	
	public FrequencyUnitDTO(long measurementUnitId, String code, String name) {
		super();
		this.measurementUnitId = measurementUnitId;
		this.code = code;
		this.name = name;
	}

	@XmlElement
	private long measurementUnitId;

	@XmlElement
	private String code;

	@XmlElement
	private String name;

	public long getMeasurementUnitId() {
		return measurementUnitId;
	}

	public void setMeasurementUnitId(long measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
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


}
