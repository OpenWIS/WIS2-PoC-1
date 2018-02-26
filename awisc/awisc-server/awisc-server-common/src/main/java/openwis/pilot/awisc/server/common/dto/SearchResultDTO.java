package openwis.pilot.awisc.server.common.dto;

public class SearchResultDTO {

	private LdshDTO ldsh;
	private DatasetDTO dataset;

	public LdshDTO getLdsh() {
		return ldsh;
	}

	public void setLdsh(LdshDTO ldsh) {
		this.ldsh = ldsh;
	}

	public DatasetDTO getDataset() {
		return dataset;
	}

	public void setDataset(DatasetDTO dataset) {
		this.dataset = dataset;
	}

}
