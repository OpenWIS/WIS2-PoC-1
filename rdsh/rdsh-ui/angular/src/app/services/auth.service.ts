import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {environment} from "../../environments/environment";
import {AuthDTO} from "../dto/Auth.dto";

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) { }

  // Authenticate a user.
  login(authDTO: AuthDTO): Observable<any> {
    return this.http.post(environment.CONSTANTS.API_ROOT + '/jwt', JSON.stringify(authDTO),
      {headers:{'Content-Type': 'application/json'}});
  }

  logout(): Observable<any> {
    sessionStorage.removeItem(environment.CONSTANTS.JWT_STORAGE_NAME);
    return this.http.post("", { headers: { 'Content-Type': 'application/json' } });
  }
}
