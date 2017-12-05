import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { FormGroup, FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import "rxjs/add/operator/startWith";
import "rxjs/add/operator/map";
import { ENTER, COMMA } from "@angular/cdk/keycodes";
import { MatChipInputEvent } from '@angular/material';


@Component({
  selector: 'app-awisc-search',
  templateUrl: './awisc-search.component.html',
  styleUrls: ['./awisc-search.component.css']
})


export class AwiscSearchComponent implements OnInit {
  panelOpenState: boolean = false;
  searchResults: boolean = false;
  filteredStates: Observable<any[]>;
  removable: boolean = true;
  
  

  stateCtrl: FormControl;

  searchForm: FormGroup;

  constructor(private router: Router) {
    AppComponent.selectedMenuItem = 'awiscSearch';
  }

  ngOnInit() {

    this.searchResults = false;
    this.stateCtrl = new FormControl();

    this.searchForm = new FormGroup({
      datasetSearch: new FormControl(""),
    });


    this.filteredStates = this.stateCtrl.valueChanges
    .startWith(null)
    .map(
      wmoCode =>
      wmoCode
          ? this.filterStates(wmoCode)
          : this.wmoCodes.slice()
    );
  }


  filterStates(name: string) {
    let out = this.wmoCodes.filter(
      wmoCode =>
      wmoCode.label.toLowerCase().indexOf(name.toLowerCase()) === 0
    );

    return out;
  }



  add(event: MatChipInputEvent): void {
    let input = event.input;
    let value = event.value;

    // Add our fruit
    if ((value || "").trim()) {
      this.wmoCodes.push({ label: value.trim() });
    }

    // Reset the input value
    if (input) {
      input.value = "";
    }
  }

  remove(measurement: any): void {
    let index = this.wmoCodes.indexOf(measurement);

    if (index >= 0) {
      this.wmoCodes.splice(index, 1);
    }
  }





  search() {
    // call search service 
    //...
    this.showSearchResults();
  }


  showSearchResults() {
    this.searchResults = true;
  }




  // ngAfterViewInit() {
  //   if (!AppComponent.menuOpen) {
  //     document.getElementById('sitenav').click();
  //   }
  // }



  wmoCodes: any = [
    { label: "Wind direction (from which blowing)", wmocode: "grib2/codeflag/4.2/0-2-0" },
    { label: "Wind speed", wmocode: "grib2/codeflag/4.2/0-2-1" },
    { label: "Wind speed", wmocode: "grib2/codeflag/4.2/3-1-19" },
    { label: "Wind chill factor", wmocode: "grib2/codeflag/4.2/0-0-13" },
    { label: "Maximum wind level", wmocode: "bufr4/codeflag/0-08-001/4" },
    { label: "Maximum wind level", wmocode: "bufr4/codeflag/0-08-086/4" },
    { label: "Rain", wmocode: "bufr4/codeflag/0-20-004/16" },
    { label: "Rain", wmocode: "bufr4/codeflag/0-20-004/6" },
    { label: "Rain", wmocode: "bufr4/codeflag/0-20-005/6" },
    { label: "Precipitation of rain", wmocode: "306/4678/RA" },
    { label: "Precipitation of rain", wmocode: "bufr4/codeflag/0-20-003/280" },
    { label: "Categorical freezing rain", wmocode: "grib2/codeflag/4.2/0-1-34" },
    { label: "Reflectivity of rain", wmocode: "grib2/codeflag/4.2/0-15-12" },
    { label: "Apparent temperature", wmocode: "grib2/codeflag/4.2/0-0-21" },
    { label: "Dewpoint temperature", wmocode: "bufr4/b/12/103" },
    { label: "Dewpoint temperature", wmocode: "bufr4/b/12/024" },
    { label: "Dewpoint temperature", wmocode: "grib2/codeflag/4.2/0-0-6" },
    { label: "Highest daily mean temperature", wmocode: "bufr4/b/12/152" },
    { label: "Minimum temperature", wmocode: "grib2/codeflag/4.2/0-0-5" },
    { label: "Minimum temperature", wmocode: "bufr4/codeflag/0-08-050/8" },
  ];

// delete:

  windCodes: any = [
    { label: "Wind direction (from which blowing)", wmocode: "grib2/codeflag/4.2/0-2-0" },
    { label: "Wind speed", wmocode: "grib2/codeflag/4.2/0-2-1" },
    { label: "Wind speed", wmocode: "grib2/codeflag/4.2/3-1-19" },
    { label: "Wind chill factor", wmocode: "grib2/codeflag/4.2/0-0-13" },
    { label: "Maximum wind level", wmocode: "bufr4/codeflag/0-08-001/4" },
    { label: "Maximum wind level", wmocode: "bufr4/codeflag/0-08-086/4" },
  ];


  rainCodes: any = [
    { label: "Rain", wmocode: "bufr4/codeflag/0-20-004/16" },
    { label: "Rain", wmocode: "bufr4/codeflag/0-20-004/6" },
    { label: "Rain", wmocode: "bufr4/codeflag/0-20-005/6" },
    { label: "Precipitation of rain", wmocode: "306/4678/RA" },
    { label: "Precipitation of rain", wmocode: "bufr4/codeflag/0-20-003/280" },
    { label: "Categorical freezing rain", wmocode: "grib2/codeflag/4.2/0-1-34" },
    { label: "Reflectivity of rain", wmocode: "grib2/codeflag/4.2/0-15-12" },
  ];

  temperatureCodes: any = [
    { label: "Apparent temperature", wmocode: "grib2/codeflag/4.2/0-0-21" },
    { label: "Dewpoint temperature", wmocode: "bufr4/b/12/103" },
    { label: "Dewpoint temperature", wmocode: "bufr4/b/12/024" },
    { label: "Dewpoint temperature", wmocode: "grib2/codeflag/4.2/0-0-6" },
    { label: "Highest daily mean temperature", wmocode: "bufr4/b/12/152" },
    { label: "Minimum temperature", wmocode: "grib2/codeflag/4.2/0-0-5" },
    { label: "Minimum temperature", wmocode: "bufr4/codeflag/0-08-050/8" },
  ];




  navigateToHome() {
    this.router.navigateByUrl('/home');
  }


}
