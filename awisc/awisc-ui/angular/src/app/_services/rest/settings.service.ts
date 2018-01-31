import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {SettingDTO} from "../../_dto/Setting.dto";
import 'rxjs/add/operator/filter';
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/mergeAll";
import { RestClient } from './rest-client.service';
import { Headers } from '@angular/http';

@Injectable()
export class SettingsService {

  constructor(private restClient: RestClient) {
  }

  // Return all settings.
  list(successCallback, errorCallback): void {
    this.restClient.get(environment.CONSTANTS.API_ROOT + '/settings',  null, successCallback, errorCallback).subscribe();
  }

  // Save settings.
  save(settings: SettingDTO[], successCallback, errorCallback): void {
      this.restClient.post(environment.CONSTANTS.API_ROOT + '/settings', new Headers({'Content-Type': 'application/json'}),
      settings, successCallback, errorCallback).subscribe();
  }

  // // Find a specific setting by key name.
  // get(settingKey: string): Observable<SettingDTO> {
  //   return this.list()
  //     .map(res => res.filter(data => data["settingKey"] === settingKey))
  //     .mergeAll();
  // }

  // // Find multiple keys from settings by key name.
  // getMany(settingKeys: string[]): Observable<SettingDTO> {
  //   return this.list()
  //     .map(res => res.filter(o => settingKeys.includes(o.settingKey)))
  //     .mergeAll();
  // }
}
