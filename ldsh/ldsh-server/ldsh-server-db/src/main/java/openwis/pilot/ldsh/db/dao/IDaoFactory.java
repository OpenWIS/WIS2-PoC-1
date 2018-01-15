package openwis.pilot.ldsh.db.dao;

public interface IDaoFactory {
	
	<T> GenericDao<T> getDao(Class<T> entityClass);

}
