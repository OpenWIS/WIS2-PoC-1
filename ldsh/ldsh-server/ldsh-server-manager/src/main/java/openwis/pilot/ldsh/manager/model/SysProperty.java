package openwis.pilot.ldsh.manager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ldsh_sys_properties")
public class SysProperty implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6515748191619668259L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "sysPorp_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "value")
	private String value;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
