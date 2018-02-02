import { Injectable } from '@angular/core';
import { RestClient } from './rest-client.service';
import {environment} from "../../../environments/environment";

@Injectable()
export class LoginService {

    constructor(public restClient: RestClient) {
    }

    login(user, successCallback, errorCallback): void{
        this.restClient.post(environment.CONSTANTS.API_ROOT + '/login', null, user, successCallback, errorCallback).subscribe();
      };

      logout(successCallback, errorCallback): void{
        this.restClient.post(environment.CONSTANTS.API_ROOT + '/logout', null, null, successCallback, errorCallback).subscribe();
      };

}


