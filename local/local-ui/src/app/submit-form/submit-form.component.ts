import { Component, OnInit, ElementRef, Input } from '@angular/core';
import { FormControl, Validators, FormGroup, FormArray } from '@angular/forms';
import { ViewChild } from '@angular/core';
import { MatInput, MatRadioGroup } from '@angular/material';
import { MatCheckbox } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';



@Component({
  selector: 'app-submit-form',
  templateUrl: './submit-form.component.html',
  styleUrls: ['./submit-form.component.css']
})
export class SubmitFormComponent implements OnInit {
  // @Input() id: string;

  rdshDissEnabled = false;
  metadataForm: FormGroup;
  paramsObj: Object;


  // @ViewChild('sidenav') private sidenav: MatSidenav;
  @ViewChild('datasetName') private text1: MatInput;

  @ViewChild('rdshDis') private rdshCb: MatCheckbox;
  @ViewChild('rdshOptions') private rdshOptions: MatRadioGroup;


  dataSet =
    {
      id: '',
      name: '',
      description: '',
      state: '',
      dateFrom: '',
      dateTo: '',
      Country: '',
      dataformat: '',
      divisions: '',
      areaCodes: '',
      references: '',
      license: '',
      relativeUrl: '',
      filenameprefix: '',
      jsonLd: '',
      downloadLink: '',
      awsQueue: '',
      rdshDissEnabled: '',
    };
// emptyDataSet = { id: '', name: '', description: '', state: '', dateFrom: '', dateTo: '', Country: '', dataformat: '', divisions: '', areaCodes: '', references: '', license: '', relativeUrl: '', filenameprefix: '', jsonLd: '', downloadLink: '', awsQueue: '', rdshDissEnabled: '', };

  dataSetList = [
    { id: '10', name: 'Athens', description: 'Rain volume in Athens', state: 'Athens', dateFrom: '', dateTo: '', Country: 'Greece', dataformat: 'CSV', divisions: '', areaCodes: '', references: '', license: '', relativeUrl: '', filenameprefix: '', jsonLd: '', downloadLink: 'aLink.com', awsQueue: '/arn:aws:sns:us-east-1:11784:SEDataQueue', rdshDissEnabled: 'false', },
    { id: '30', name: 'Thesaloniki wind', description: 'Wind power in Thesaloniki', state: 'Thesaloniki', dateFrom: '', dateTo: '', Country: 'Greece', dataformat: '', divisions: 'text', areaCodes: '', references: '', license: '', relativeUrl: '/daddada/uril', filenameprefix: 'Thesx', jsonLd: '', downloadLink: '', awsQueue: '/arn:aws:sns:us-east-1:11784:SEDataQueue', rdshDissEnabled: '', },
    { id: '12', name: 'Iraklio sun', description: 'Sun strength in Iraklio', state: '', dateFrom: '', dateTo: '', Country: '', dataformat: '', divisions: '', areaCodes: '', references: '', license: '', relativeUrl: '', filenameprefix: '', jsonLd: '', downloadLink: '', awsQueue: '', rdshDissEnabled: '', }
  ];



  countries = [
    { value: 'gr', viewValue: 'Greece' },
    { value: 'uk', viewValue: 'United Kingdom' },
    { value: 'cb', viewValue: 'Cuba' }
  ];

  climates = [
    { value: 'pol', viewValue: 'Polar' },
    { value: 'tem', viewValue: 'Temperate' },
    { value: 'ari', viewValue: 'Arid' },
    { value: 'tro', viewValue: 'Tropical' },
    { value: 'tun', viewValue: 'Tundra' },
    { value: 'med', viewValue: 'Mediterranean' },
  ];


  dataformats = [
    { value: 'csv', viewValue: 'CSV' },
    { value: 'xml', viewValue: 'XML' },
    { value: 'txt', viewValue: 'text' },
    { value: 'exl', viewValue: 'excel' },
  ];


