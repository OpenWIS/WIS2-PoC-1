package openwis.pilot.awisc.server.manager.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class Util {

	private static final Logger logger = Logger.getLogger(Util.class.getName());

	public static String readResource(String resourcePath, String charset) throws IOException {
		Bundle b = FrameworkUtil.getBundle(Util.class);
		InputStream is = b.getEntry(resourcePath).openStream();
		return IOUtils.toString(is, charset);
	}
	
	public static <T> T getService(Class<T> serviceClass) throws IOException {
		BundleContext bc = FrameworkUtil.getBundle(Util.class).getBundleContext();
		ServiceReference<T> sr = bc.getServiceReference(serviceClass);
		return (T)bc.getService(sr);
	}

}
