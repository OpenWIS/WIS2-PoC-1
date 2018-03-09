import { Component, OnInit, ViewChild, ElementRef } from "@angular/core";
import { RouterModule, Routes, Router } from "@angular/router";
import { SettingsService } from "./_services/rest/settings.service";
import { MatSidenav, MatIconRegistry } from "@angular/material";
import { LoginService } from "../app/_services/rest/login.service";
import { RestClient } from "../app/_services/rest/rest-client.service";
import { AuthUtil } from "../app/_services/auth.util";
import { environment } from "../environments/environment";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class AppComponent implements OnInit {
  @ViewChild("sidenav") private sidenav: MatSidenav;
  @ViewChild("titleText") private titleText: ElementRef;
  @ViewChild("copyright") private copyright: ElementRef;
  @ViewChild("email") private email: ElementRef;

  currentUrl: string;
  sidenavOpen: boolean = false;
  isLoggedIn: boolean = false;
  static selectedMenuItem: any;

  showMenu: boolean = false;

  constructor(
    private router: Router,
    private settingsService: SettingsService,
    public matIconRegistry: MatIconRegistry,
    private loginService: LoginService,
    private restClient: RestClient,
    private authUtil: AuthUtil
  ) {
    matIconRegistry.registerFontClassAlias("fa");

    this.authUtil.isLoggedIn().subscribe(loggedIn => {
      console.log("AppComponent isLoggedIn subscriber: " + loggedIn);
      if (loggedIn) {
        this.showMenu = true;
        this.isLoggedIn = true;
      }
      else {
        this.sidenav.close();
        this.showMenu = false;
        this.isLoggedIn = false;
      }
    });
  }

  getSidenavOpen(){
    return this.sidenavOpen;
  }

  onOpenedChange() {
    this.sidenavOpen = this.sidenav.opened;
  }

  getSettingValue(data, settingKey) {
    for (let setting of data) {
      if (setting.settingKey === settingKey) {
        return setting.settingVal;
      }
    }
  }

  listSettingsCallback = response => {

    var data = response.body;
    this.titleText.nativeElement.innerHTML = this.getSettingValue(
      data,
      "title"
    );
    this.copyright.nativeElement.innerHTML = this.getSettingValue(
      data,
      "copyright"
    );
    this.email.nativeElement.innerHTML = this.getSettingValue(data, "email");
  };

  ngOnInit() {
    let currentUrl = this.router.url;
    this.settingsService.list(this.listSettingsCallback, null);
  }

  logout() {
    this.loginService.logout(this.onLogoutSuccess, null);
  }

  onLogoutSuccess = response => {
    sessionStorage.removeItem(environment.CONSTANTS.JWT_STORAGE_NAME);
    this.authUtil.setLoggedIn(false);
    this.router.navigateByUrl("/");
  };

  
  clicked(object) {
    //AppComponent.selectedMenuItem = object;
  }

  get selectedMenuItem() {
    return AppComponent.selectedMenuItem;
  }

  navigateToLogin() {
    this.router.navigateByUrl("/login");
  }

  ngAfterViewInit() { }
}
