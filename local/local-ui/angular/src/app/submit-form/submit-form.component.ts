import { Component, OnInit, ElementRef, Input } from "@angular/core";
import { FormControl, Validators, FormGroup, FormArray } from "@angular/forms";
import { ViewChild } from "@angular/core";
import { MatInput, MatRadioGroup, MatChipInputEvent } from "@angular/material";
import { MatCheckbox } from "@angular/material";
import { ActivatedRoute, Router } from "@angular/router";
import { Observable } from "rxjs/Observable";
import "rxjs/add/operator/startWith";
import "rxjs/add/operator/map";
import { ENTER, COMMA } from "@angular/cdk/keycodes";
import { DataService } from "../data.service";

@Component({
  selector: "app-submit-form",
  templateUrl: "./submit-form.component.html",
  styleUrls: ["./submit-form.component.css"],
  providers:[DataService]
})
export class SubmitFormComponent implements OnInit {
  displayFn: any;
  measurementUnit: any;
  country: any;
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

  postObject: any = null
  

  @ViewChild("datasetName") private text1: MatInput;
  @ViewChild("rdshDis") private rdshCb: MatCheckbox;
  @ViewChild("rdshOptions") private rdshOptions: MatRadioGroup;

  dataSet = {
    id: "",
    name: "",
    description: "",
    state: "",
    city: "",
    Country: "",
    dataformat: "",
    latitude: "",
    longitude: "",
    elevation: "",
    references: "",
    license: "",
    relativeUrl: "",
    filenameprefix: "",
    jsonLd: "",
    imageUrl: "",
    downloadLink: "",
    awsQueue: "",
    rdshDissEnabled: "",
    periodFrom: Date,
    periodTo: Date
  };

  dataSetList = [
    {
      id: "10",
      name: "Athens",
      description: "Rain volume in Athens",
      state: "Attica",
      city: "Athens",
      latitude: "",
      longitude: "",
      elevation: "",
      country: "gr",
      dataformat: "csv",
      climate: "med",
      divisions: "Attica Athens",
      license: " https://creativecommons.org/licenses/by/4.0/",
      relativeUrl: "attica/athens",
      filenameprefix: "ath",
      jsonLd: "",
      downloadLink: "http://localhost/attica/athens/ath",
      awsQueue: "/arn:aws:sns:us-ATH-1:11784:SEDataQueue",
      rdshDissEnabled: "false",
      periodFrom: "1984/01/01",
      periodTo: "Now",
      measurementUnit: "Day",
      imageUrl: "",
         // measurementUnit: "http://codes.wmo.int/common/unit/d"
    },
    {
      id: "30",
      name: "Thesaloniki wind",
      description: "Wind power in Thesaloniki",
      state: "Thesaloniki",
      city: "Thesaloniki",
      latitude: "",
      longitude: "",
      elevation: "",
      country: "gr",
      dataformat: "txt",
      climate: "med",
      divisions: "text",
      areaCodes: "",
      license: "",
      relativeUrl: "/daddada/uril",
      filenameprefix: "Thesx",
      jsonLd: "",
      downloadLink: "",
      awsQueue: "/arn:aws:sns:us-east-1:11784:SEDataQueue",
      rdshDissEnabled: "",
      periodFrom: "1984/01/01",
      periodTo: "Now",
      measurementUnit:"Month",
      imageUrl: "",
    },
    {
      id: "12",
      name: "Iraklio sun",
      description: "Sun strength in Iraklio",
      state: "Iraklio",
      city: "Iraklio",
      latitude: "",
      longitude: "",
      elevation: "",
      country: "gr",
      dataformat: "csv",
      climate: "med",
      divisions: "",
      areaCodes: "",
      license: "",
      relativeUrl: "",
      filenameprefix: "",
      jsonLd: "",
      downloadLink: "",
      awsQueue: "",
      rdshDissEnabled: "",
      periodFrom: "1984/01/01",
      periodTo: "Now",
      measurementUnit:"Hour",
      imageUrl: "",
    }
  ];

