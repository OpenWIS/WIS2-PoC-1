import { Component, OnInit, ViewChild } from "@angular/core";
import { SubmitFormModule } from "./submit-form/submit-form.module";
import { RouterModule, Routes, Router, NavigationEnd, ActivatedRoute } from "@angular/router";
import { MatSidenav } from "@angular/material";
import { DataService } from "./data.service";
import { LoginComponent } from "./login/login.component";
import { environment } from "../environments/environment";
import { Observable } from "rxjs/Observable";
import { AuthService } from "./services/auth.service";
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/mergeMap'
import { Title } from "@angular/platform-browser";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  providers: [DataService]

})
export class AppComponent implements OnInit {
  constructor(private dataService: DataService, private router: Router, private authService: AuthService,
    private activatedRoute: ActivatedRoute, private titleService: Title) {

      this.router.events.filter((event) => event instanceof NavigationEnd)
      .map(() => this.activatedRoute)
      .map((route) => {
        while (route.firstChild) route = route.firstChild;
        return route;
      }) .filter((route) => route.outlet === 'primary')
      .mergeMap((route) => route.data)
      .subscribe((event) => {
        titleService.setTitle(event["title"]);
      });
  }

  @ViewChild("sidenav") private sidenav: MatSidenav;

  currentUrl: string;
  footerTex: String;
  SystemTitleText: String;
  LoginStatus: String;

  static menuOpen: boolean = true;


  onOpenedChange() {
    AppComponent.menuOpen = this.sidenav.opened;
  }

  ngOnInit() {
    let currentUrl = this.router.url;
    this.loadProperties();
  }
  title = "app";
  showhideCondrols: boolean = false;

  hideShowControls(): boolean {
    let currentUrl = this.router.url;

    if (this.router.url === "/home") {
      this.showhideCondrols = false;
      this.sidenav.close();
    } else {
      this.showhideCondrols = true;
    }
    return this.showhideCondrols;
  }

  static selectedMenuItem: any;


  loadProperties() {
    this.dataService.getCall("getSettings").then(result => {
      this.footerTex = result.footerTxt;
      this.SystemTitleText = result.title;
    })
  }


  clicked(object) {
    AppComponent.selectedMenuItem = object;
  }

  get selectedMenuItem() {
    return AppComponent.selectedMenuItem;
  }

  authenticate() {

    if (sessionStorage.getItem((environment.CONSTANTS.JWT_STORAGE_NAME))) {
      this.authService.logout();
      this.LoginStatus = "Log out";
      this.navigateToHome();
    } else {
      this.navigateToMenu();
      this.LoginStatus = "Log in";
    }
  }

  navigateToHome() {
    this.router.navigateByUrl("/home");
  }

  navigateToMenu() {
    this.router.navigateByUrl("/datasets");
  }
}
