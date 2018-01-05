package openwis.pilot.awisc.server.db.dao;

public interface IDaoFactory {
	
	<T> GenericDao<T> getDao(Class<T> entityClass);

}
