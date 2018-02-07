package openwis.pilot.ldsh.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import openwis.pilot.ldsh.common.dto.RemoteSystemDTO;
import openwis.pilot.ldsh.common.dto.SysPropertyDTO;
import openwis.pilot.ldsh.manager.model.QDataset;
import openwis.pilot.ldsh.manager.model.QRemoteSystem;
import openwis.pilot.ldsh.manager.model.RemoteSystem;
import openwis.pilot.ldsh.manager.model.SysProperty;
import openwis.pilot.ldsh.manager.model.QSysProperty;
import openwis.pilot.ldsh.manager.service.SystemService;
import openwis.pilot.ldsh.manager.mappers.SysPropertyMapperImpl;
import openwis.pilot.ldsh.manager.mappers.RemoteSystemMapperImpl;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Singleton
@OsgiServiceProvider(classes = { SystemService.class })
public class SystemServiceImpl implements SystemService {

	@PersistenceContext(unitName = "ldshPU")
	private EntityManager em;
	
	private static final Logger logger = Logger.getLogger(SystemServiceImpl.class.getName());

	private static QRemoteSystem qRemoteSystem = QRemoteSystem.remoteSystem;
	
	private static QSysProperty qSysProperty = QSysProperty.sysProperty;
	
	
	@Override
	public RemoteSystemDTO getAwisc() {

		final RemoteSystem awisc = new JPAQueryFactory(em).selectFrom(qRemoteSystem).where(qRemoteSystem.type.eq(RemoteSystem.SystemType.AWISC)).fetchOne();
				if (awisc == null){
					System.out.println("No AWISC found");
					return null;
				}
		return new  RemoteSystemMapperImpl().toRemoteSystemDTO(awisc);
	}

	@Override
	public RemoteSystemDTO getRdsh() {
		final RemoteSystem rdsh = new JPAQueryFactory(em).selectFrom(qRemoteSystem).where(qRemoteSystem.type.eq(RemoteSystem.SystemType.RDSH)).fetchOne();
		if (rdsh == null) {
			System.out.println("No RDSH found");
			return null;
		}
		return new RemoteSystemMapperImpl().toRemoteSystemDTO(rdsh);

	}

	@Override
	@Transactional
	public RemoteSystemDTO saveSystem(RemoteSystemDTO remoteSystemDto) {
		try {
			final RemoteSystem rs = new JPAQueryFactory(em).selectFrom(qRemoteSystem).where(qRemoteSystem.id.eq(remoteSystemDto.getId())).fetchOne();

			if (rs != null) {
				em.merge(new RemoteSystemMapperImpl().toRemoteSystem(remoteSystemDto));

			} else {
				em.persist(new RemoteSystemMapperImpl().toRemoteSystem(remoteSystemDto));
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "error", e);
			e.printStackTrace();
		}
		return remoteSystemDto;
	}

	@Override
	@Transactional
	public SysPropertyDTO saveSystemProperty(SysPropertyDTO sysPropertyDTO) {

		List<SysProperty> sysproperties = new SysPropertyMapperImpl().toSysProperty(sysPropertyDTO);

		for (SysProperty systemPropery : sysproperties) {
			try {
				final SysProperty sp  =  new JPAQueryFactory(em).selectFrom(qSysProperty).where(qSysProperty.name.eq(systemPropery.getName())).fetchOne();
					if (sp != null){
					systemPropery.setId(sp.getId());
					em.merge(systemPropery);
				} else {
					System.out.println("saving.." + systemPropery.getName() + " - " + systemPropery.getValue());
					em.persist(systemPropery);
				}
	
			} catch (Exception e) {
				logger.log(Level.SEVERE, "error", e);
				e.printStackTrace();
			}
		}

		return sysPropertyDTO;
	}


	@Override
	public SysPropertyDTO getAllSystemProperties() {

		ArrayList<SysProperty> sps = (ArrayList<SysProperty>) new JPAQueryFactory(em).selectFrom(qSysProperty).fetch();
		
		return (new SysPropertyMapperImpl()).toSysPropertyDTO(sps);
	}

	@Override
	public String getSystemPropertyValue(String systemPropertyname) {
		
		final SysProperty sp  =  new JPAQueryFactory(em).selectFrom(qSysProperty).where(qSysProperty.name.eq(systemPropertyname)).fetchOne();
		
		return sp.getValue();
	}


}
