import { Injectable } from '@angular/core';
import { MatSnackBar } from "@angular/material";
import * as errorMessages from './error-messages.json';


@Injectable()
export class ErrorUtil {

    private errorMessageArray: Array<Object> = [];
    

    constructor(public snackBar: MatSnackBar) {
        console.log("Loading error messages:");
        console.log(errorMessages);
    }


    showError(errorCode){
        this.snackBar.open(errorMessages[errorCode], "Close", {
            duration: 10000,
            verticalPosition: "top"
          });
    }

}



