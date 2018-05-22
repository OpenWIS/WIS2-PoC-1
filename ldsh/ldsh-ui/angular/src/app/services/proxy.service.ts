import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { environment } from "../../environments/environment";
import { ForwardRequestDTO } from "../dto/ForwardRequest.dto";

@Injectable()
export class ProxyService {

  constructor(private http: HttpClient) { }

  forward(request: ForwardRequestDTO): Observable<any> {
    return this.http.post(environment.CONSTANTS.API_ROOT + 'proxy/forward', JSON.stringify(request),
      { headers: { 'Content-Type': 'application/json' } });
  }

}
