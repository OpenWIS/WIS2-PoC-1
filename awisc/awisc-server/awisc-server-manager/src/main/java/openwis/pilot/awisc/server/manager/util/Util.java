package openwis.pilot.awisc.server.manager.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class Util {

	public static String readResource(String resourcePath) throws IOException {
		Bundle b = FrameworkUtil.getBundle(Util.class);
		InputStream is = b.getEntry(resourcePath).openStream();
		return readFile(is);
	}

	private static String readFile(InputStream is) throws IOException {
		Scanner s = new Scanner(is);
		s.useDelimiter("\\A");
		String result = s.hasNext() ? s.next() : "";
		s.close();
		return result;
	}

}
