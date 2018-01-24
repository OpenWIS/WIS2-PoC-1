package openwis.pilot.ldsh.manager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ldsh_data_format")
public class DataFormat implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -910433257825026437L;

	@Id
	@Column(name = "data_format_id", updatable = false, nullable = false) 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
