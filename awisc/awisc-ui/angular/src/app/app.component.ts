import { Component, OnInit, ViewChild, ElementRef } from "@angular/core";
import { RouterModule, Routes, Router } from "@angular/router";
import { SettingsService } from "./_services/rest/settings.service";
import { MatSidenav } from "@angular/material";

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
  static selectedMenuItem: any;
  static menuOpen: boolean = true;

  constructor(
    private router: Router,
    private settingsService: SettingsService
  ) {}

  onOpenedChange() {
    AppComponent.menuOpen = this.sidenav.opened;
  }

  getSettingValue(data, settingKey) {
    for (let setting of data) {
      if (setting.settingKey === settingKey) {
        return setting.settingVal;
      }
    }
  }

  listSettingsCallback = response => {
    var data = JSON.parse(response["_body"]);
    this.titleText.nativeElement.innerHTML = this.getSettingValue(data, "title");
    this.copyright.nativeElement.innerHTML = this.getSettingValue(data, "copyright");
    this.email.nativeElement.innerHTML = this.getSettingValue(data, "email");
  };

  ngOnInit() {
    let currentUrl = this.router.url;
    this.settingsService.list(this.listSettingsCallback, null);
  }
  title = "app";
  showhideCondrols: boolean = false;

  hideShowControls(): boolean {
    let currentUrl = this.router.url;

    if (this.router.url === "/home" || this.router.url === '/awiscSearch') {
      this.showhideCondrols = false;
      this.sidenav.close();
    } else {
      this.showhideCondrols = true;
    }
    return this.showhideCondrols;
  }

  clicked(object) {
    AppComponent.selectedMenuItem = object;
  }

  get selectedMenuItem() {
    return AppComponent.selectedMenuItem;
  }

  navigateToLogin() {
    this.router.navigateByUrl("/login");
  }

  ngAfterViewInit() {}
}
