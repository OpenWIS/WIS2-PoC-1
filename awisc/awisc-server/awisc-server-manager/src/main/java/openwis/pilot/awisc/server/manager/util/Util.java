package openwis.pilot.awisc.server.manager.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;
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
		return (T) bc.getService(sr);
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue(/* Collections.reverseOrder() */))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	/**
	 * @return the JSON providers for a CXF client call
	 */
	public static List<JSONProvider<?>> getJsonProviders() {
		List<JSONProvider<?>> providers = new ArrayList<JSONProvider<?>>();
		JSONProvider<?> jsonProvider = new JSONProvider<>();
		jsonProvider.setDropRootElement(true);
		jsonProvider.setDropCollectionWrapperElement(true);
		jsonProvider.setSerializeAsArray(true);
		jsonProvider.setSupportUnwrapped(true);
		providers.add(jsonProvider);

		return providers;
	}

}
