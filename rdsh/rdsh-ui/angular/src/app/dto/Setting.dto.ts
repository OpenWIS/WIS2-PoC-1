export class SettingDTO {
  constructor(settingKey: string, settingVal: string) {
    this.settingKey = settingKey;
    this.settingVal = settingVal;
  }
  settingKey: string;
  settingVal: string;
}
