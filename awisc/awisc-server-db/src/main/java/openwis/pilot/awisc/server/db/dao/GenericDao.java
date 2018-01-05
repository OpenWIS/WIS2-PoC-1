package openwis.pilot.awisc.server.db.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

	@SuppressWarnings("unchecked")
	public T get(Object id) {
		try {
			Type genericType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			return (T) entityManager.find(Class.forName(genericType.getTypeName()), id);
		} catch (Throwable t) {
			logger.log(Level.SEVERE, t.getMessage(), t);
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public List<T> list() {
		try {
			Type genericType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			String jpql = "from " + genericType.getTypeName();
			return entityManager.createQuery(jpql).getResultList();
		} catch (Throwable t) {
			logger.log(Level.SEVERE, t.getMessage(), t);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> executeSimpleListQuery(QueryFragment... fragments) {
		try {
			Type genericType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			Map<String, Object> parameters = new LinkedHashMap<String, Object>();

			String jpql = "from " + genericType.getTypeName() + " where ";

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
			Type genericType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			Map<String, Object> parameters = new LinkedHashMap<String, Object>();

			String jpql = "from " + genericType.getTypeName() + " where ";

			for (QueryFragment qf : fragments) {
				JpqlFragment jpqlf = processQueryFragment(qf);
				jpql += jpqlf.getJpql();
				parameters.putAll(jpqlf.getParameters());
			}

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
			String paramName = equals.getKey() + "__";
			jpqlf.setJpql(" " + equals.getKey() + "= :" + paramName + " ");
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
		logger.log(Level.INFO, "JPQL: [" + jpqlf.getJpql() + "]");
		return jpqlf;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
