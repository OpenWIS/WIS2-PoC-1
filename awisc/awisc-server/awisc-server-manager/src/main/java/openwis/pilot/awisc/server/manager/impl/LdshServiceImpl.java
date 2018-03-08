package openwis.pilot.awisc.server.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import com.querydsl.jpa.impl.JPAQueryFactory;

import openwis.pilot.awisc.server.common.dto.LdshDTO;
import openwis.pilot.awisc.server.common.exception.ServiceException;
import openwis.pilot.awisc.server.common.util.Constants.ErrorCode;
import openwis.pilot.awisc.server.manager.mappers.LdshMapperImpl;
import openwis.pilot.awisc.server.manager.model.Ldsh;
import openwis.pilot.awisc.server.manager.model.QLdsh;
import openwis.pilot.awisc.server.manager.service.AwiscIndexingService;
import openwis.pilot.awisc.server.manager.service.LdshService;

@Singleton
@Transactional
@OsgiServiceProvider(classes = { LdshService.class })
public class LdshServiceImpl implements LdshService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7218869005902567277L;

	// Logger reference.
	private static final Logger LOGGER = Logger.getLogger(LdshServiceImpl.class.getName());

	// QuerydDSL helpers.
	private static QLdsh qLdsh = QLdsh.ldsh;

	@PersistenceContext(unitName = "awisc-pu")
	private EntityManager em;
	
	@Inject
	AwiscIndexingService awiscIndexingService;

	/**
	 * Fetches a single Ldsh by Id.
	 *
	 * @param ldshId
	 *            The Id of the Ldsh to fetch.
	 * @return Returns the Ldsh entity object.
	 */
	private Ldsh getLdshById(Long ldshId) {
		return new JPAQueryFactory(em).selectFrom(qLdsh).where(qLdsh.id.eq(ldshId)).fetchOne();
	}

	@Override
	public List<LdshDTO> getLdshs() {
		final List<Ldsh> ldshList = new JPAQueryFactory(em).selectFrom(qLdsh).orderBy(qLdsh.name.asc()).fetch();

		return new LdshMapperImpl().toLdshDTOList(ldshList);
	}

	@Override
	public LdshDTO getLdsh(Long ldshId) {
		return new LdshMapperImpl().toLdshDTO(getLdshById(ldshId));
	}

	@Override
	public void deleteLdsh(Long ldshId) {
		LdshDTO ldsh = getLdsh(ldshId);
		new JPAQueryFactory(em).delete(qLdsh).where(qLdsh.id.eq(ldshId)).execute();
		awiscIndexingService.deleteLdsh(ldsh.getSystemId());
	}

	@Override
	public LdshDTO saveLdsh(LdshDTO ldshDTO) throws ServiceException{
		final Ldsh ldsh = new LdshMapperImpl().toLdsh(ldshDTO);
		Ldsh fromDb;
		
		if(ldshDTO.getId()!=null) {
			fromDb = new JPAQueryFactory(em).selectFrom(qLdsh).where(qLdsh.id.eq(ldshDTO.getId())).fetchOne();
			if(fromDb!=null) {
				ldsh.setId(fromDb.getId());
			}
		}
		else {
			fromDb = new JPAQueryFactory(em).selectFrom(qLdsh).where(qLdsh.systemId.eq(ldshDTO.getSystemId())).fetchOne();
			if(fromDb!=null) {
				throw new ServiceException(ErrorCode.LDSH_SAVE_ERROR_DUPLICATE_SYSTEM_ID);
			}
		}
		
		em.merge(ldsh);
		return new LdshMapperImpl().toLdshDTO(ldsh);
	}

	@Override
	public boolean validateLdshToken(String ldshToken) {
		JPAQueryFactory f = new JPAQueryFactory(em);
		boolean found = f.selectFrom(qLdsh).where(qLdsh.token.eq(ldshToken)).fetchOne() != null;
		if(found) {
			f.update(qLdsh).where(qLdsh.token.eq(ldshToken)).set(qLdsh.registrationDate, new Date());
			return true;
		}
		return false;
	}
}
