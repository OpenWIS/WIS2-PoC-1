import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, RequestMethod, Request, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class RestClient {

    private baseURL = "http://localhost:8181/cxf/api";

    constructor(private http: Http) {

    }

    post(url: string, h: Headers, data: Object): Observable<any> {

        var cpheaders = new Headers({ 'Content-Type': 'application/json' });

        var requestOptions = new RequestOptions({
            headers: cpheaders
        });

        console.log(requestOptions);

        return this.http.post(this.baseURL + url, data, requestOptions)
            .map((response: Response) => {
                if (response) {
                    console.log(response);
                    return response;
                }
            })
            .catch((error: any) => {
                throw Observable.throw(error['_body']);
            });
    }
}