  countries = [
    { value: "gr", viewValue: "Greece" },
    { value: "uk", viewValue: "United Kingdom" },
    { value: "cb", viewValue: "Cuba" }
  ];

  climates = [
    { value: "pol", viewValue: "Polar" },
    { value: "tem", viewValue: "Temperate" },
    { value: "ari", viewValue: "Arid" },
    { value: "tro", viewValue: "Tropical" },
    { value: "tun", viewValue: "Tundra" },
    { value: "med", viewValue: "Mediterranean" }
  ];

  dataformats = [
    { value: "csv", viewValue: "CSV" },
    { value: "xml", viewValue: "XML" },
    { value: "txt", viewValue: "Text" },
    { value: "exl", viewValue: "Excel" }
  ];


  measurementUnits: any = [
    // { wmocode: "http://codes.wmo.int/common/unit/a", label: "year" },
    { wmocode: "http://codes.wmo.int/common/unit/mon", label: "Month" },
    { wmocode: "http://codes.wmo.int/common/unit/d", label: "Day" },
    { wmocode: "http://codes.wmo.int/common/unit/h", label: "Hour" },
    { wmocode: "http://codes.wmo.int/common/unit/min",label: "Minute (time)"},
  ];

  measurements: any = [
    // { wmocode: "http://codes.wmo.int/common/unit/a", label: "year" },
    // { wmocode: "http://codes.wmo.int/common/unit/mon", label: "month" },
    // { wmocode: "http://codes.wmo.int/common/unit/d", label: "day" },
    // { wmocode: "http://codes.wmo.int/common/unit/h", label: "hour" },
    // { wmocode: "http://codes.wmo.int/common/unit/min",label: " minute (time)"},
    {
      wmocode: "http://codes.wmo.int/grib2/codeflag/4.2/0-2-0",
      label: "Wind direction (from which blowing)"
    },
    {
      wmocode: "http://codes.wmo.int/grib2/codeflag/4.2/0-2-1",
      label: "Wind speed"
    },
    {
      wmocode: "http://codes.wmo.int/grib2/codeflag/4.2/0-0-21",
      label: "Apparent temperature"
    },
    {
      wmocode: "http://codes.wmo.int/grib2/codeflag/4.2/0-0-6",
      label: "Dewpoint temperature"
    },
    {
      wmocode: "http://codes.wmo.int/grib2/codeflag/4.2/0-3-11",
      label: "Altimeter setting"
    }
  ];

  addSelected() {
    // todo
    // get selected values
    // remove them from the available
    //  console.log("adding: "+  this.readingTpSel.nativeElement.selectedOptions);
    console.log("called.")
    return true;
  }

  editMode: boolean = true;
  // selectedCountry: string = "gr";
  
  
  SERVICE_URL ='/cxf/api/verification'
  // SERVICE_URL ='/cxf/api/testget'

