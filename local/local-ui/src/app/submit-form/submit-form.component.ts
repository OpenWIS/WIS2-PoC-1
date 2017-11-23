import { Component, OnInit, ElementRef } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { ViewChild } from '@angular/core';



@Component({
  selector: 'app-submit-form',
  templateUrl: './submit-form.component.html',
  styleUrls: ['./submit-form.component.css']
})
export class SubmitFormComponent implements OnInit {
  
  metadataForm: FormGroup;
  
  @ViewChild('readingTpSel') private readingTpSel: ElementRef;

countries = [
  {value: 'mord', viewValue: 'Mordor'},
  {value: 'gorg', viewValue: 'Gorgoroth'},
  {value: 'nurn', viewValue: 'Nurn'}
];

climates = [
  {value: 'pol', viewValue: 'Polar'},
  {value: 'tem', viewValue: 'Temperate'},
  {value: 'ari', viewValue: 'Arid'},
  {value: 'tro', viewValue: 'Tropical'},
  {value: 'tun', viewValue: 'Tundra'},
  {value: 'med', viewValue: 'Mediterranean'},
];


dataformats = [
  {value: 'csv', viewValue: 'CSV'},
  {value: 'xml', viewValue: 'XML'},
  {value: 'txt', viewValue: 'text'},
  {value: 'exl', viewValue: 'excel'},
];


readingTypes = [
  {wmocode: 'http://codes.wmo.int/common/unit/a', label: 'year'},
  {wmocode: 'http://codes.wmo.int/common/unit/mon', label: 'month'},
  {wmocode: 'http://codes.wmo.int/common/unit/d', label: 'day'},
  {wmocode: 'http://codes.wmo.int/common/unit/h', label: 'hour'},
  {wmocode: 'http://codes.wmo.int/common/unit/min', label: ' minute (time)'},
  {wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-2-0', label: 'Wind direction (from which blowing)'},
  {wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-2-1', label: 'Wind speed'},
  {wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-0-21', label: 'Apparent temperature'},
  {wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-0-6', label: 'Dewpoint temperature'},
  {wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-3-11', label: 'Altimeter setting'},

];



readingTypesSel = [
  {value: '', viewValue: ''}
];


setTest(){
 console.log("It works");

}

addSelected(){
  // todo 
  // get selected values
  // remove them from the available 
//  console.log("adding: "+  this.readingTpSel.nativeElement.selectedOptions);
}



   constructor() {}
 
  ngOnInit() {
    this.metadataForm = new FormGroup({
      state: new FormControl('', [Validators.required, Validators.minLength(2)]),
      divisions: new FormControl('', [Validators.required, Validators.minLength(2)]),
      areaCodes: new FormControl('', [Validators.required, Validators.minLength(2)]),
      references: new FormControl('', [Validators.required, Validators.minLength(2)]),
      datasetName: new FormControl('', [Validators.required, Validators.minLength(2)]),
      description: new FormControl('', [Validators.required, Validators.minLength(2)]),
      license: new FormControl('', [Validators.required, Validators.minLength(2)]),
      // stations: new FormControl('', [Validators.required, Validators.minLength(2)]),
      relativeUrl: new FormControl('',  ),
      jsonLd: new FormControl('',  ) ,
      downloadLink: new FormControl('', []),
      awsQueue: new FormControl('', []),

    });
  }
  onSubmit() {
    console.log("submit");
  }


 

  getErrorMessage() {
    return this.metadataForm.hasError('required') ? 'You must enter a value' :
        this.metadataForm.hasError('email') ? 'Not a valid email' :'not valid Mail';
  }
}
