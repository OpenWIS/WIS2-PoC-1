package openwis.pilot.rdsh.server.manager.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import openwis.pilot.rdsh.server.common.dto.LDSHDTO;
import openwis.pilot.rdsh.server.manager.mappers.LDSHMapperImpl;
import openwis.pilot.rdsh.server.manager.model.Ldsh;
import openwis.pilot.rdsh.server.manager.model.QLdsh;
import openwis.pilot.rdsh.server.manager.service.LDSHService;
import org.apache.commons.lang3.StringUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Singleton
@Transactional
@OsgiServiceProvider(classes = {LDSHService.class})
public class LDSHServiceImpl implements LDSHService {

  // Logger reference.
  private static final Logger LOGGER = Logger.getLogger(LDSHServiceImpl.class.getName());

  // QuerydDSL helpers.
  private static QLdsh qLdsh = QLdsh.ldsh;

  // Entity Manager ref.
  @PersistenceContext(unitName = "rdsh-pu")
  private EntityManager em;

  /**
   * Fetches a single LDSH by Id.
   *
   * @param ldshId The Id of the LDSH to fetch.
   * @return Returns the LDSH entity object.
   */
  private Ldsh getLDSHById(String ldshId) {
    return new JPAQueryFactory(em)
        .selectFrom(qLdsh)
        .where(qLdsh.id.eq(ldshId))
        .fetchOne();
  }

  @Override
  public List<LDSHDTO> getLDSH() {
    final List<Ldsh> ldshList = new JPAQueryFactory(em)
        .selectFrom(qLdsh)
        .orderBy(qLdsh.title.asc())
        .fetch();

    return new LDSHMapperImpl().toLDSHDTOList(ldshList);
  }

  @Override
  public LDSHDTO getLDSH(String ldshId) {
    return new LDSHMapperImpl().toLDSHDTO(getLDSHById(ldshId));
  }

  @Override
  public void deleteLDSH(String ldshId) {
    new JPAQueryFactory(em)
        .delete(qLdsh)
        .where(qLdsh.id.eq(ldshId))
        .execute();
  }

  @Override
  public LDSHDTO saveLDSH(LDSHDTO ldshDTO) {
    final Ldsh ldsh = new LDSHMapperImpl().toLDSH(ldshDTO);
    if (StringUtils.isEmpty(ldsh.getId())) {
      ldsh.setId(UUID.randomUUID().toString());
    }
    em.merge(ldsh);

    return new LDSHMapperImpl().toLDSHDTO(ldsh);
  }

  @Override
  public boolean validateLDSHToken(String ldshToken) {
    return new JPAQueryFactory(em)
        .selectFrom(qLdsh)
        .where(qLdsh.token.eq(ldshToken))
        .fetchOne() != null;
  }
}
