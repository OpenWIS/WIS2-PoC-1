import { Injectable } from '@angular/core';
import { Headers, RequestOptions, RequestMethod, Request, Response } from '@angular/http';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { ErrorUtil } from '../error.util';
import { SuccessUtil } from '../success.util';

@Injectable()
export class RestClient {

    constructor(public http: HttpClient, private errorUtil: ErrorUtil, private successUtil: SuccessUtil) {
    }

    get(url: string, h: Headers, successCallback: Function, errorCallback: Function): Observable<any> {

        
        //var ctHeaders = new HttpHeaders().set('Content-Type', "application/json")


        return this.http.get(url, {
            headers: new HttpHeaders().set('Content-Type', "application/json"),
            observe: 'response'
        })
            .map((response) => {
                if (response) {
                    if(successCallback){
                        successCallback(response);
                    }
                    else{
                        this.successUtil.showMessage(response.body["code"]);
                    }
                    return response;
                }
            })
            .catch((error: any) => {
                console.log(error);
                if(errorCallback){
                    errorCallback(error.error);
                }
                else{
                    this.errorUtil.showError(error.error.code);
                }
                
                throw Observable.throw(error.error);
            });
    }

    post(url: string, h: Headers, data: Object, successCallback: Function, errorCallback: Function): Observable<any> {

        var cpheaders = new Headers({ 'Content-Type': 'application/json' });

        var requestOptions = new RequestOptions({
            headers: cpheaders
        });

        return this.http.post(url, data, {
            headers: new HttpHeaders().set('Content-Type', "application/json"),
            observe: 'response'
        })
            .map((response) => {
                console.log(response);
                if (response) {
                    if(successCallback){
                        successCallback(response);
                    }
                    else{
                        this.successUtil.showMessage(response.body["code"]);
                    }
                    return response;
                }
            })
            .catch((error: any) => {
                console.log(error);
                if(errorCallback){
                    errorCallback(error.error);
                }
                else{
                    this.errorUtil.showError(error.error.code);
                }

                throw Observable.throw(error.error);
            });
    }

    delete(url: string, h: Headers, successCallback: Function, errorCallback: Function): Observable<any> {

        var cpheaders = new Headers({ 'Content-Type': 'application/json' });

        var requestOptions = new RequestOptions({
            headers: cpheaders
        });

        return this.http.delete(url, {
            headers: new HttpHeaders().set('Content-Type', "application/json"),
            observe: 'response'
        })
            .map((response) => {
                if (response) {
                    if(successCallback){
                        successCallback(response);
                    }
                    else{
                        this.successUtil.showMessage(response.body["code"]);
                    }
                    return response;
                }
            })
            .catch((error: any) => {
                console.log(error);
                if(errorCallback){
                    errorCallback(error);
                }
                else{
                    this.errorUtil.showError(error.error.code);
                }

                throw Observable.throw(error.error);
            });
    }
}


