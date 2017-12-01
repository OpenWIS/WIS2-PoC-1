import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-awisc-main',
  templateUrl: './awisc-main.component.html',
  styleUrls: ['./awisc-main.component.css']
})
export class AwiscMainComponent implements OnInit {

  searchForm: FormGroup;
  
  constructor() {  }


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


  Search() {}


}
