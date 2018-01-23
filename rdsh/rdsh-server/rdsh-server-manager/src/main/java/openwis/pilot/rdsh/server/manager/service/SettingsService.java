package openwis.pilot.rdsh.server.manager.service;

import openwis.pilot.rdsh.server.common.dto.SettingDTO;

import java.util.List;

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
}
