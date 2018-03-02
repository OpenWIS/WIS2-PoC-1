import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams }
    from '@angular/http';
import {
    HttpClient, HttpParams, HttpInterceptor, HttpRequest, HttpHandler,
    HttpEvent, HttpErrorResponse, HttpResponse, HttpHeaders
} from '@angular/common/http';
import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';
import { environment } from '../environments/environment';



@Injectable()
export class DataService {
    headers: Headers;
    options: RequestOptions;

    constructor(private httpClient: HttpClient) {
        this.headers = new Headers({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
        this.options = new RequestOptions({ headers: this.headers });
    }

    public awiscCall<T>(url: string, cmd: string): Promise<any> {
        return this.httpClient.get<T>(url + environment.CONSTANTS.AWISC_ROOT + cmd).toPromise();
    }

    public remoteCall<T>(url: string): Promise<any> {

        return this.httpClient.get<T>(url).toPromise();
    }

    public getCall<T>(url: string): Promise<any> {
        return this.httpClient.get<T>(environment.CONSTANTS.API_ROOT + url).toPromise();
    }


    public create(url: string, datasetDTO: any): Observable<any> {
        return this.httpClient.post(environment.CONSTANTS.API_ROOT + url, datasetDTO, {
                headers: new HttpHeaders().set('Content-Type', 'application/json')
                    .set("Access-Control-Allow-Origin", "*")
            });
    }

    // Save or create a dataset. todo use me
    save(url: string, datasetDTO: any): Observable<any> {
        return this.httpClient.post(environment.CONSTANTS.API_ROOT + url, JSON.stringify(datasetDTO),
            { headers: { 'Content-Type': 'application/json' } });
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