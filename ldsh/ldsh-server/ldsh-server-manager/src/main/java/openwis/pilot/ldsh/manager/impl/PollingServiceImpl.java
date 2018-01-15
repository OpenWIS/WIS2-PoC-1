package openwis.pilot.ldsh.manager.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Singleton;

//import openwis.pilot.ldsh.db.dao.IDaoFactory;
import openwis.pilot.ldsh.manager.service.PollingService;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.core.osgi.OsgiDefaultCamelContext;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

@Singleton
@OsgiServiceProvider(classes = { PollingService.class })
@ContextName("Poll-service")
public class PollingServiceImpl extends RouteBuilder implements PollingService {

	// @OsgiService
	// @Inject
	// private IDaoFactory iDaoFactory;

	protected BundleContext context;
	protected CamelContext camelContext;

	private static final Logger logger = Logger
			.getLogger(PollingServiceImpl.class.getName());

	// private static String FTP_URL="ftp://localhost/?delay=5s&move=../done&passiveMode=true&password=openwis&username=openwis";
	private static String FTP_URL = "ftp://localhost/?delay=5s&delete=true&passiveMode=true&password=openwis&username=openwis";

	// . for now we delete them from the FTP server..
	// TODO via settigs page..
	// ADD start stop method
	/** add parameters :
	@param ftp server url
	@param destination path..or queue
	@param delay time, unit, username password.
	*/
	@Override
	public void poll() {

		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(
					this.getClass()).getBundleContext();
			this.camelContext = new OsgiDefaultCamelContext(bundleContext);
			this.camelContext.addRoutes(this);
			this.camelContext.start();

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void configure() throws Exception {

		getContext().getShutdownStrategy().setTimeout(10);

		from(FTP_URL).log(FTP_URL).log("getting from " + FTP_URL)
		.to("file:target/download").log("Downloaded file ${file:name} complete.");

	}

	@Override
	public void startPolling() {

		try {
			this.camelContext.start();

			logger.log(Level.INFO,"*********************************************************************************");
			logger.log(Level.INFO,"Camel will route files from the FTP server: " + FTP_URL+ " to the target/download directory.");
			logger.log(Level.INFO,"*********************************************************************************");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void stopPolling() {
		try {
			this.camelContext.stop();

			logger.log(Level.INFO,"*********************************************************************************");
			logger.log(Level.INFO, "Camel stoped.");
			logger.log(Level.INFO,"*********************************************************************************");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}
