import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {SettingDTO} from "../dto/Setting.dto";

@Injectable()
export class SettingsService {

  constructor(private http: HttpClient) { }

  // Return all settings.
  list(): any {
    return this.http.get(environment.CONSTANTS.API_ROOT + "/settings");
  }

  // Save settings.
  save(settings: SettingDTO[]): any {
    return this.http.post(environment.CONSTANTS.API_ROOT + "/settings", JSON.stringify(settings),
      {headers:{'Content-Type': 'application/json'}});
  }
}
