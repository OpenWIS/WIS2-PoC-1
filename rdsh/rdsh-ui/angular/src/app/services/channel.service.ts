import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ChannelService {

  constructor(private http: HttpClient) { }


  list(): Observable<any> {
    return this.http.get(environment.CONSTANTS.API_ROOT + '/channel');
  }

  // Fetch a specific Channel by Id.
  get(channelId: string): Observable<any> {
    return this.http.get(environment.CONSTANTS.API_ROOT + '/channel/' + channelId);
  }

  // Pugre a Channel
  purge(channelId: string): Observable<any> {
    return this.http.get(environment.CONSTANTS.API_ROOT + '/channel/purge/' + channelId);
  }

 // Delete a channel.
  delete(channelId: string): Observable<any> {
    return this.http.delete(environment.CONSTANTS.API_ROOT + '/channel/' + channelId);
  }

}
