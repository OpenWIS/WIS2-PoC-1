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


  constructor(private dataService: DataService, private router: Router, public snackBar: MatSnackBar, ) {
    AppComponent.selectedMenuItem = 'settings';
  }

  ngOnInit() {

    this.dataService.getCall("getSettings").then(result => {

      // this.checkPollingStatus();
      if (result == null) {
        this.buildForm(null);
      } else {
        this.buildForm(result);
      }

    })
  }

  buildForm(rs: SystemPropery) {

    if (rs == null) {

      this.metadataForm = new FormGroup({
        title: new FormControl("", [Validators.required]),
        systemid: new FormControl("", [Validators.required]),
        email: new FormControl("", [Validators.required]),
        copyright: new FormControl("", [Validators.required]),
        footerTxt: new FormControl("", [Validators.required]),
        homeTxt: new FormControl("", [Validators.required]),
        ftpUrl: new FormControl(""),
        ftpUsername: new FormControl(""),
        ftpPassword: new FormControl(""),

      });
    } else {

      this.sysprops = rs;
      this.metadataForm = new FormGroup({
        title: new FormControl(rs.title, [Validators.required]),
        systemid: new FormControl(rs.systemid, [Validators.required]),
        email: new FormControl(rs.email, [Validators.required]),
        copyright: new FormControl(rs.copyright, [Validators.required]),
        footerTxt: new FormControl(rs.footerTxt, [Validators.required]),
        homeTxt: new FormControl(rs.homeTxt, [Validators.required]),
        ftpUrl: new FormControl(rs.ftpUrl),
        ftpUsername: new FormControl(rs.ftpUsername),
        ftpPassword: new FormControl(rs.ftpPassword),

      });
    }
  }

  
  onSubmit() {

    if (this.metadataForm.valid) {
      var messageObject = new Object();
      var syspr: SystemPropery;

      syspr = this.metadataForm.value;

      messageObject['message'];
      messageObject['title'] = syspr.title;
      messageObject['systemid'] = syspr.systemid;
      messageObject['email'] = syspr.email;
      messageObject['copyright'] = syspr.copyright;
      messageObject['footerTxt'] = syspr.footerTxt;
      messageObject['homeTxt'] = syspr.homeTxt;


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

    } else {
      this.snackBar.open('Please enter a correct value to all required fields', null, {
        duration: 5000,
        verticalPosition: 'top'
      });
    }
  }

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


  // startPolling() {
  //   this.dataService.getCall("startPolling").then(result => {
  //     console.log(result);
  //     this.checkPollingStatus();
  //   })
  // }

  // stopPolling() {
  //   this.dataService.getCall("stopPolling").then(result => {
  //     console.log(result);
  //     this.checkPollingStatus();
  //   })
  // }

  // checkPollingStatus() {
  //   this.dataService.getCall("getPollingStatus").then(result => {
  //     console.log(result);
  //     this.pollingServiceStatus = result;
  //   })
  // }


}
export interface SystemPropery {
  id: number,
  title: String,
  systemid: String,
  email: String, copyright: String, footerTxt: String, homeTxt: String,
  ftpPassword: String, ftpUrl: String, ftpUsername: String
};
