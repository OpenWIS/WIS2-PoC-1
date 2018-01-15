package openwis.pilot.ldsh.manager.service;


public interface PollingService {
	
	public void poll();
	public void startPolling();
	public void stopPolling();

}
