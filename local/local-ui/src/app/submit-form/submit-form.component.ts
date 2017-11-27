import { Component, OnInit, ElementRef, Input } from '@angular/core';
import { FormControl, Validators, FormGroup, FormArray } from '@angular/forms';
import { ViewChild } from '@angular/core';
import { MatInput, MatRadioGroup, MatChipInputEvent } from '@angular/material';
import { MatCheckbox } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/operator/map';
import { ENTER, COMMA } from '@angular/cdk/keycodes';




@Component({
  selector: 'app-submit-form',
  templateUrl: './submit-form.component.html',
  styleUrls: ['./submit-form.component.css']
})
export class SubmitFormComponent implements OnInit {

  rdshDissEnabled = false;
  metadataForm: FormGroup;
  paramsObj: Object;
  //autocomplete
  stateCtrl: FormControl;
  filteredStates: Observable<any[]>;
  //Chips 
  visible: boolean = true;
  selectable: boolean = true;
  removable: boolean = true;
  addOnBlur: boolean = true;

  separatorKeysCodes = [ENTER, COMMA];


  @ViewChild('datasetName') private text1: MatInput;
  @ViewChild('rdshDis') private rdshCb: MatCheckbox;
  @ViewChild('rdshOptions') private rdshOptions: MatRadioGroup;


  dataSet =
    {
      id: '',
      name: '',
      description: '',
      state: '',
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
      periodFrom: Date,
      periodTo: Date
    };


  dataSetList = [
    {
      id: '10', name: 'Athens', description: 'Rain volume in Athens', state: 'Athens', country: 'gr', dataformat: 'csv', climate: 'med', divisions: 'Attica Athens', areaCodes: '12313, 12543,12432', references: 'https://en.wikipedia.org/wiki/Athens', license: ' https://creativecommons.org/licenses/by/4.0/', relativeUrl: 'ath/weth/', filenameprefix: 'ath::', jsonLd: '', downloadLink: 'aLink.com', awsQueue: '/arn:aws:sns:us-ATH-1:11784:SEDataQueue', rdshDissEnabled: 'false',
      periodFrom: '1984/01/01', periodTo: 'Now'
    },
    { id: '30', name: 'Thesaloniki wind', description: 'Wind power in Thesaloniki', state: 'Thesaloniki', country: 'gr', dataformat: 'txt', climate: 'med', divisions: 'text', areaCodes: '', references: '', license: '', relativeUrl: '/daddada/uril', filenameprefix: 'Thesx', jsonLd: '', downloadLink: '', awsQueue: '/arn:aws:sns:us-east-1:11784:SEDataQueue', rdshDissEnabled: '', periodFrom: '1984/01/01', periodTo: 'Now' },
    { id: '12', name: 'Iraklio sun', description: 'Sun strength in Iraklio', state: '', country: 'gr', dataformat: 'csv', climate: 'med', divisions: '', areaCodes: '', references: '', license: '', relativeUrl: '', filenameprefix: '', jsonLd: '', downloadLink: '', awsQueue: '', rdshDissEnabled: '', periodFrom: '1984/01/01', periodTo: 'Now' }
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
    { value: 'txt', viewValue: 'Text' },
    { value: 'exl', viewValue: 'Excel' },
  ];


