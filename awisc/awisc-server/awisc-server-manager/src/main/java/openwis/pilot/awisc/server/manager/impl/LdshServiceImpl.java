package openwis.pilot.awisc.server.manager.impl;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import com.querydsl.jpa.impl.JPAQueryFactory;

import openwis.pilot.awisc.server.common.dto.LdshDTO;
import openwis.pilot.awisc.server.manager.mappers.LdshMapperImpl;
import openwis.pilot.awisc.server.manager.model.Ldsh;
import openwis.pilot.awisc.server.manager.model.QLdsh;
import openwis.pilot.awisc.server.manager.service.LdshService;

@Singleton
@Transactional
@OsgiServiceProvider(classes = { LdshService.class })
public class LdshServiceImpl implements LdshService {

	// Logger reference.
	private static final Logger LOGGER = Logger.getLogger(LdshServiceImpl.class.getName());

	// QuerydDSL helpers.
	private static QLdsh qLdsh = QLdsh.ldsh;

	// Entity Manager ref.
	@PersistenceContext(unitName = "awisc-pu")
	private EntityManager em;

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
	public List<LdshDTO> getLdsh() {
		final List<Ldsh> ldshList = new JPAQueryFactory(em).selectFrom(qLdsh).orderBy(qLdsh.name.asc()).fetch();

		return new LdshMapperImpl().toLdshDTOList(ldshList);
	}

	@Override
	public LdshDTO getLdsh(Long ldshId) {
		return new LdshMapperImpl().toLdshDTO(getLdshById(ldshId));
	}

	@Override
	public void deleteLdsh(Long ldshId) {
		new JPAQueryFactory(em).delete(qLdsh).where(qLdsh.id.eq(ldshId)).execute();
	}

	@Override
	public LdshDTO saveLdsh(LdshDTO ldshDTO) {
		final Ldsh ldsh = new LdshMapperImpl().toLdsh(ldshDTO);
		em.merge(ldsh);

		return new LdshMapperImpl().toLdshDTO(ldsh);
	}

	@Override
	public boolean validateLdshToken(String ldshToken) {
		return new JPAQueryFactory(em).selectFrom(qLdsh).where(qLdsh.token.eq(ldshToken)).fetchOne() != null;
	}
}
