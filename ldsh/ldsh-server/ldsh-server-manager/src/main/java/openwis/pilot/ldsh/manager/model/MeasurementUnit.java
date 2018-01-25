package openwis.pilot.ldsh.manager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ldsh_measurement_unit")
public class MeasurementUnit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4365261104590886710L;

	@Id
	@Column(name = "measurement_unit_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long measurementUnitId;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
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
