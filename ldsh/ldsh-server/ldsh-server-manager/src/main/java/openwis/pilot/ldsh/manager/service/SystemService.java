package openwis.pilot.ldsh.manager.service;

import openwis.pilot.ldsh.common.dto.RemoteSystemDTO;
import openwis.pilot.ldsh.common.dto.SysPropertyDTO;

public interface SystemService {
	
	public RemoteSystemDTO getAwisc();

	public RemoteSystemDTO getRdsh();

	public RemoteSystemDTO saveSystem(RemoteSystemDTO remoteSystem);

	public SysPropertyDTO saveSystemProperty(SysPropertyDTO sysPropertyDTO);

//	public SysPropertyDTO getSystemProperty(String name);
	
	public String getSystemPropertyValue(String systemPropertyname);
	
	public SysPropertyDTO getAllSystemProperties();

}
