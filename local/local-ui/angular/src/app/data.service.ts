import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams }
from '@angular/http';
import { HttpClient, HttpParams, HttpInterceptor, HttpRequest, HttpHandler,
  HttpEvent, HttpErrorResponse, HttpResponse, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';



@Injectable()
export class DataService {
  headers: Headers;
  options: RequestOptions;

  constructor(private httpClient: HttpClient) {
      this.headers = new Headers({ 'Content-Type': 'application/json','Accept': 'application/json' });
      this.options = new RequestOptions({ headers: this.headers });
  }



  // get call
  public getImportMessage<T>(url: string):Promise<any>  {
    
        return this.httpClient.get<T>("http://localhost:8181"+url).toPromise();
      }


// .post(this.api + url, datasetDTO, {
// todo na pros8esw to full url

  public create(url: string, datasetDTO: any): Observable<any> {

    console.log(datasetDTO);
        return this.httpClient
          .post("http://localhost:8181" +url, datasetDTO, {
            headers: new HttpHeaders().set('Content-Type', 'application/json')
              .set("Access-Control-Allow-Origin", "*")
          });
      }

  private extractData(res: Response) {
      let body = res.json();
      return body || {};
  }

  private handleError(error: any): Promise<any> {
      console.error('An error occurred', error);
      return Promise.reject(error.message || error);
  }
}