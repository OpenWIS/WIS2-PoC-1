package openwis.pilot.ldsh.manager.service;

import java.io.File;

public interface FileService {
	
	File getFile(String relativeUrl, String prefix);

}
