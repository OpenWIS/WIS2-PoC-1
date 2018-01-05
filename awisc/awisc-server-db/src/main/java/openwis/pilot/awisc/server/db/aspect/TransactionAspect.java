package openwis.pilot.awisc.server.db.aspect;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import openwis.pilot.awisc.server.db.dao.GenericDao;

@Aspect
public class TransactionAspect {
	
	private static final Logger logger = Logger.getLogger(TransactionAspect.class.getName());
	
	@Pointcut("execution(@openwis.pilot.awisc.server.common.annotation.Transaction * *(..))")
	public void transactionAnnotation() {}
	
	@Around("transactionAnnotation()")
	public Object wrapTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
		Object o = null;
		GenericDao<?> targetDao = (GenericDao<?>)joinPoint.getTarget();
		EntityManager em = targetDao.getEntityManager();
		try {
			em.getTransaction().begin();
			
			//Run the actual code
			o = joinPoint.proceed();
			
			em.flush();
			em.getTransaction().commit();
			
		}
		catch(Throwable t) {
			logger.log(Level.SEVERE, t.getMessage(), t);
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw t;
		}
		return o;
		
	}

}
