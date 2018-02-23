import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterModule, Routes, Router } from '@angular/router';
import { MatSidenav } from '@angular/material';
import { environment } from '../environments/environment';
import { AuthService } from './services/auth.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],

})



export class AppComponent implements OnInit {

  @ViewChild('sidenav') private sidenav: MatSidenav;

  currentUrl: string;
  static selectedMenuItem: any;
  static menuOpen: boolean = true;

  constructor(private router: Router, private authService: AuthService) {

  }

  onOpenedChange() {
    AppComponent.menuOpen = this.sidenav.opened;
  }


  ngOnInit() {
    let currentUrl = this.router.url;
  }
  title = 'app';
  showhideCondrols: boolean = false;


  hideShowControls(): boolean {
    let currentUrl = this.router.url;


    if (this.router.url === '/home') {
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


  authenticate() {

    if (sessionStorage.getItem((environment.CONSTANTS.JWT_STORAGE_NAME))) {
      this.authService.logout();
      this.navigateToHome();
    } else {
      this.navigateToMenu();
    }
  }

  get selectedMenuItem() {
    return AppComponent.selectedMenuItem;
  }

  navigateToMenu() {
    this.router.navigateByUrl('/ldshs');
  }

  navigateToHome() {
    this.router.navigateByUrl("/home");
  }
}

