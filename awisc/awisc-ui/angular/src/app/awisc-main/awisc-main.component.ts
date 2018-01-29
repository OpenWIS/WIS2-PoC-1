import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-awisc-main',
  templateUrl: './awisc-main.component.html',
  styleUrls: ['./awisc-main.component.css']
})
export class AwiscMainComponent implements OnInit {

  searchResults: boolean = false;
  
  searchForm: FormGroup;

  constructor(private router: Router, private http: HttpClient) {  }

  onSubmit(){
    
  }


  ngOnInit() {
    this.searchForm = new FormGroup({
      datasetSearch: new FormControl("")
    });
    
  }

  ngAfterViewInit(){
    if(!AppComponent.menuOpen){
      document.getElementById('sitenav').click();
    }    
  }


  navigateToMenu(){
    this.router.navigateByUrl('/awiscSearch');
  }

  search() {
    // call search service 
    //...
    this.showSearchResults();
  }


  showSearchResults() {
    this.searchResults = true;
  }

}
