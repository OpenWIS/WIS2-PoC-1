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
    console.log(this.user);
    this.restClient
      .post("/login", null, this.user)
      .subscribe(this.onLoginSuccess, this.onLoginError);
  }

  onLoginSuccess = response => {
    console.log(response);
    console.log(response.json());
    var token = response.headers.get("X-Authorization");
    alert(token);
    // this.storage.set('token', token).then(() => {
    //   this.crudService.getToken().then(() => this.sendHeaderMessage('mainComponent', 'home', 'Home', 'homePageNavBar'));
    // });
  };

  onLoginError = errorObservable => {
    this.user.username = null;
    this.user.password = null;
    var errorObject = JSON.parse(errorObservable.error);
    this.snackBar.open(errorObject.code, "Close", {
      duration: 10000,
      verticalPosition: "top"
    });
  };
}
