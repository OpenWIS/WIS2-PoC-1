import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { environment } from "../../environments/environment";


@Injectable()
export class AuthUtil {

    private loggedIn: Subject<boolean> = new Subject<boolean>();

    constructor(){
        this.setLoggedIn(false);
    }

    isLoggedIn(): Observable<boolean> {
        return this.loggedIn.asObservable();
    }

    setLoggedIn(loggedIn) {
        this.loggedIn.next(loggedIn);
    }
}



