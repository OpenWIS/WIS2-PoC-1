import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {SettingDTO} from "../dto/Setting.dto";
import 'rxjs/add/operator/filter';
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/mergeAll";

@Injectable()
export class SettingsService {

  constructor(private http: HttpClient) {
  }

  // Return all settings.
  list(): Observable<any> {
    return this.http.get(environment.CONSTANTS.API_ROOT + '/settings');
  }

  // Save settings.
  save(settings: SettingDTO[]): Observable<any> {
    return this.http.post(environment.CONSTANTS.API_ROOT + '/settings', JSON.stringify(settings),
      {headers: {'Content-Type': 'application/json'}});
  }

  // Find a specific setting by key name.
  get(settingKey: string): Observable<SettingDTO> {
    return this.list()
      .map(res => res.filter(data => data["settingKey"] === settingKey))
      .mergeAll();
  }

  // Find multiple keys from settings by key name.
  getMany(settingKeys: string[]): Observable<SettingDTO> {
    return this.list()
      .map(res => res.filter(o => settingKeys.includes(o.settingKey)))
      .mergeAll();
  }
}
