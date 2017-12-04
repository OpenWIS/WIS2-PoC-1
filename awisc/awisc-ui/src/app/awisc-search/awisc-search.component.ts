import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { FormGroup, FormControl } from '@angular/forms';



@Component({
  selector: 'app-awisc-search',
  templateUrl: './awisc-search.component.html',
  styleUrls: ['./awisc-search.component.css']
})


export class AwiscSearchComponent implements OnInit {
  panelOpenState: boolean = false;

  stateCtrl: FormControl;

  searchForm: FormGroup;

  constructor(private router: Router) {
    AppComponent.selectedMenuItem = 'awiscSearch';
  }

  ngOnInit() {
    
    this.stateCtrl = new FormControl();
    
    this.searchForm = new FormGroup({
      datasetSearch: new FormControl(""),
    });
  }



  // ngAfterViewInit() {
  //   if (!AppComponent.menuOpen) {
  //     document.getElementById('sitenav').click();
  //   }
  // }

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





}
