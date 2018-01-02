import { Component, OnInit, ViewChild } from "@angular/core";
import { SubmitFormModule } from "./submit-form/submit-form.module";
import { RouterModule, Routes, Router } from "@angular/router";
import { MatSidenav } from "@angular/material";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class AppComponent implements OnInit {
  constructor(private router: Router) {}

  @ViewChild("sidenav") private sidenav: MatSidenav;

  currentUrl: string;
  static menuOpen: boolean = true;

  onOpenedChange() {
    AppComponent.menuOpen = this.sidenav.opened;
  }

  ngOnInit() {
    let currentUrl = this.router.url;
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

  clicked(object) {
    AppComponent.selectedMenuItem = object;
  }

  get selectedMenuItem() {
    return AppComponent.selectedMenuItem;
  }

  navigateToMenu() {
    this.router.navigateByUrl("/datasets");
  }
}
