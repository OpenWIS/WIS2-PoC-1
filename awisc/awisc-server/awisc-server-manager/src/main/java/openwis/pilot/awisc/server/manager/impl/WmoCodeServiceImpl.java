package openwis.pilot.awisc.server.manager.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import com.querydsl.jpa.impl.JPAQueryFactory;

import openwis.pilot.awisc.server.common.dto.WmoCodeDTO;
import openwis.pilot.awisc.server.manager.mappers.WmoCodeMapperImpl;
import openwis.pilot.awisc.server.manager.model.QWmoCode;
import openwis.pilot.awisc.server.manager.model.WmoCode;
import openwis.pilot.awisc.server.manager.service.WmoCodeService;

@Singleton
@Transactional
@OsgiServiceProvider(classes = { WmoCodeService.class })
public class WmoCodeServiceImpl implements WmoCodeService {

	private static final Logger logger = Logger.getLogger(WmoCodeServiceImpl.class.getName());

	@PersistenceContext(unitName = "awisc-pu")
	private EntityManager em;

	QWmoCode qQWmoCode = QWmoCode.wmoCode;

	/* (non-Javadoc)
	 * @see openwis.pilot.awisc.server.manager.service.WmoCodeService#getWmoCodes()
	 */
	@Override
	public List<WmoCodeDTO> getWmoCodes() {
		List<WmoCode> wmoCodes = new JPAQueryFactory(em).selectFrom(qQWmoCode).orderBy(qQWmoCode.name.asc()).fetch();
		return new WmoCodeMapperImpl().toWmoCodeDTOList(wmoCodes);
	}
}