  constructor(private dataService: DataService, private activatedRoute: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(params => {
      this.paramsObj = { ...params.keys, ...params };
    });
    let id = this.paramsObj["params"]["id"];
    let dataset = this.dataSetList.find(i => i.id === id);

    this.stateCtrl = new FormControl();

    if (dataset == null) {
      // create new
      this.metadataForm = new FormGroup({
        state: new FormControl("", [Validators.required]),
        city: new FormControl(""),
        latitude: new FormControl("", [Validators.required]),
        longitude: new FormControl("", [Validators.required]),
        elevation: new FormControl("", [Validators.required]),
        datasetName: new FormControl("", [
          Validators.required,
          Validators.minLength(2)
        ]),
        description: new FormControl("", [
          Validators.required,
          Validators.minLength(2)
        ]),
        license: new FormControl("", [
          Validators.required,
          Validators.minLength(2)
        ]),
        relativeUrl: new FormControl(""),
        filenameprefix: new FormControl(""),
        jsonLd: new FormControl(""),
        downloadLink: new FormControl("", []),
        awsQueue: new FormControl("", []),
        rdshDissEnabled: new FormControl(false, []),
        periodFrom: new FormControl("", [
          Validators.required,
          Validators.minLength(2)
        ]),
        periodTo: new FormControl("", [
          Validators.required,
          Validators.minLength(2)
        ]),
        measurementUnitsCb:  new FormControl(""),
        countryCB: new FormControl(""),
        climate: new FormControl(""),
        dataformat: new FormControl(""),
        datasetImage: new FormControl("")
      });
    } else {
      this.metadataForm = new FormGroup({
        datasetName: new FormControl(dataset.name, [
          Validators.required,
          Validators.minLength(2)
        ]),
        measurementUnitsCb: new FormControl(dataset.measurementUnit),
        countryCB: new FormControl(dataset.country),
        climate: new FormControl(dataset.climate),
        state: new FormControl(dataset.state, [Validators.required]),
        city: new FormControl(dataset.city),
        latitude: new FormControl(dataset.latitude, [Validators.required]),
        longitude: new FormControl(dataset.longitude, [Validators.required]),
        elevation: new FormControl(dataset.elevation, [Validators.required]),
        description: new FormControl(dataset.description, [
          Validators.required,
          Validators.minLength(2)
        ]),
        license: new FormControl(dataset.license, [
          Validators.required,
          Validators.minLength(2)
        ]),
        relativeUrl: new FormControl(dataset.relativeUrl, [
          Validators.required,
          Validators.minLength(2)
        ]),
        filenameprefix: new FormControl(dataset.filenameprefix, [
          Validators.required,
          Validators.minLength(2)
        ]),
        jsonLd: new FormControl(dataset.jsonLd, [
          Validators.required,
          Validators.minLength(2)
        ]),
        downloadLink: new FormControl(dataset.downloadLink, [
          Validators.required,
          Validators.minLength(2)
        ]),
        awsQueue: new FormControl(dataset.awsQueue, [
          Validators.required,
          Validators.minLength(2)
        ]),
        rdshDissEnabled: new FormControl(dataset.rdshDissEnabled, [
          Validators.required,
          Validators.minLength(2)
        ]),
        dataformat: new FormControl(dataset.dataformat),

        periodFrom: new FormControl(new Date(dataset.periodFrom), [
          Validators.required,
          Validators.minLength(2)
        ]),
        periodTo: new FormControl(new Date(), [
          Validators.required,
          Validators.minLength(2)
        ]),
        datasetImage: new FormControl("")
      });
    }

    this.filteredStates = this.stateCtrl.valueChanges
      .startWith(null)
      .map(
        measurement =>
          measurement
            ? this.filterStates(measurement)
            : this.measurements.slice()
      );
  }

  filterStates(name: string) {
    let out = this.measurements.filter(
      measurement =>
        measurement.label.toLowerCase().indexOf(name.toLowerCase()) === 0
    );

    return out;
  }

  rdshAdjust(srcElement: HTMLInputElement) {
    console.log("CBval   " + this.rdshCb.checked);
  }

  add(event: MatChipInputEvent): void {
    let input = event.input;
    let value = event.value;

    // Add our fruit
    if ((value || "").trim()) {
      this.measurements.push({ label: value.trim() });
    }

    // Reset the input value
    if (input) {
      input.value = "";
    }
  }

  remove(measurement: any): void {
    let index = this.measurements.indexOf(measurement);

    if (index >= 0) {
      this.measurements.splice(index, 1);
    }
  }

