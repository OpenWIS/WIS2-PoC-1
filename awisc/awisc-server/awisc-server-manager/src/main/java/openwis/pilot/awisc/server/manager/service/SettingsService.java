package openwis.pilot.awisc.server.manager.service;

import java.util.List;
import java.util.Optional;

import openwis.pilot.awisc.server.common.dto.SettingDTO;

public interface SettingsService {

  /**
   * Returns all settings available in the system.
   * @return Returns the list of settings.
   */
  List<SettingDTO> getSettings();

  /**
   * Updates system settings.
   * @param settings The list of settings to update.
   */
  void saveSettings(List<SettingDTO> settings);

  /**
   * Returns the specified setting.
   * @param settingKey The key of the setting to fetch.
   * @return The value of the specified setting.
   */
  Optional<SettingDTO> getSetting(String settingKey);
}
