import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AppComponent } from "../app.component";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { Observable } from "rxjs/Observable";
import "rxjs/add/operator/startWith";
import "rxjs/add/operator/map";
import { ENTER, COMMA } from "@angular/cdk/keycodes";
import { MatChipInputEvent } from "@angular/material";
import { User } from "./user.model";
import { RestClient } from "../../app/_services/rest.client";
import { MatSnackBar } from "@angular/material";
import {environment} from "../../environments/environment";

@Component({
  selector: "app-awisc-login",
  templateUrl: "./awisc-login.component.html",
  styleUrls: ["./awisc-login.component.css"]
})
export class AwiscLoginComponent implements OnInit {
  public user = new User();
  loginForm: FormGroup;
  errorMsg = null;
  panelOpenState: boolean = false;
  searchResults: boolean = false;
  filteredStates: Observable<any[]>;
  removable: boolean = true;
  codeSelected: any;
  addOnBlur: any;
  displayFn: any;
  separatorKeysCodes: any;

  onSubmit() {}

  stateCtrl: FormControl;
  searchForm: FormGroup;

  constructor(
    private router: Router,
    private restClient: RestClient,
    public snackBar: MatSnackBar
  ) {
    AppComponent.selectedMenuItem = "awiscSearch";
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      username: new FormControl("", [Validators.required]),
      password: new FormControl("", [Validators.required])
    });
  }

  login() {
    this.restClient
      .postUnauthorized("/login", null, this.user, this.onLoginSuccess, null)
      .subscribe();
  }

  logout() {
    this.restClient
      .post("/logout", null, null, null, null)
      .subscribe();
  }

  onLogoutSuccess = response => {
    localStorage.removeItem('jwtToken');
  };

  // onLogoutError = errorObservable => {
  //   this.user.username = null;
  //   this.user.password = null;
  //   var errorObject = JSON.parse(errorObservable.error);
  //   this.snackBar.open(errorObject.code, "Close", {
  //     duration: 10000,
  //     verticalPosition: "top"
  //   });
  // };

  onLoginSuccess = response => {
    var token = response.headers.get("Authorization");
    //localStorage.setItem('jwtToken', token.substring('Bearer '.length));
    sessionStorage.setItem(environment.CONSTANTS.JWT_STORAGE_NAME, token);
  };

  // onLoginError = errorObservable => {
  //   alert('lalala');
  // };
}
