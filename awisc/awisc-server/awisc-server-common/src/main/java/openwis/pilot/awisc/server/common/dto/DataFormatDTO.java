package openwis.pilot.awisc.server.common.dto;

import java.io.Serializable;
import java.util.List;

public class DataFormatDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

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

	public List<DatasetDTO> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DatasetDTO> datasets) {
		this.datasets = datasets;
	}

	
}

