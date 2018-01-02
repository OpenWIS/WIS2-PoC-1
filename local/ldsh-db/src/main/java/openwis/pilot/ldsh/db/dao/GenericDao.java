package openwis.pilot.ldsh.db.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericDao<T> {

	private static final Logger logger = Logger.getLogger(GenericDao.class.getName());

	@PersistenceContext(unitName = "ldshPU")
	protected EntityManager entityManager;

	public void create(T o) {
		try {

System.out.println("creating...");

			entityManager.getTransaction().begin();
			entityManager.persist(o);
			entityManager.flush();
		} catch (Throwable t) {
			entityManager.getTransaction().rollback();
System.out.println(t.getStackTrace());
			logger.log(Level.SEVERE, t.getMessage(), t);
		}
		entityManager.getTransaction().commit();
	}

	public void update(T o) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(o);
			entityManager.flush();
		} catch (Throwable t) {
			entityManager.getTransaction().rollback();
			logger.log(Level.SEVERE, t.getMessage(), t);
		}
		entityManager.getTransaction().commit();

	}

	public void delete(T o) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(o);
			entityManager.flush();
		} catch (Throwable t) {
			entityManager.getTransaction().rollback();
			logger.log(Level.SEVERE, t.getMessage(), t);
		}
		entityManager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public T get(Object id) {
		try {
			Type genericType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			return (T)entityManager.find(Class.forName(genericType.getTypeName()), id);
		}
		catch (Throwable t) {
			logger.log(Level.SEVERE, t.getMessage(), t);
		}
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<T> list() {
		try {
			Type genericType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			String sql = "from " + genericType.getTypeName();
			return entityManager.createQuery(sql).getResultList();
		}
		catch (Throwable t) {
			logger.log(Level.SEVERE, t.getMessage(), t);
		}
		return null;
		
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
