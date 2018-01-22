package openwis.pilot.ldsh.manager.mappers;

import openwis.pilot.ldsh.common.dto.CountryDTO;
import openwis.pilot.ldsh.manager.model.Country;

import org.mapstruct.Mapper;

@Mapper
public interface CountryMapper {

	CountryDTO toCountryDTO(Country country);

	Country toCountry(CountryDTO countryDto);
}
