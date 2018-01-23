package openwis.pilot.rdsh.server.common.dto;

public class SettingDTO {
  private String settingKey;
  private String settingVal;

  public SettingDTO() {

  }

  public SettingDTO(String settingKey, String settingVal) {
    this.settingKey = settingKey;
    this.settingVal = settingVal;
  }

  public String getSettingKey() {
    return settingKey;
  }

  public void setSettingKey(String settingKey) {
    this.settingKey = settingKey;
  }

  public String getSettingVal() {
    return settingVal;
  }

  public void setSettingVal(String settingVal) {
    this.settingVal = settingVal;
  }
}