  measurements: any = [
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



  addSelected() {
    // todo 
    // get selected values
    // remove them from the available 
    //  console.log("adding: "+  this.readingTpSel.nativeElement.selectedOptions);
  }

  editMode: boolean = true;
  selectedCountry: string = 'gr';

  constructor(private activatedRoute: ActivatedRoute,
    private router: Router) { }


  ngOnInit() {

    this.activatedRoute.queryParamMap.subscribe(params => {
      this.paramsObj = { ...params.keys, ...params };
    });
    let id = this.paramsObj['params']['id'];
    let dataset = this.dataSetList.find(i => i.id === id);


    this.stateCtrl = new FormControl();

    if (dataset == null) {
      // create new 
      this.metadataForm = new FormGroup({
        state: new FormControl('', [Validators.required, Validators.minLength(2)]),
        divisions: new FormControl('', [Validators.required, Validators.minLength(2)]),
        areaCodes: new FormControl('', [Validators.required, Validators.minLength(2)]),
        references: new FormControl('', [Validators.required, Validators.minLength(2)]),
        datasetName: new FormControl('', [Validators.required, Validators.minLength(2)]),
        description: new FormControl('', [Validators.required, Validators.minLength(2)]),
        license: new FormControl('', [Validators.required, Validators.minLength(2)]),
        relativeUrl: new FormControl('', ),
        filenameprefix: new FormControl('', ),
        jsonLd: new FormControl('', ),
        downloadLink: new FormControl('', []),
        awsQueue: new FormControl('', []),
        rdshDissEnabled: new FormControl(false, []),
        periodFrom: new FormControl('', [Validators.required, Validators.minLength(2)]),
        periodTo: new FormControl('', [Validators.required, Validators.minLength(2)]),
        countryCB: new FormControl(''),
        climate: new FormControl(''),
        dataformat: new FormControl(''),
        datasetImage: new FormControl('')

      });


    } else {
      this.metadataForm = new FormGroup({
        datasetName: new FormControl(dataset.name, [Validators.required, Validators.minLength(2)]),

        countryCB: new FormControl(dataset.country),
        climate: new FormControl(dataset.climate),
        state: new FormControl(dataset.state, [Validators.required, Validators.minLength(2)]),
        divisions: new FormControl(dataset.divisions, [Validators.required, Validators.minLength(2)]),
        areaCodes: new FormControl(dataset.areaCodes, [Validators.required, Validators.minLength(2)]),
        references: new FormControl(dataset.references, [Validators.required, Validators.minLength(2)]),
        description: new FormControl(dataset.description, [Validators.required, Validators.minLength(2)]),
        license: new FormControl(dataset.license, [Validators.required, Validators.minLength(2)]),
        relativeUrl: new FormControl(dataset.relativeUrl, [Validators.required, Validators.minLength(2)]),
        filenameprefix: new FormControl(dataset.filenameprefix, [Validators.required, Validators.minLength(2)]),
        jsonLd: new FormControl(dataset.jsonLd, [Validators.required, Validators.minLength(2)]),
        downloadLink: new FormControl(dataset.downloadLink, [Validators.required, Validators.minLength(2)]),
        awsQueue: new FormControl(dataset.awsQueue, [Validators.required, Validators.minLength(2)]),
        rdshDissEnabled: new FormControl(dataset.rdshDissEnabled, [Validators.required, Validators.minLength(2)]),
        dataformat: new FormControl(dataset.dataformat),

        periodFrom: new FormControl(new Date(dataset.periodFrom), [Validators.required, Validators.minLength(2)]),
        periodTo: new FormControl(new Date(), [Validators.required, Validators.minLength(2)]),
        datasetImage: new FormControl('')

      });
    }


    this.filteredStates = this.stateCtrl.valueChanges.startWith(null)
      .map(measurement => measurement ? this.filterStates(measurement) : this.measurements.slice());
  }

  filterStates(name: string) {
    let out = this.measurements.filter(measurement => measurement.label.toLowerCase().indexOf(name.toLowerCase()) === 0);

    return out;
  }


  rdshAdjust(srcElement: HTMLInputElement) {
    console.log("CBval   " + this.rdshCb.checked);
  }

  add(event: MatChipInputEvent): void {
    let input = event.input;
    let value = event.value;

    // Add our fruit
    if ((value || '').trim()) {
      this.measurements.push({ label: value.trim() });
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  remove(measurement: any): void {
    let index = this.measurements.indexOf(measurement);

    if (index >= 0) {
      this.measurements.splice(index, 1);
    }
  }


  onSubmit() {
    console.log("submit");
  }


  getErrorMessage() {
    return this.metadataForm.hasError('required') ? 'You must enter a value' :
      this.metadataForm.hasError('email') ? 'Not a valid email' : 'not valid Mail';
  }
}