  readingTypes = [
    { wmocode: 'http://codes.wmo.int/common/unit/a', label: 'year' },
    { wmocode: 'http://codes.wmo.int/common/unit/mon', label: 'month' },
    { wmocode: 'http://codes.wmo.int/common/unit/d', label: 'day' },
    { wmocode: 'http://codes.wmo.int/common/unit/h', label: 'hour' },
    { wmocode: 'http://codes.wmo.int/common/unit/min', label: ' minute (time)' },
    { wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-2-0', label: 'Wind direction (from which blowing)' },
    { wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-2-1', label: 'Wind speed' },
    { wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-0-21', label: 'Apparent temperature' },
    { wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-0-6', label: 'Dewpoint temperature' },
    { wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-3-11', label: 'Altimeter setting' },

  ];



  readingTypesSel = [
    { value: '', viewValue: '' }
  ];


  setTest() {
    console.log("It works");

  }

  addSelected() {
    // todo 
    // get selected values
    // remove them from the available 
    //  console.log("adding: "+  this.readingTpSel.nativeElement.selectedOptions);
  }

  //  editMode:true;
  editMode: boolean = true;

  constructor(private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {

    this.activatedRoute.queryParamMap.subscribe(params => {
      this.paramsObj = { ...params.keys, ...params };
    });
    let id = this.paramsObj['params']['id'];
    let dataset = this.dataSetList.find(i => i.id === id);

    if (dataset == null){
      // create new 
      this.dataSet = this.emptyDataSet;
      this.metadataForm = new FormGroup({
        state: new FormControl('', [Validators.required, Validators.minLength(2)]),
        divisions: new FormControl('', [Validators.required, Validators.minLength(2)]),
        areaCodes: new FormControl('', [Validators.required, Validators.minLength(2)]),
        references: new FormControl('', [Validators.required, Validators.minLength(2)]),
        datasetName: new FormControl( '', [Validators.required, Validators.minLength(2)]),
        description: new FormControl('', [Validators.required, Validators.minLength(2)]),
        license: new FormControl( '' , [Validators.required, Validators.minLength(2)]),
        relativeUrl: new FormControl('', ),
        filenameprefix: new FormControl('', ),
        jsonLd: new FormControl('', ),
        downloadLink: new FormControl('', []),
        awsQueue: new FormControl('', []),
        rdshDissEnabled: new FormControl(false, []),
      });
    }else {
     //TODO edit mode
      this.dataSet = dataset;
      // this.editMode = true;
      // console.log( this.dataSet.name);
    
    this.metadataForm = new FormGroup({
      datasetName: new FormControl( this.dataSet.name, [Validators.required, Validators.minLength(2)]),
      state: new FormControl(this.dataSet.state , [Validators.required, Validators.minLength(2)]),
      divisions: new FormControl( this.dataSet.divisions, [Validators.required, Validators.minLength(2)]),
      areaCodes: new FormControl( this.dataSet.areaCodes, [Validators.required, Validators.minLength(2)]),
      references: new FormControl( this.dataSet.references , [Validators.required, Validators.minLength(2)]),
      description: new FormControl(this.dataSet.description, [Validators.required, Validators.minLength(2)]),
      license: new FormControl( this.dataSet.license, [Validators.required, Validators.minLength(2)]),
      relativeUrl: new FormControl(this.dataSet.relativeUrl),
      filenameprefix: new FormControl(this.dataSet.filenameprefix ),
      jsonLd: new FormControl( this.dataSet.jsonLd ),
      downloadLink: new FormControl(this.dataSet.downloadLink, []),
      awsQueue: new FormControl(this.dataSet.awsQueue, []),
      rdshDissEnabled: new FormControl(this.dataSet.rdshDissEnabled, []),
    });
  }
  }

  rdshAdjust(srcElement: HTMLInputElement) {
    console.log("CBval   " + this.rdshCb.checked);
  }



  // first: new FormControl({value: 'Nancy', disabled: true}, Validators.required),



  onSubmit() {
    console.log("submit");

  }

  setReadonly() {

  }


  getErrorMessage() {
    return this.metadataForm.hasError('required') ? 'You must enter a value' :
      this.metadataForm.hasError('email') ? 'Not a valid email' : 'not valid Mail';
  }
}
