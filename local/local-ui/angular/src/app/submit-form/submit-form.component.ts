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
  providers: [DataService]
})
export class SubmitFormComponent implements OnInit {
  displayFn: any;
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
  editMode: boolean = true;
  separatorKeysCodes = [ENTER, COMMA];
  selectedDataSetId: number = null;

  @ViewChild("datasetName") private text1: MatInput;
  @ViewChild("rdshDis") private rdshCb: MatCheckbox;
  @ViewChild("rdshOptions") private rdshOptions: MatRadioGroup;


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
      measurementUnit: "Month",
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
      measurementUnit: "Hour",
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
    { wmocode: "http://codes.wmo.int/common/unit/mon", name: "Month" },
    { wmocode: "http://codes.wmo.int/common/unit/d", name: "Day" },
    { wmocode: "http://codes.wmo.int/common/unit/h", name: "Hour" },
    { wmocode: "http://codes.wmo.int/common/unit/min", name: "Minute (time)" },
  ];


  //chips
  // measurements
  // todo fetch from db.. change label to name
  wmoCodes: any = [
    { id: "1", name: "Wind speed", code: "grib2/codeflag/4.2/3-1-19" },
    { id: "2", name: "Wind chill factor", code: "grib2/codeflag/4.2/0-0-13" },
    { id: "3", name: "Maximum wind level", code: "bufr4/codeflag/0-08-001/4" },
    { id: "4", name: "Maximum wind level", code: "bufr4/codeflag/0-08-086/4" },
    { id: "5", name: "Rain", code: "bufr4/codeflag/0-20-004/16" },
    { id: "6", name: "Rain", code: "bufr4/codeflag/0-20-004/6" },
    { id: "7", name: "Rain", code: "bufr4/codeflag/0-20-005/6" },
    { id: "8", name: "Precipitation of rain", code: "306/4678/RA" },
    { id: "9", name: "Precipitation of rain", code: "bufr4/codeflag/0-20-003/280" },
    { id: "10", name: "Categorical freezing rain", code: "grib2/codeflag/4.2/0-1-34" },
    { id: "11", name: "Reflectivity of rain", code: "grib2/codeflag/4.2/0-15-12" },
    { id: "12", name: "Apparent temperature", code: "grib2/codeflag/4.2/0-0-21" },
    { id: "13", name: "Dewpoint temperature", code: "bufr4/b/12/024" },
    { id: "14", name: "Dewpoint temperature", code: "grib2/codeflag/4.2/0-0-6" },
    { id: "15", name: "Highest daily mean temperature", code: "bufr4/b/12/152" },
    { id: "16", name: "Minimum temperature", code: "grib2/codeflag/4.2/0-0-5" },
    { id: "17", name: "Minimum temperature", code: "bufr4/codeflag/0-08-050/8" },
    { id: "18", name: "Wind direction (from which blowing)", code: "grib2/codeflag/4.2/0-2-0" },
    { id: "19", name: "Dewpoint temperature", code: "bufr4/b/12/103" },
  ];

  //  selectedCodes:any[]=[];
  selectedCodes: WmoCode[];
  //  = [{name: "Wind speed", code: "grib2/codeflag/4.2/0-2-1" }  ];


  codeSelected: any;


  addSelected() {

    if (this.codeSelected != "") {
      let code = this.wmoCodes.find(i => i.code === this.codeSelected);
      var index = this.wmoCodes.indexOf(code, 0);
      if (index > -1) {
        this.wmoCodes.splice(index, 1);
        this.selectedCodes.push(code);
        this.codeSelected = "";
      }
    }
    this.stateCtrl.setValue("");
  }


  setSelectedCode($event, wmocode) {
    this.codeSelected = wmocode;
  }



  filterStates(name: string) {
    let out = this.wmoCodes.filter(
      wmoCode =>
        wmoCode.name.toLowerCase().indexOf(name.toLowerCase()) === 0
    );
    return out;
  }


  add(event: MatChipInputEvent): void {
    let input = event.input;
    let value = event.value;

    // Add code
    if ((value || "").trim()) {
      this.wmoCodes.push({ name: value.trim() });
    }
    // Reset 
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



  deSelect(code: any): void {

    this.removeFromArray(this.selectedCodes, code);
    //Add to unselected
    this.addToArray(this.wmoCodes, code);
    this.refreshAutoCompleteList();
  }


  private addToArray(array: any[], item: any) {
    array.push(item);
  }

  private removeFromArray(array: any[], item: any) {
    let index = array.indexOf(item);
    if (index >= 0) {
      array.splice(index, 1);
    }
  }

  private refreshAutoCompleteList() {

    this.filteredStates = this.stateCtrl.valueChanges
      .startWith(null)
      .map(
      wmoCode =>
        wmoCode
          ? this.filterStates(wmoCode)
          : this.wmoCodes.slice()
      );
  }





  SERVICE_URL = '/cxf/api/verification'
  // SERVICE_URL ='/cxf/api/testget'

  constructor(private dataService: DataService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(params => {
      this.paramsObj = { ...params.keys, ...params };
    });
    let id = this.paramsObj["params"]["id"];
    this.fetchDataset(id);

  }

  rdshAdjust(srcElement: HTMLInputElement) {
    console.log("CBval   " + this.rdshCb.checked);
  }


  // SERVICE_URL ='/cxf/api/verification'
  // SERVICE_URL ='/cxf/api/testget'

  private buildForm(dataset: DataSet, id) {

    this.selectedDataSetId = id;
    console.log(dataset);
    // let dataset = this.dataSetList.find(i => i.id === id);

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
        measurementUnitsCb: new FormControl(""),
        countryCB: new FormControl(""),
        climate: new FormControl(""),
        dataformat: new FormControl(""),
        datasetImage: new FormControl(""),
        wmoSelectedCodes: new FormControl("")
      });
      this.stateCtrl = new FormControl();
    } else {
      this.metadataForm = new FormGroup({
        datasetName: new FormControl(dataset.name, [
          Validators.required,
          Validators.minLength(2)
        ]),
        measurementUnitsCb: new FormControl(dataset.measurementUnitsCb),
        countryCB: new FormControl(dataset.country),
        // climate: new FormControl(dataset.climate),
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
        periodTo: new FormControl(dataset.periodTo, [
          Validators.required,
          Validators.minLength(2)
        ]),
        datasetImage: new FormControl(dataset.imageUrl),
        wmoSelectedCodes: new FormControl("")
      });
      this.stateCtrl = new FormControl();
    }

    if (dataset.wmoCodes) {
      this.selectedCodes = [];
    } else {
      this.selectedCodes = dataset.wmoCodes;
    }
    console.log(dataset.wmoCodes);
    // dataset.wmoCodes ;

    this.refreshAutoCompleteList();
  }

  private fetchDataset(id: number) {

    console.log("dataset loading starts");

    this.dataService.getImportMessage("/cxf/api/getDataset/id=" + id).then(result => {
      console.log("dataset fetched");
      console.log(result);
      this.buildForm(result, id);
    }
    )
  }
  // return this.dataService.getImportMessage("/cxf/api/getDataset/id=" + id).subscribe((result) => {
  // return this.dataService.getImportMessage("/cxf/api/getDataset/id=" + id).toPromise().then( result => <DataSet> result) ;
  // console.log("I got: ");
  // messageObject :DataSet;


  // .then((result) => {       });
  // return  null;

  // return <DataSet>result;


  onGET() {
    //todo fix id
    // this.dataService.getImportMessage("/cxf/api/getDataset/id=10").subscribe((result) => {
    //   console.log("I got: ");
    //   console.log(result);
    // });

  }


  onPOST() {

    var messageObject = new Object();
    //@ Depricated   id =10 athens.
    // let dataset = this.dataSetList.find(i => i.id === '10');

    // TODO REfresh object selectedDataSet gia to update??????????
    let dataset = this.metadataForm.value;
    let id = this.selectedDataSetId;

    // todo object to send...
    messageObject['message'];
    messageObject['id'] = id;
    messageObject['description'] = dataset.description;
    messageObject['periodFrom'] = dataset.periodFrom;
    messageObject['periodTo'] = dataset.periodTo;
    messageObject['license'] = dataset.license;
    messageObject['state'] = dataset.state;
    messageObject['city'] = dataset.city;
    messageObject['latitude'] = dataset.latitude;
    messageObject['longitude'] = dataset.longitude;
    messageObject['elevation'] = dataset.elevation;
    messageObject['relativeUrl'] = dataset.relativeUrl;
    messageObject['filenameprefix'] = dataset.filenameprefix;
    messageObject['dataformat'] = dataset.dataformat;
    messageObject['rdshDissEnabled'] = dataset.rdshDissEnabled;
    messageObject['jsonLd'] = dataset.jsonLd;
    messageObject['country'] = dataset.countryCB;
    messageObject['name'] = dataset.datasetName;
    messageObject['measurementUnit'] = dataset.measurementUnitsCb;
    messageObject['imageUrl'] = dataset.datasetImage;

    //check
    messageObject['wmoCodes'] = this.selectedCodes;
    //dataset.wmoSelectedCodes;

    // TODO fix us:
    // messageObject['downloadUrl'] = dataset.downloadLink;
    // messageObject['subscriptionUri'] = dataset.subscriptionUri;

    console.log(messageObject);
    // this.dataService.create(this.SERVICE_URL, messageObject).subscribe((result) => {
    //UNCOMMENT
    this.dataService.create("/cxf/api/saveDataset", messageObject).subscribe((result) => {
      console.log("I RECEIVEEEEEEEE: ");
      console.log(result);
    });
  }



  onSubmit() {
    console.log("Submit");
    var messageObject = new Object();

    //@ Depricated   id =10 athens.
    // let dataset = this.dataSetList.find(i => i.id === '10');

    // TODO REfresh object selectedDataSet gia to update??????????
    // let dataset = this.selectedDataSet;

    let dataset = this.metadataForm.value;
    console.log("formValue");
    console.log(dataset);


    messageObject['message']; // todo object to send...

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
    messageObject['longitude'] = dataset.longitude;
    messageObject['elevation'] = dataset.elevation;
    messageObject['relativeUrl'] = dataset.relativeUrl;
    messageObject['filenameprefix'] = dataset.filenameprefix;
    messageObject['downloadUrl'] = "dataset.downloadUrl TODO FIXME";
    messageObject['subscriptionUri'] = "dataset.subscriptionUri TODO FIX ME";
    messageObject['dataformat'] = dataset.dataformat;
    messageObject['rdshDissEnabled'] = dataset.rdshDissEnabled;
    messageObject['jsonLd'] = dataset.jsonLd;

    // this.dataService.getImportMessage(this.SERVICE_URL).subscribe((result) => {
    //   console.log("I RECEIVEEEEEEEE: ");
    //   console.log(result);
    //     });

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
    console.log(formValue);

    // this.postObject.comments = formValue.comments;
    // this.postObject.controlScope = formValue.controlScope;
    // this.postObject.controlType = formValue.controlType;
    // this.postObject.user = 1
    // this.postObject.documentType = formValue.documentType;
    // this.postObject.creationDate = new Date().getTime();
    // this.postObject.orderNum = this.dataService.getRandom(5);
    // this.dataService.create(this.SERVICE_URL, this.postObject).subscribe((data: any) => {
    //   // this.router.navigate(['/control-panel'], { relativeTo: this.activatedRoute })
    // });
  }



  replaceSlash(s: string) {
    return s && s.replace('/', ':');
  }

  getErrorMessage() {
    return this.metadataForm.hasError("required")
      ? "You must enter a value"
      : this.metadataForm.hasError("email")
        ? "Not a valid email"
        : "not valid Mail";
  }
}



export interface WmoCode { id: number, name: String, code: String };

export interface DataSet {
  id: number,
  name: String,
  description: String,
  state: String,
  city: String,
  country: String,
  dataformat: String,
  latitude: String,
  longitude: String,
  elevation: String,
  references: String,
  license: String,
  relativeUrl: String,
  filenameprefix: String,
  jsonLd: String,
  imageUrl: String,
  downloadLink: String,
  awsQueue: String
  rdshDissEnabled: String,
  measurementUnitsCb: String,
  periodFrom: Date,
  periodTo: Date,
  wmoCodes: any
};
