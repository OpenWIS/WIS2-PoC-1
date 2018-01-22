package openwis.pilot.ldsh.db.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

//import javax.transaction.Transactional;
// import com.eurodyn.iborderctrl.traveller.server.db.model.Traveller;
// import com.eurodyn.iborderctrl.traveller.server.db.model.UserAccount;

import openwis.pilot.ldsh.manager.model.Dataset;

public class DatasetDao extends GenericDao<Dataset> {

	private static final Logger logger = Logger.getLogger(DatasetDao.class.getName());

//	@Transactional
	public boolean userExists(String email) {
		logger.log(Level.INFO, "entityManager: " + entityManager);
		String hql_traveller = "FROM Traveller as t WHERE  t.email = :email and t.userAccount.status = true" ;
		int count = entityManager.createQuery(hql_traveller).setParameter("email", email).getResultList().size();
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
}
