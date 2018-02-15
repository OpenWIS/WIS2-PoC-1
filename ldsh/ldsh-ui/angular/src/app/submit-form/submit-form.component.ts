import { Component, OnInit, ElementRef, Input, Inject } from "@angular/core";
import { FormControl, Validators, FormGroup, FormArray } from "@angular/forms";
import { ViewChild } from "@angular/core";
import { MatInput, MatRadioGroup, MatChipInputEvent } from "@angular/material";
import { MatCheckbox, MatSnackBar } from "@angular/material";
import { ActivatedRoute, Router } from "@angular/router";
import { Observable } from "rxjs/Observable";
import "rxjs/add/operator/startWith";
import "rxjs/add/operator/map";
import { ENTER, COMMA } from "@angular/cdk/keycodes";
import { DataService } from "../data.service";
import { DOCUMENT } from "@angular/platform-browser";

@Component({
  selector: "app-submit-form",
  templateUrl: "./submit-form.component.html",
  styleUrls: ["./submit-form.component.css"],
  providers: [DataService]
})
export class SubmitFormComponent implements OnInit {

  // private window: Window
  displayFn: any;
  rdshDissEnabled = false;
  disseminateData = false;
  metadataForm: FormGroup;
  paramsObj: Object;
  pageUrl: String;
  lastUpdate: Date;

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


  constructor(private dataService: DataService, private activatedRoute: ActivatedRoute,
    private router: Router, public snackBar: MatSnackBar, @Inject(DOCUMENT) private document: any) {
  }

  measurementUnits: MeasurementUnit[];
  wmoCodes: WmoCode[];
  countries: Country[];
  dataformats: DataFormat[];
  selectedCodes: WmoCode[];
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


  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(params => {
      this.paramsObj = { ...params.keys, ...params };
    });
    let dataset_id = this.paramsObj["params"]["id"];
    this.featchFormData(dataset_id);

