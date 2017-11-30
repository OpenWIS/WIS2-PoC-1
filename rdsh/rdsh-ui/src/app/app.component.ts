import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterModule, Routes, Router } from '@angular/router';
import { MatSidenav } from '@angular/material';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],

})



export class AppComponent implements OnInit {

  @ViewChild('sidenav') private sidenav: MatSidenav;

  currentUrl: string;
  static selectedMenuItem: any;
  static menuOpen: boolean;

  constructor(private router: Router) { 
    
  }

  onOpenedChange(){
    AppComponent.menuOpen = this.sidenav.opened;
  }


  ngOnInit() {
    let currentUrl = this.router.url;
  }
  title = 'app';
  showhideCondrols: boolean = false;


  hideShowControls(): boolean {
    let currentUrl = this.router.url;
    
    
    if (this.router.url === '/home'){
      this.showhideCondrols = false;
      this.sidenav.close();
    }else{
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

  // get sidenav(){
  //   return AppComponent.sidenav;
  // }

  navigateToMenu(){
    this.router.navigateByUrl('/ldshs');
  }

  ngAfterViewInit(){
    // AppComponent.sn = this.sidenav;
  }
}

