package openwis.pilot.awisc.server.db.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import openwis.pilot.awisc.server.common.annotation.Transaction;
import openwis.pilot.awisc.server.db.dao.simplequery.And;
import openwis.pilot.awisc.server.db.dao.simplequery.Equals;
import openwis.pilot.awisc.server.db.dao.simplequery.JpqlFragment;
import openwis.pilot.awisc.server.db.dao.simplequery.Or;
import openwis.pilot.awisc.server.db.dao.simplequery.QueryFragment;

public class GenericDao<T> {

	private static final Logger logger = Logger.getLogger(GenericDao.class.getName());

	protected EntityManager entityManager;
	
	protected Class<T> entityClass;
	
	public GenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Transaction
	public void create(T o) {
		entityManager.persist(o);
	}

	@Transaction
	public void update(T o) {
		entityManager.merge(o);
	}

	@Transaction
	public void delete(T o) {
		entityManager.remove(o);
	}

	public T get(Object id) {
		try {
			return (T) entityManager.find(entityClass, id);
		} catch (Throwable t) {
			logger.log(Level.SEVERE, t.getMessage(), t);
		}
		return null;

	}

	@Transaction
	@SuppressWarnings("unchecked")
	public List<T> list() {
		try {
			String jpql = "from " + entityClass.getSimpleName() + " as o";
			return entityManager.createQuery(jpql).getResultList();
		} catch (Throwable t) {
			logger.log(Level.SEVERE, t.getMessage(), t);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> executeSimpleListQuery(QueryFragment... fragments) {
		try {
			Map<String, Object> parameters = new LinkedHashMap<String, Object>();

			String jpql = "select o from " + entityClass.getSimpleName() + " o where ";

			for (QueryFragment qf : fragments) {
				JpqlFragment jpqlf = processQueryFragment(qf);
				jpql += jpqlf.getJpql();
				parameters.putAll(jpqlf.getParameters());
			}

			Query q = entityManager.createQuery(jpql);
			for (Entry<String, Object> e : parameters.entrySet()) {
				q.setParameter(e.getKey(), e.getValue());
			}
			return q.getResultList();
		} catch (Throwable t) {
			logger.log(Level.SEVERE, t.getMessage(), t);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public T executeSimpleGetQuery(QueryFragment... fragments) {
		try {
			Map<String, Object> parameters = new LinkedHashMap<String, Object>();

			String jpql = "from " + entityClass.getSimpleName() + " o where ";
			
			for (QueryFragment qf : fragments) {
				JpqlFragment jpqlf = processQueryFragment(qf);
				jpql += jpqlf.getJpql();
				parameters.putAll(jpqlf.getParameters());
			}
			
			logger.info("JPQL: [" + jpql + "]");
			Query q = entityManager.createQuery(jpql);
						
			for (Entry<String, Object> e : parameters.entrySet()) {
				q.setParameter(e.getKey(), e.getValue());
			}
			return (T)q.getSingleResult();
		} catch (Throwable t) {
			logger.log(Level.SEVERE, t.getMessage(), t);
		}
		return null;
	}

	private JpqlFragment processQueryFragment(QueryFragment qf) {
		JpqlFragment jpqlf = new JpqlFragment();
		switch (qf.getType()) {
		case EQUALS:
			Equals<?> equals = (Equals<?>) qf;
			String paramName = "parameter" + equals.getKey();
			jpqlf.setJpql(" o." + equals.getKey() + " =:" + paramName + " ");
			jpqlf.getParameters().put(paramName, equals.getValue());
			break;
		case AND:
			And and = (And) qf;
			String jpqAndlString = " ";
			for (QueryFragment andFragment : and.getFragments()) {
				JpqlFragment childJpqlf = processQueryFragment(andFragment);
				jpqAndlString += childJpqlf.getJpql() + " and ";
				jpqlf.getParameters().putAll(childJpqlf.getParameters());
			}
			if (jpqAndlString.length() > 1) {
				jpqAndlString = jpqAndlString.substring(0, jpqAndlString.length() - 4);
			}
			jpqlf.setJpql(jpqAndlString);
			break;
		case OR:
			Or or = (Or) qf;
			String jpqlOrString = " ";
			for (QueryFragment andFragment : or.getFragments()) {
				JpqlFragment childJpqlf = processQueryFragment(andFragment);
				jpqlOrString += childJpqlf.getJpql() + " or ";
				jpqlf.getParameters().putAll(childJpqlf.getParameters());
			}
			if (jpqlOrString.length() > 1) {
				jpqlOrString = jpqlOrString.substring(0, jpqlOrString.length() - 4);
			}
			break;
		}
		return jpqlf;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

}
