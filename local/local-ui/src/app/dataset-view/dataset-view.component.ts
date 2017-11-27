import { Component, OnInit, ElementRef, Input } from '@angular/core';
import { FormControl, Validators, FormGroup, FormArray } from '@angular/forms';
import { ViewChild } from '@angular/core';
import { MatInput, MatRadioGroup } from '@angular/material';
import { MatCheckbox } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';



@Component({
  selector: 'app-dataset-view',
  templateUrl: './dataset-view.component.html',
  styleUrls: ['./dataset-view.component.css']
})
export class DatasetViewComponent implements OnInit {

  rdshDissEnabled = false;
  metadataForm: FormGroup;
  paramsObj: Object;


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
      country: '',
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
      climate :'',
      periodFrom:'',
      periodTo:''

    };

  dataSetList = [
    { id: '10', name: 'Athens', description: 'Rain volume in Athens', state: 'Athens', dateFrom: '', dateTo: '', country: 'Greece', dataformat: 'CSV', climate:'Mediterranean', divisions: 'Attica Athens', areaCodes: '12313, 12543,12432', references: 'https://en.wikipedia.org/wiki/Athens', license: ' https://creativecommons.org/licenses/by/4.0/', relativeUrl: '', filenameprefix: '', jsonLd: '', downloadLink: 'https://en.wikipedia.org/wiki/Athens', awsQueue: '/arn:aws:sns:us-ATH-1:11784:SEDataQueue', rdshDissEnabled: 'false',
    periodFrom:'1984/01/01',   periodTo:'Now', map:'https://upload.wikimedia.org/wikipedia/commons/c/cf/Phyle_map-en.svg' },
    { id: '30', name: 'Thesaloniki wind', description: 'Wind power in Thesaloniki', state: 'Thesaloniki', dateFrom: '', dateTo: '', country: 'Greece', dataformat: '',climate:'Mediterranean', divisions: 'text', areaCodes: '', references: '', license: '', relativeUrl: '/daddada/uril', filenameprefix: 'Thesx', jsonLd: '', downloadLink: '', awsQueue: '/arn:aws:sns:us-east-1:11784:SEDataQueue', rdshDissEnabled: '',periodFrom:'1984/01/01',   periodTo:'Now', map:'https://upload.wikimedia.org/wikipedia/commons/a/aa/2011_Dimos_Thessalonikis.png' },
    { id: '12', name: 'Iraklio sun', description: 'Sun strength in Iraklio', state: '', dateFrom: '', dateTo: '', country: '', dataformat: '',climate:'Mediterranean', divisions: '', areaCodes: '', references: '', license: '', relativeUrl: '', filenameprefix: '', jsonLd: '', downloadLink: '', awsQueue: '', rdshDissEnabled: '',periodFrom:'1984/01/01',   periodTo:'Now', map :'https://upload.wikimedia.org/wikipedia/commons/3/3c/Nomos_Irakliou.png' }
  ];




  //  editMode:true;
  viewMode: boolean = true;

  constructor(private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {

    this.activatedRoute.queryParamMap.subscribe(params => {
      this.paramsObj = { ...params.keys, ...params };
    });
    let id = this.paramsObj['params']['id'];
    let dataset = this.dataSetList.find(i => i.id === id);
// console.log("id "+ id);
    this.dataSet = dataset;
    console.log(this.dataSet);

    if (dataset == null){
    //empty
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
     
      
      this.metadataForm = new FormGroup({
        datasetName: new FormControl( {value: dataset.name, disabled:this.viewMode}, []),
        country: new FormControl( {value: dataset.country, disabled:this.viewMode}, []),
        climate: new FormControl({value: dataset.climate, disabled:this.viewMode}, []),
        state: new FormControl({value: dataset.state,  disabled:this.viewMode}, []),
        divisions: new FormControl( {value: dataset.divisions, disabled:this.viewMode}, []),
        areaCodes: new FormControl( {value: dataset.areaCodes, disabled:this.viewMode}, []),
        references: new FormControl( {value: dataset.references , disabled:this.viewMode}, []),
        description: new FormControl({value: dataset.description, disabled:this.viewMode}, []),
        license: new FormControl( {value: dataset.license, disabled:this.viewMode}, []),
        relativeUrl: new FormControl({value: dataset.relativeUrl, disabled:this.viewMode},),
        filenameprefix: new FormControl({value: dataset.filenameprefix, disabled:this.viewMode},[] ),
        jsonLd: new FormControl( {value: dataset.jsonLd, disabled:this.viewMode},[] ),
        downloadLink: new FormControl({value: dataset.downloadLink, disabled:this.viewMode}, []),
        awsQueue: new FormControl({value: dataset.awsQueue, disabled:this.viewMode}, []),
        rdshDissEnabled: new FormControl({value: dataset.rdshDissEnabled, disabled:this.viewMode}, []),
        dataformat: new FormControl({value: dataset.dataformat, disabled:this.viewMode}, []),
        
        periodFrom: new FormControl({value: new Date(dataset.periodFrom), disabled:this.viewMode}, []),
        periodTo: new FormControl({value: new Date(), disabled:this.viewMode}, []),

    });
  }
  }

  rdshAdjust(srcElement: HTMLInputElement) {
    console.log("CBval   " + this.rdshCb.checked);
  }

  setReadonly() {
  }


  getErrorMessage() {
    return this.metadataForm.hasError('required') ? 'You must enter a value' :
      this.metadataForm.hasError('email') ? 'Not a valid email' : 'not valid Mail';
  }
 
}


  // countries = [
  //   { value: 'gr', viewValue: 'Greece' },
  //   { value: 'uk', viewValue: 'United Kingdom' },
  //   { value: 'cb', viewValue: 'Cuba' }
  // ];

  // climates = [
  //   { value: 'pol', viewValue: 'Polar' },
  //   { value: 'tem', viewValue: 'Temperate' },
  //   { value: 'ari', viewValue: 'Arid' },
  //   { value: 'tro', viewValue: 'Tropical' },
  //   { value: 'tun', viewValue: 'Tundra' },
  //   { value: 'med', viewValue: 'Mediterranean' },
  // ];


  // dataformats = [
  //   { value: 'csv', viewValue: 'CSV' },
  //   { value: 'xml', viewValue: 'XML' },
  //   { value: 'txt', viewValue: 'text' },
  //   { value: 'exl', viewValue: 'excel' },
  // ];


  // readingTypes = [
  //   { wmocode: 'http://codes.wmo.int/common/unit/a', label: 'year' },
  //   { wmocode: 'http://codes.wmo.int/common/unit/mon', label: 'month' },
  //   { wmocode: 'http://codes.wmo.int/common/unit/d', label: 'day' },
  //   { wmocode: 'http://codes.wmo.int/common/unit/h', label: 'hour' },
  //   { wmocode: 'http://codes.wmo.int/common/unit/min', label: ' minute (time)' },
  //   { wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-2-0', label: 'Wind direction (from which blowing)' },
  //   { wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-2-1', label: 'Wind speed' },
  //   { wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-0-21', label: 'Apparent temperature' },
  //   { wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-0-6', label: 'Dewpoint temperature' },
  //   { wmocode: 'http://codes.wmo.int/grib2/codeflag/4.2/0-3-11', label: 'Altimeter setting' },

  // ];
