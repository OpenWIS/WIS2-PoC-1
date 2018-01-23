import { Component, OnInit, ViewChild } from "@angular/core";
import { SubmitFormModule } from "./submit-form/submit-form.module";
import { RouterModule, Routes, Router } from "@angular/router";
import { MatSidenav } from "@angular/material";
import { DataService } from "./data.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  providers: [DataService]
  
})
export class AppComponent implements OnInit {
  constructor(private dataService: DataService, private router: Router) {}

  @ViewChild("sidenav") private sidenav: MatSidenav;

  currentUrl: string;
  footerTex: String;
  SystemTitleText: String;

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


  loadProperties(){
    this.dataService.getCall("/cxf/api/getSettings").then(result => {
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

navigateToHome() {
  this.router.navigateByUrl("/home");
}

  navigateToMenu() {
    this.router.navigateByUrl("/datasets");
  }
}
