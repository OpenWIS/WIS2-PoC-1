package openwis.pilot.ldsh.manager.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import openwis.pilot.ldsh.manager.service.PollingService;
import openwis.pilot.ldsh.manager.service.SystemService;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.core.osgi.OsgiDefaultCamelContext;
import org.ops4j.pax.cdi.api.OsgiService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Activate;

import com.querydsl.jpa.impl.JPAQueryFactory;

import openwis.pilot.ldsh.manager.model.SysProperty;
import openwis.pilot.ldsh.manager.model.QSysProperty;

@Singleton
@OsgiServiceProvider(classes = { PollingService.class })
@ContextName("Poll-service")
public class PollingServiceImpl extends RouteBuilder implements PollingService {
	
	@PersistenceContext(unitName = "ldshPU")
	private EntityManager em;
	

	protected BundleContext context;
	protected CamelContext camelContext;
	
	private static QSysProperty qSysProperty = QSysProperty.sysProperty;


	private static final Logger logger = Logger.getLogger(PollingServiceImpl.class.getName());

//	private static String FTP_URL = "ftp://localhost/?delay=5s&delete=true&passiveMode=true&password=openwis&username=openwis";

	private  String URL =""; 
	private  String USERNAME ="";
	private  String PASSWORD ="";
	private  String FTP_URL = "ftp://"+URL+"/?delay=5s&delete=true&passiveMode=true&password="+PASSWORD+"&username="+USERNAME;

	//TODO FIX
	@PostConstruct
@Transactional 
	@Override
    public void startUp() {
		
//	final SysProperty urlSp = new JPAQueryFactory(em).selectFrom(qSysProperty).where(qSysProperty.name.eq("ftpUrl")).fetchOne();
//	todo initialize db connection
 	
    }
	
	
	@Override
	public Boolean poll() {

		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(
					this.getClass()).getBundleContext();
			this.camelContext = new OsgiDefaultCamelContext(bundleContext);
			this.camelContext.addRoutes(this);
			this.camelContext.start();

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void configure() throws Exception {

		getContext().getShutdownStrategy().setTimeout(10);

		from(FTP_URL).log(FTP_URL).log("getting from " + FTP_URL)
		.to("file:target/download").log("Downloaded file ${file:name} complete.");

	}

	@Override
	public Boolean startPolling() {

		try {
		
			System.out.println("reading DB.. ");
			final SysProperty urlSp = new JPAQueryFactory(em).selectFrom(qSysProperty).where(qSysProperty.name.eq("ftpUrl")).fetchOne();
			final SysProperty userSp = new JPAQueryFactory(em).selectFrom(qSysProperty).where(qSysProperty.name.eq("ftpUsername")).fetchOne();
			final SysProperty passSp = new JPAQueryFactory(em).selectFrom(qSysProperty).where(qSysProperty.name.eq("ftpPassword")).fetchOne();
	        
			this.URL = urlSp.getValue();
	        this.USERNAME =  userSp.getValue();
	        this.PASSWORD = passSp .getValue();
	        
			logger.log(Level.INFO, "Poling service is starting....");
			logger.log(Level.INFO,"*********************************************************************************");
			logger.log(Level.INFO,"Camel will route files from the FTP server: " + FTP_URL+ " to the target/download directory.");
			logger.log(Level.INFO,"*********************************************************************************");
			
			return this.poll();
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean stopPolling() {
		try {
			
			this.camelContext.stop();

			logger.log(Level.INFO,"*********************************************************************************");
			logger.log(Level.INFO, "Camel stoped.");
			logger.log(Level.INFO,"*********************************************************************************");
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage());
			e.printStackTrace();
			return false;
		}
	return true;
	}
}
