import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, RequestMethod, Request, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { ErrorUtil } from './error.util';
import { SuccessUtil } from './success.util';
import {environment} from "../../environments/environment";

@Injectable()
export class RestClient {

    constructor(public http: Http, private errorUtil: ErrorUtil, private successUtil: SuccessUtil) {
    }

    postUnauthorized(url: string, h: Headers, data: Object, successCallback: Function, errorCallback: Function): Observable<any> {

        var cpheaders = new Headers({ 'Content-Type': 'application/json' });

        var requestOptions = new RequestOptions({
            headers: cpheaders
        });

        console.log(requestOptions);

        return this.http.post(environment.CONSTANTS.API_ROOT + url, data, requestOptions)
            .map((response: Response) => {
                if (response) {
                    if(successCallback){
                        successCallback(response);
                    }
                    else{
                        this.successUtil.showMessage(response.json().code);
                    }
                    return response;
                }
            })
            .catch((error: any) => {
                var errorObject = JSON.parse(error['_body']);
                if(errorCallback){
                    errorCallback(error);
                }
                else{
                    this.errorUtil.showError(errorObject.code);
                }
                
                throw Observable.throw(error['_body']);
            });
    }

    post(url: string, h: Headers, data: Object, successCallback: Function, errorCallback: Function): Observable<any> {

        var cpheaders = new Headers({ 'Content-Type': 'application/json' });

        var requestOptions = new RequestOptions({
            headers: cpheaders
        });

        console.log(requestOptions);

        return this.http.post(environment.CONSTANTS.API_ROOT + url, data, requestOptions)
            .map((response: Response) => {
                if (response) {
                    if(successCallback){
                        successCallback(response);
                    }
                    else{
                        this.successUtil.showMessage(response.json().code);
                    }
                    return response;
                }
            })
            .catch((error: any) => {
                
                var errorObject = JSON.parse(error['_body']);
                if(errorCallback){
                    errorCallback(error);
                }
                else{
                    this.errorUtil.showError(errorObject.code);
                }

                throw Observable.throw(error['_body']);
            });
    }
}


