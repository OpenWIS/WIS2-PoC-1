package openwis.pilot.awisc.server.manager.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import openwis.pilot.awisc.server.common.dto.SettingDTO;
import openwis.pilot.awisc.server.manager.model.Setting;

@Mapper
public interface SettingMapper {
	SettingDTO toSettingDTO(Setting setting);

	Setting toSetting(SettingDTO settingDTO);

	List<SettingDTO> toSettingDTOList(List<Setting> settings);
}
