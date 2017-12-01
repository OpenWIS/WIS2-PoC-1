import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-awisc-search',
  templateUrl: './awisc-search.component.html',
  styleUrls: ['./awisc-search.component.css']
})


export class AwiscSearchComponent implements OnInit {

  constructor(private router: Router) {
    AppComponent.selectedMenuItem = 'awiscSearch';
  }

  ngOnInit() {
  }



  ngAfterViewInit() {
    if (!AppComponent.menuOpen) {
      document.getElementById('sitenav').click();
    }
  }


}
