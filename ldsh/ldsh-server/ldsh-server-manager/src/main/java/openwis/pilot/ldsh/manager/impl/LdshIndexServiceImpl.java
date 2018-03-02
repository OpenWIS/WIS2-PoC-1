package openwis.pilot.ldsh.manager.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import com.querydsl.jpa.impl.JPAQueryFactory;

import openwis.pilot.common.dto.awisc.DatasetIndexDTO;
import openwis.pilot.common.dto.awisc.LdshIndexDTO;
import openwis.pilot.common.dto.awisc.WmoCodeDTO;
import openwis.pilot.ldsh.manager.model.Dataset;
import openwis.pilot.ldsh.manager.model.QDataset;
import openwis.pilot.ldsh.manager.model.WmoCode;
import openwis.pilot.ldsh.manager.service.LdshIndexService;
import openwis.pilot.ldsh.manager.service.SystemService;

@Singleton
@OsgiServiceProvider(classes = { LdshIndexService.class })
public class LdshIndexServiceImpl implements LdshIndexService {

	private static final Logger logger = Logger.getLogger(LdshIndexServiceImpl.class.getName());
	
	@Inject
    private SystemService systemService;

	private static QDataset qDataset = QDataset.dataset;

	@PersistenceContext(unitName = "ldshPU")
	private EntityManager em;

	@Override
	public LdshIndexDTO getLdshIndexingInformation(String baseUrl) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String ldshName = systemService.getSystemPropertyValue("title");
		String ldshcontact = systemService.getSystemPropertyValue("email");
		String ldshSystemId = systemService.getSystemPropertyValue("systemid");
		
		List<Dataset> datasets = new JPAQueryFactory(em).selectFrom(qDataset).where(qDataset.rdshDissEnabled.eq(true)).fetch();
		
		LdshIndexDTO dto = new LdshIndexDTO();
		dto.setContact(ldshcontact);
		dto.setName(ldshName);;
		dto.setSystemId(ldshSystemId);
		
		
		for(Dataset dataset: datasets) {
			DatasetIndexDTO diDto = new DatasetIndexDTO();
			
			diDto.setName(dataset.getName());
			diDto.setDescription(dataset.getDescription());
			diDto.setPrefix(dataset.getFilenameprefix());
			
			diDto.setUrl(baseUrl + "/view?id=" + dataset.getId());
			
			diDto.setPeriodFrom(dataset.getPeriodFrom()!=null?sdf.format(dataset.getPeriodFrom()):null);
			diDto.setPeriodFrom(dataset.getPeriodTo()!=null?sdf.format(dataset.getPeriodTo()):null);
			diDto.setDataFormat(dataset.getDataformat()!=null?dataset.getDataformat().getName():null);
			
			diDto.setCountry(dataset.getCountry()!=null?dataset.getCountry().getName():null);
			diDto.setRegion(dataset.getState());
			diDto.setCity(dataset.getCity());
			
			diDto.setLatittude(dataset.getLatitude());
			diDto.setLongitude(dataset.getLongitude());
			diDto.setElevation(dataset.getElevation());
			
			diDto.setDownloadUrl(dataset.getDownloadUrl());
			diDto.setDissemitationType(dataset.isSenddata()?DatasetIndexDTO.DissemitationType.NOTIFICATION_AND_DATA:DatasetIndexDTO.DissemitationType.NOTIFICATION_ONLY);
			diDto.setSubscriptionUri(dataset.getSubscriptionUri());			

			diDto.setLicense(dataset.getLicense());
			diDto.setUpdateFrequency(dataset.getFrequencyUnit());
			
			for(WmoCode wc: dataset.getWmoCodes()) {
				WmoCodeDTO wcDto = new WmoCodeDTO();
				wcDto.setCode(wc.getCode());
				diDto.getWmoCodes().add(wcDto);
			}
			
			diDto.setLastUpdate(dataset.getLastUpdate()!=null?sdf.format(dataset.getLastUpdate()):null);
			
			dto.getDatasets().add(diDto);
		}
		
		return dto;
		
	}

}
