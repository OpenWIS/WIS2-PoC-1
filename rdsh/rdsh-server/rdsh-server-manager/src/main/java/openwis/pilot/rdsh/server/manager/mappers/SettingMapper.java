package openwis.pilot.rdsh.server.manager.mappers;

import openwis.pilot.rdsh.server.common.dto.SettingDTO;
import openwis.pilot.rdsh.server.manager.model.Setting;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SettingMapper {
  SettingDTO toSettingDTO(Setting setting);
  Setting toSetting(SettingDTO settingDTO);
  List<SettingDTO> toSettingDTOList(List<Setting> settings);
}
