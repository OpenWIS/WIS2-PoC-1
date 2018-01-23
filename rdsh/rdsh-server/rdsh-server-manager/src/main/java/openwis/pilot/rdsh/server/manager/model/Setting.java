package openwis.pilot.rdsh.server.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "rdsh_setting")
public class Setting implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name="setting_key")
  private String settingKey;

  @Column(name = "setting_val")
  private String settingVal;

  public static long getSerialVersionUID() {
    return serialVersionUID;
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
