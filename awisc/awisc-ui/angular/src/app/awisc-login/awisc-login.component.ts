import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AppComponent } from "../app.component";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { Observable } from "rxjs/Observable";
import { MatChipInputEvent } from "@angular/material";
import { User } from "../_dto/User.dto";
import { LoginService } from "../../app/_services/rest/login.service";
import { RestClient } from "../../app/_services/rest/rest-client.service";
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
    private loginService: LoginService,
    private restClient: RestClient,
    public snackBar: MatSnackBar
  ) {
    AppComponent.selectedMenuItem = "login";
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      username: new FormControl("", [Validators.required]),
      password: new FormControl("", [Validators.required])
    });
  }

  login() {
    this.loginService.login(this.user, this.onLoginSuccess, null);
    
  }

  logout() {
    this.loginService.logout(this.onLogoutSuccess, null);
  }

  onLogoutSuccess = (response) => {
    sessionStorage.removeItem(environment.CONSTANTS.JWT_STORAGE_NAME);
  };

  onLoginSuccess = (response) => {
    var token = response.headers.get("Authorization");
    sessionStorage.setItem(environment.CONSTANTS.JWT_STORAGE_NAME, token);
    this.router.navigateByUrl('/ldshs');
  };

  // onLoginError = errorObservable => {
  //   do stuff
  // };
}
