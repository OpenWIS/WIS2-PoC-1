package openwis.pilot.ldsh.manager.service;


public interface PollingService {
	
	public void startUp();
	public Boolean  poll();
	public Boolean startPolling();
	public Boolean stopPolling();
	public String getPollingStatus();

}
