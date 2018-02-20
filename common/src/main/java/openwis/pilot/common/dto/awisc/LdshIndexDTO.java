package openwis.pilot.common.dto.awisc;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * LDSH indexing information for AWISC
 *
 */
@XmlRootElement()
public class LdshIndexDTO {
	
	/**
	 * The LDSH name
	 */
	private String name;
		
	/**
	 * The LDSH system Id
	 */
	private String systemId;
		
	/**
	 * The LDSH contact details
	 */
	private String contact;

	/**
	 * The datasets for this LDSH
	 */
	private List<DatasetIndexDTO> datasets = new ArrayList<DatasetIndexDTO>();

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSystemId() {
		return systemId;
	}


	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public List<DatasetIndexDTO> getDatasets() {
		return datasets;
	}


	public void setDatasets(List<DatasetIndexDTO> datasets) {
		this.datasets = datasets;
	}
	

}
