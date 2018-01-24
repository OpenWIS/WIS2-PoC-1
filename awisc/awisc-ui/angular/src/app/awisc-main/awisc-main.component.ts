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

    // This is just for demo! All REST calls should be routed via a Service and the URL prefix
    // of the back-end be a Constant.
    // Normal call.
    //this.http.get("http://localhost:8181/cxf/api/hello-world").subscribe(
	this.http.get("http://10.100.1.95:8181/cxf/api/hello-world").subscribe(
        onNext => {
          console.log("OK1: ", onNext);
        },
        onError => {
          console.error("Error1: ", onError);
        }
    )

    // Error call.
    // this.http.get("http://localhost:8181/cxf/api/hello-world-error").subscribe(
    //     onNext => {
    //       console.log("OK2: ", onNext);
    //     },
    //     onError => {
    //       console.error("Error2: ", onError);
    //     }
    // )

    // Demo for QueryDSL + MapStruct.
    this.http.get("http://localhost:8181/cxf/api/hello-world-qdsl").subscribe(
        onNext => {
          console.log("OK3: ", onNext);
        },
        onError => {
          console.error("Error3: ", onError);
        }
    )

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
