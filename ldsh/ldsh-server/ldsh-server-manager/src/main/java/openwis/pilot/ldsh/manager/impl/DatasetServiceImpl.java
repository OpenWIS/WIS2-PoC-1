package openwis.pilot.ldsh.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import openwis.pilot.ldsh.common.dto.CountryDTO;
import openwis.pilot.ldsh.common.dto.DataFormatDTO;
import openwis.pilot.ldsh.common.dto.DatasetDTO;
import openwis.pilot.ldsh.common.dto.FrequencyUnitDTO;
import openwis.pilot.ldsh.common.dto.WmoCodeDTO;
import openwis.pilot.ldsh.manager.model.Country;
import openwis.pilot.ldsh.manager.model.QCountry;
import openwis.pilot.ldsh.manager.model.FrequencyUnit;
import openwis.pilot.ldsh.manager.model.QFrequencyUnit;
import openwis.pilot.ldsh.manager.model.DataFormat;
import openwis.pilot.ldsh.manager.model.QDataFormat;
import openwis.pilot.ldsh.manager.model.Dataset;
import openwis.pilot.ldsh.manager.model.QDataset;
import openwis.pilot.ldsh.manager.model.WmoCode;
import openwis.pilot.ldsh.manager.model.QWmoCode;
import openwis.pilot.ldsh.manager.mappers.DatasetMapperImpl;
import openwis.pilot.ldsh.manager.mappers.DataFormatMapperImpl;
import openwis.pilot.ldsh.manager.mappers.CountryMapperImpl;
import openwis.pilot.ldsh.manager.mappers.WmoCodeMapperImpl;
import openwis.pilot.ldsh.manager.mappers.MeasurementUnitMapperImpl;
import openwis.pilot.ldsh.manager.service.DatasetService;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Singleton
@OsgiServiceProvider(classes = { DatasetService.class })
public class DatasetServiceImpl implements DatasetService {
	
	private static final Logger logger = Logger.getLogger(DatasetServiceImpl.class.getName());
	
	private static QDataset qDataset = QDataset.dataset;
	private static QDataFormat qDataFormat = QDataFormat.dataFormat;
	private static QCountry qCountry = QCountry.country;
	private static QWmoCode qWmoCode = QWmoCode.wmoCode;
	private static QFrequencyUnit qMeasurementUnit = QFrequencyUnit.frequencyUnit;
	
		
	  @PersistenceContext(unitName = "ldshPU")
	  private EntityManager em;


	@Override
	public DatasetDTO getDataSet(Long id) {
		
		final Dataset dataset = new JPAQueryFactory(em).selectFrom(qDataset).where(qDataset.id.eq(id)).fetchOne();

		if (dataset == null) {
			System.out.println("NO Datset found.");
			return null;
		}
		return new DatasetMapperImpl().toDatasetDTO(dataset);
	}


	@Override
	@Transactional
	public DatasetDTO saveDataset(DatasetDTO datasetDto) {

		try {
			if (datasetDto.getId() != null) {
				em.merge(new DatasetMapperImpl().toDataset(datasetDto));
			} else {
				em.persist(new DatasetMapperImpl().toDataset(datasetDto));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error", e);
			e.printStackTrace();
		}
		return datasetDto;
	}
	
	@Override
	public List<DatasetDTO> getAllDataSets() {

		ArrayList<DatasetDTO> datasetDtoList = new ArrayList<DatasetDTO>();
		ArrayList<Dataset> datasets = (ArrayList<Dataset>) new JPAQueryFactory(em).selectFrom(qDataset).fetch();

		for (Dataset ds : datasets) {
			datasetDtoList.add(new DatasetMapperImpl().toDatasetDTO(ds));
		}
		return datasetDtoList;
	}
	

	@Override
	public List<DataFormatDTO> getDataFormats() {

		ArrayList<DataFormat> dataFormats = (ArrayList<DataFormat>) new JPAQueryFactory(em).selectFrom(qDataFormat).fetch();
		ArrayList<DataFormatDTO> dataFormatsList = new ArrayList<DataFormatDTO>();

		for (DataFormat df : dataFormats) {
			dataFormatsList.add(new DataFormatMapperImpl().toDataFormatDTO(df));
		}
		return dataFormatsList;
	}


	@Override
	public List<CountryDTO> getCountries() {

		ArrayList<Country> countries = (ArrayList<Country>) new JPAQueryFactory(em).selectFrom(qCountry).fetch();
		ArrayList<CountryDTO> countrytList = new ArrayList<CountryDTO>();
		
		for (Country c : countries) {
			countrytList.add(new CountryMapperImpl().toCountryDTO(c));
		}
		return countrytList;
	}


	@Override
	@Transactional
	public Boolean deleteDataset(Long id) {
		final Dataset dataset = new JPAQueryFactory(em).selectFrom(qDataset).where(qDataset.id.eq(id)).fetchOne();

		try {
			if (dataset != null) {
				logger.log(Level.INFO, "*** Deleting ... " + dataset.getName());
				em.remove(dataset);
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error", e);
			e.printStackTrace();
			return false;
		}

		return true;
	}


	@Override
	public List<WmoCodeDTO> getWmoCodes() {

		ArrayList<WmoCodeDTO> codesDtoList = new ArrayList<WmoCodeDTO>();
		ArrayList<WmoCode> codes = (ArrayList<WmoCode>) new JPAQueryFactory(em).selectFrom(qWmoCode).fetch();
		for (WmoCode wc : codes) {
			codesDtoList.add(new WmoCodeMapperImpl().toWmoCodeDTO(wc));
		}
		return codesDtoList;
	}


	@Override
	public List<FrequencyUnitDTO> getMeasurementUnits() {
		
		ArrayList<FrequencyUnitDTO> muDtoList = new ArrayList<FrequencyUnitDTO>();
		ArrayList<FrequencyUnit> mesurments = (ArrayList<FrequencyUnit>) new JPAQueryFactory(em).selectFrom(qMeasurementUnit).fetch();
		for (FrequencyUnit mu : mesurments) {
			muDtoList.add(new MeasurementUnitMapperImpl().toMeasurementUnitDTO(mu));
		}
		return muDtoList;
	}


	@Transactional
	@Override
	public void updateDatasetLastUpdate(Long id) {

		try {
			final Dataset dataset = new JPAQueryFactory(em).selectFrom(qDataset).where(qDataset.id.eq(id)).fetchOne();
			dataset.setLastUpdate(new Date());
			em.merge(dataset);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}		
		
	}

}
