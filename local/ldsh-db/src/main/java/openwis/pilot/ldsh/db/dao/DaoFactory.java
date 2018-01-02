package openwis.pilot.ldsh.db.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.BundleWiring;

@Singleton
@OsgiServiceProvider(classes = { IDaoFactory.class })
public class DaoFactory implements IDaoFactory {

	private static final Logger logger = Logger.getLogger(DaoFactory.class.getName());

	private static Map<Class<?>, GenericDao<?>> daoRegistry = new ConcurrentHashMap<Class<?>, GenericDao<?>>();
	
	private String RESOURCE_PATH = "/";
	// private String RESOURCE_PATH = "/openwis/pilot";
	
	@PersistenceContext(unitName = "ldshPU")
	protected EntityManagerFactory emf;
	// private EntityManager em;
	




	@SuppressWarnings("unchecked")
	private <T> GenericDao<T> getCandidateDaoExtenderClass(Class<T> entityClass) {
		try {

			Bundle b = FrameworkUtil.getBundle(this.getClass());
			BundleWiring bundleWiring = b.adapt(BundleWiring.class);

			Collection<String> resources = bundleWiring.listResources(RESOURCE_PATH, "*.class",
					BundleWiring.LISTRESOURCES_RECURSE);

			for (String r : resources) {
				String className = r.replace("/", ".").substring(0, r.length() - ".class".length());
				Class<?> clazz = Class.forName(className);

				if (GenericDao.class.isAssignableFrom(clazz)) {
					try {
						Type genericType = ((ParameterizedType) clazz.getGenericSuperclass())
								.getActualTypeArguments()[0];
						if (genericType.getTypeName().equals(entityClass.getName())) {
							return (GenericDao<T>) clazz.newInstance();
						}
					} catch (ClassCastException cce) {
						// would throw exception if there is no actual generic type (e.g. GenericDao<T>)
					}
				}
			}
		} catch (Throwable t) {
			logger.log(Level.SEVERE, t.getMessage(), t);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public synchronized <T> GenericDao<T> getDao(Class<T> entityClass) {
		GenericDao<T> dao = (GenericDao<T>) daoRegistry.get(entityClass);
		if (dao == null) {
			dao = getCandidateDaoExtenderClass(entityClass);
System.out.println("dao ok: "+dao.toString());
			if (dao == null) {
System.out.println("NO DAO FOUND");
				
				dao = new GenericDao<T>();
			}
			
			System.out.println("Entitymanagerfactory to string"+ emf.toString());
			System.out.println("Entitymanagerfactory is open"+ emf.isOpen());
			dao.setEntityManager(emf.createEntityManager());
			// dao.setEntityManager(em);
			daoRegistry.put(entityClass, dao);
		}
		return dao;
	}


}