  onSubmit() {
    console.log("Submit");
    var messageObject = new Object();
    
    // test 2 id =10 athens.
    let dataset = this.dataSetList.find(i => i.id === '10');
    
    messageObject['message'] ; // todo object to send...
    
    messageObject['id'] = dataset.id;
		messageObject['name'] = dataset.name;
		messageObject['description'] = dataset.description;
		messageObject['periodFrom'] = dataset.periodFrom;
		messageObject['periodTo'] = dataset.periodTo;
		messageObject['license'] = dataset.license;
		messageObject['imageUrl'] = dataset.imageUrl;
		messageObject['measurementUnit'] = dataset.measurementUnit;
		 messageObject['wmoCodes'] = "dataset.wmoCodes TODO FIXME"
		messageObject['country'] = dataset.country;
		messageObject['state'] = dataset.state;
		messageObject['city'] = dataset.city;
		messageObject['latitude'] = dataset.latitude;
		messageObject['longitude'] =  dataset.longitude;
		messageObject['elevation'] =  dataset.elevation;
		messageObject['relativeUrl'] =  dataset.relativeUrl;
		messageObject['filenameprefix'] =  dataset.filenameprefix;
		messageObject['downloadUrl'] =  "dataset.downloadUrl TODO FIXME";
		messageObject['subscriptionUri'] =  "dataset.subscriptionUri TODO FIX ME";
		messageObject['dataformat'] =  dataset.dataformat;
		messageObject['rdshDissEnabled'] =  dataset.rdshDissEnabled;
		messageObject['jsonLd'] = dataset.jsonLd;

    // this.dataService.getImportMessage(this.SERVICE_URL).subscribe((result) => {



    this.dataService.create(this.SERVICE_URL, messageObject).subscribe((result) => {
 
console.log("I RECEIVEEEEEEEE: ");
console.log(result);

    // }).catch((ex) => {
      // console.error('Error fetching response', ex);
    });


  // test
      // this.onSubmit2();


  }


  onSubmit2() {
    console.log("Onsubmit 2 >>>>>>>>>>>>>>>>>>" + this.metadataForm.valid);
    
        // if (this.metadataForm.valid) {}
          let formValue = this.metadataForm.value;
           console.log(formValue)
    
          //  city
          //  :
          //  "Athens"
          //  climate
          //  :
          //  "med"
          //  countryCB
          //  :
          //  "gr"
          //  dataformat
          //  :
          //  "csv"
          //  datasetImage
          //  :
          //  ""
          //  datasetName
          //  :
          //  "Athens"
          //  description
          //  :
          //  "Rain volume in Athens"
          //  downloadLink
          //  :
          //  "http://localhost/attica/athens/ath"
          //  elevation
          //  :
          //  ""
          //  filenameprefix
          //  :
          //  "ath"
          //  jsonLd
          //  :
          //  ""
          //  latitude
          //  :
          //  ""
          //  license
          //  :
          //  " https://creativecommons.org/licenses/by/4.0/"
          //  longitude
          //  :
          //  ""
          //  measurementUnitsCb
          //  :
          //  "Day"
          //  periodFrom
          //  :
          //  Sun Jan 01 1984 00:00:00 GMT+0200 (GTB Standard Time) {}
          //  periodTo
          //  :
          //  Fri Dec 22 2017 16:23:15 GMT+0200 (GTB Standard Time) {}
          //  rdshDissEnabled
          //  :
          //  "false"
          //  relativeUrl
          //  :
          //  "attica/athens"
          //  state
          //  :
          //  "Attica"



          // this.postObject.comments = formValue.comments;
          // this.postObject.controlScope = formValue.controlScope;
          // this.postObject.controlType = formValue.controlType;
          // this.postObject.user = 1
          // this.postObject.documentType = formValue.documentType;
          // this.postObject.creationDate = new Date().getTime();
          // this.postObject.orderNum = this.dataService.getRandom(5);
          // this.dataService.create(this.SERVICE_URL, this.postObject).subscribe((data: any) => {
          //   console.log("dddddd");
          //   // this.router.navigate(['/control-panel'], { relativeTo: this.activatedRoute })
          // });
      
    }



  replaceSlash(s:string) {
    return s && s.replace('/',':');
  }

  getErrorMessage() {
    return this.metadataForm.hasError("required")
      ? "You must enter a value"
      : this.metadataForm.hasError("email")
        ? "Not a valid email"
        : "not valid Mail";
  }
}
