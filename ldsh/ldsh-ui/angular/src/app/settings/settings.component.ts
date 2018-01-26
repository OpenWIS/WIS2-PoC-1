import { Component, OnInit, ElementRef } from "@angular/core";
import { FormControl, Validators, FormGroup } from "@angular/forms";
import { ViewChild } from "@angular/core";
import { AppComponent } from "../app.component";
import { Router } from "@angular/router";
import { DataService } from "../data.service";
import { MatSnackBar } from "@angular/material";


@Component({
  selector: "app-settings",
  templateUrl: "./settings.component.html",
  styleUrls: ["./settings.component.css"],
  providers: [DataService]
})
export class SettingsComponent implements OnInit {

  @ViewChild("readingTpSel") private readingTpSel: ElementRef;

  metadataForm: FormGroup;
  pollingServiceStatus: String = "Not defined"
  sysprops: SystemPropery;


  constructor(private dataService: DataService, private router: Router,  public snackBar: MatSnackBar,) {
    AppComponent.selectedMenuItem = 'settings';
  }

  ngOnInit() {

    this.dataService.getCall("getSettings").then(result => {

      this.checkPollingStatus();
      if (result == null) {
        this.buildForm(null);
      } else {
        this.buildForm(result);
      }

    })
  }
  // } else {
  //   this.buildForm(null);
  // }

  // array: any[]
  // res:SystemPropery []
  buildForm(rs: SystemPropery) {

    if (rs == null) {

      this.metadataForm = new FormGroup({
        title: new FormControl("", [Validators.required]),
        systemid: new FormControl("", [Validators.required]),
        email: new FormControl("", [Validators.required]),
        copyright: new FormControl("", []),
        footerTxt: new FormControl("", []),
        homeTxt: new FormControl("", []),

        ftpUrl: new FormControl("", []),
        ftpUsername: new FormControl("", []),
        ftpPassword: new FormControl("", [])
      });
    } else {
      //TODO fix me
      this.sysprops = rs;

      this.metadataForm = new FormGroup({
        title: new FormControl(rs.title, [Validators.required]),
        systemid: new FormControl(rs.systemid, [Validators.required]),
        email: new FormControl(rs.email, [Validators.required]),
        copyright: new FormControl(rs.copyright, []),
        footerTxt: new FormControl(rs.footerTxt, []),
        homeTxt: new FormControl(rs.homeTxt, []),

        ftpUrl: new FormControl(rs.ftpUrl, []),
        ftpUsername: new FormControl(rs.ftpUsername, []),
        ftpPassword: new FormControl(rs.ftpPassword, [])
      });
    }
  }



  onSubmit() {

    var messageObject = new Object();
    var syspr: SystemPropery;

    syspr = this.metadataForm.value;

    messageObject['message'];
    messageObject['title'] = syspr.title;
    // messageObject['systemid'] = syspr.systemid;
    messageObject['systemid'] = syspr.systemid;
    messageObject['email'] = syspr.email;
    messageObject['copyright'] = syspr.copyright;
    messageObject['footerTxt'] = syspr.footerTxt;
    messageObject['homeTxt'] = syspr.homeTxt;

    messageObject['ftpUrl'] = syspr.ftpUrl;
    messageObject['ftpUsername'] = syspr.ftpUsername;
    messageObject['ftpPassword'] = syspr.ftpPassword;

    this.dataService.create("saveSettings", messageObject).subscribe(onNext => {
      this.snackBar.open('Settings were saved successfully.', null, {
        duration: 5000,
        verticalPosition: 'top'
      });
      this.router.navigate(['/datasets']);
    }, onError => {
      console.log(onError);
      this.snackBar.open('There was a problem saving Settings.', null, {
        duration: 5000,
        verticalPosition: 'top'
      });
    });


  }
  /*  
  getSettings

  title: "Stitle",
  systemId: "sid"
  email:"email"
  copyright: "copy"
  footerTxt:"footer"
  homeTxt:"Homepage welcome text"
  ftpPassword:"ftppass"
  ftpUrl:"ftpurl"
  ftpUsername:"ftpusername"
  */



  cancel() {
    this.router.navigate(['/datasets']);
  }

  getErrorMessage() {
    return this.metadataForm.hasError("required")
      ? "You must enter a value"
      : this.metadataForm.hasError("email")
        ? "Not a valid email"
        : "not valid Mail";
  }

  ngAfterViewInit() {
    if (!AppComponent.menuOpen) {
      document.getElementById('sitenav').click();
    }
  }


  startPolling() {

    this.dataService.getCall("startPolling").then(result => {
      console.log(result);
      this.checkPollingStatus();
    })
  }


  
  stopPolling() {

     console.log("STOPPING");
    this.dataService.getCall("stopPolling").then(result => {
      console.log(result);
      this.checkPollingStatus();
    })

  }

  checkPollingStatus() {
 
    this.dataService.getCall("getPollingStatus").then(result => {
      console.log(result);
      this.pollingServiceStatus = result;
    })
  }


}
export interface SystemPropery {
  id: number,
  title: String,
  systemid: String,
  email: String, copyright: String, footerTxt: String, homeTxt: String,
  ftpPassword: String, ftpUrl: String, ftpUsername: String
};
