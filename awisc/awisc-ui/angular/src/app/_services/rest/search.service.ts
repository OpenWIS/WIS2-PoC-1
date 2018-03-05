import { Injectable } from '@angular/core';
import { RestClient } from './rest-client.service';
import { environment } from "../../../environments/environment";

@Injectable()
export class SearchService {

  constructor(public restClient: RestClient) {
  }

  simple(searchQuery, successCallback, errorCallback): void {
    this.restClient.post(environment.CONSTANTS.API_ROOT + '/search/simple', null, searchQuery, successCallback, errorCallback).subscribe();
  };

  advanced(searchQuery, successCallback, errorCallback): void {
    this.restClient.post(environment.CONSTANTS.API_ROOT + '/search/advanced', null, searchQuery, successCallback, errorCallback).subscribe();
  };

}


