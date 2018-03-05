import { Injectable } from '@angular/core';
import { MatSnackBar } from "@angular/material";
import * as successMessages from '../_resources/success-messages.json';


@Injectable()
export class SuccessUtil {
    

    constructor(public snackBar: MatSnackBar) {
        // console.log("Loading error messages:");
        // console.log(successMessages);
    }


    showMessage(messageCode){
        this.snackBar.open(successMessages[messageCode], "Close", {
            duration: 4000,
            verticalPosition: "top"
          });
    }

}