    this.pageUrl = " http://" + this.document.location.hostname; // .origin (with port)

  }

  rdshAdjust(srcElement: HTMLInputElement) {
  }


  private buildForm(dataset: DataSet, id) {

    if (dataset == null) {
      // create new
      this.metadataForm = new FormGroup({
        state: new FormControl(""),
        city: new FormControl(""),
        latitude: new FormControl(""),
        longitude: new FormControl(""),
        elevation: new FormControl(""),
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
        filenameprefix: new FormControl("", [Validators.required]),
        jsonLd: new FormControl(""),
        downloadLink: new FormControl("", []),
        awsQueue: new FormControl("", []),
        rdshDissEnabled: new FormControl(false, []),
        sendData: new FormControl(false, []),
        periodFrom: new FormControl("", [
          Validators.required,
          Validators.minLength(2)
        ]),
        periodTo: new FormControl(""),
        measurementUnit: new FormControl(""),
        countryCB: new FormControl(""),
        climate: new FormControl(""),
        dataformat: new FormControl(""),
        datasetImage: new FormControl(""),
        wmoSelectedCodes: new FormControl("")
      });
      this.stateCtrl = new FormControl();
      this.selectedCodes = [];

    } else {

      let countrId = null;
      if (dataset.country) {
        countrId = dataset.country.id;
      }
      let dataformatId = null;
      if (dataset.dataformat) {
        dataformatId = dataset.dataformat.id;
      }
      this.lastUpdate = dataset.lastUpdate;
      this.disseminateData = dataset.sendData;

      this.metadataForm = new FormGroup({
        datasetName: new FormControl(dataset.name, [
          Validators.required,
          Validators.minLength(2)
        ]),
        measurementUnit: new FormControl(dataset.measurementUnit),
        countryCB: new FormControl(countrId),

        state: new FormControl(dataset.state),
        city: new FormControl(dataset.city),
        latitude: new FormControl(dataset.latitude),
        longitude: new FormControl(dataset.longitude),
        elevation: new FormControl(dataset.elevation),
        description: new FormControl(dataset.description, [
          Validators.required,
          Validators.minLength(2)
        ]),
        license: new FormControl(dataset.license, [
          Validators.required,
          Validators.minLength(2)
        ]),
        relativeUrl: new FormControl(dataset.relativeUrl),
        filenameprefix: new FormControl(dataset.filenameprefix, [
          Validators.required,
          Validators.minLength(2)
        ]),
        jsonLd: new FormControl(dataset.jsonLd),
        downloadLink: new FormControl(dataset.downloadLink),
        awsQueue: new FormControl(dataset.awsQueue),
        rdshDissEnabled: new FormControl(dataset.rdshDissEnabled, []),

        sendData: new FormControl(dataset.sendData, []),
        dataformat: new FormControl(dataformatId),

        periodFrom: new FormControl(new Date(dataset.periodFrom), [
          Validators.required,
          Validators.minLength(2)
        ]),
        periodTo: new FormControl(dataset.periodTo),
        datasetImage: new FormControl(dataset.imageUrl),
        wmoSelectedCodes: new FormControl("")
      });
      this.stateCtrl = new FormControl();

      if (dataset.wmoCodes instanceof Array) {
        this.selectedCodes = (dataset.wmoCodes).slice();
      } else {
        this.selectedCodes = [];
        if (dataset.wmoCodes != undefined) {
          this.selectedCodes.push(dataset.wmoCodes);
        }
      }
    }

    this.refreshAutoCompleteList();
  }

  private fetchDataset(id: number) {

    if (id) {
      this.dataService.getCall("getDataset/id=" + id).then(result => {
        this.selectedDataSetId = id;
        this.buildForm(result, id);
      })
    } else {
      this.buildForm(null, null);
    }
  }

  private featchFormData(id: number) {

    this.dataService.getCall("getAllDataFormats").then(result => {
      this.dataformats = result;
      this.dataService.getCall("getAllWmoCodes").then(result => {

        this.wmoCodes = result;
        this.dataService.getCall("getAllCountries").then(result => {
          this.countries = result;
          this.dataService.getCall("getMeasurementUnits").then(result => {
            this.measurementUnits = result;
            this.fetchDataset(id);
          })
        })
      })
    })
  }


  onSubmit() {

    if (this.metadataForm.valid) {
      var messageObject = new Object();

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
      messageObject['rdshDissEnabled'] = dataset.rdshDissEnabled;
      messageObject['jsonLd'] = dataset.jsonLd;
      messageObject['dataformat'] = this.dataformats.find(i => i.id === dataset.dataformat);
      messageObject['country'] = this.countries.find(i => i.id === dataset.countryCB);
      messageObject['name'] = dataset.datasetName;
      messageObject['measurementUnit'] = dataset.measurementUnit;
      messageObject['sendData'] = dataset.sendData;
      messageObject['imageUrl'] = dataset.datasetImage;
      messageObject['wmoCodes'] = this.selectedCodes;

      // Download URL   -- downloadUrl
      messageObject['downloadUrl'] = this.pageUrl + "/" + dataset.relativeUrl + "/" + dataset.filenameprefix;
      messageObject['subscriptionUri'] = this.pageUrl + ":" + dataset.relativeUrl + ":" + dataset.filenameprefix;

      this.dataService.create("saveDataset", messageObject).subscribe(onNext => {
        this.snackBar.open('Dataset was saved successfully.', null, {
          duration: 5000,
          verticalPosition: 'top'
        });
        this.router.navigate(['/datasets']);
      }, onError => {
        console.log(onError);
        this.snackBar.open('There was a problem saving the Dataset.', null, {
          duration: 5000,
          verticalPosition: 'top'
        });
      });
    } else {
      this.snackBar.open('Please enter a correct value to all required fields', null, {
        duration: 5000,
        verticalPosition: 'top'
      });
    }
  }

  //   console.log(result);
  //   this.router.navigate(['/datasets'], { queryParams: {} });
  // });


  goBack() {
    this.router.navigate(['/datasets'], { queryParams: {} });
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



export interface WmoCode { id: number, name: String, code: String, continent: String, uri: String };
export interface DataFormat { id: number, name: String, description: String };
export interface Country { id: number, name: String, code: String };
export interface MeasurementUnit { id: number, name: String, code: String };

export interface DataSet {
  id: number,
  name: String,
  description: String,
  state: String,
  city: String,
  country: Country,
  dataformat: DataFormat,
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
  subscriptionUri: String;
  awsQueue: String
  rdshDissEnabled: String,
  measurementUnit: String,
  periodFrom: Date,
  periodTo: Date,
  wmoCodes: WmoCode[]
  sendData: boolean;
  lastUpdate: Date;
};
