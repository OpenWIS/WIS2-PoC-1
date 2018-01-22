import { Component, OnInit, ElementRef } from "@angular/core";
import { FormControl, Validators, FormGroup } from "@angular/forms";
import { ViewChild } from "@angular/core";
import { AppComponent } from "../app.component";
import { Router } from "@angular/router";
import { DataService } from "../data.service";


@Component({
  selector: "app-settings",
  templateUrl: "./settings.component.html",
  styleUrls: ["./settings.component.css"],
  providers: [DataService]
})
export class SettingsComponent implements OnInit {
  metadataForm: FormGroup;

  @ViewChild("readingTpSel") private readingTpSel: ElementRef;


  sysprops: SystemPropery;

  constructor(private dataService: DataService, private router: Router) {
    AppComponent.selectedMenuItem = 'settings';
  }

  ngOnInit() {

    this.dataService.getCall("/cxf/api/getSettings").then(result => {
      console.log("I GOTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT ");
      console.log(result);


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

    //TODO  id??????????????????
    // let id = this.selectedDataSetId;
    var messageObject = new Object();
    var syspr: SystemPropery;

    syspr = this.metadataForm.value;
    console.log(syspr);
    console.log(syspr.systemid);
    

    messageObject['message'];

    // todo object to send...
    // messageObject['id'] = id;
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

    this.dataService.create("/cxf/api/saveSettings", messageObject).subscribe((result) => {
      console.log("I RECEIVEEEEEEEE: ");
      console.log(result);
    });

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


  }

  clear() {
    this.router.navigate(['/settings']);
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

  testConnection() {

  }

  startPolling() {


    this.dataService.getCall("/cxf/api/startPolling"). then(result => {
      console.log("I GOTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT ");
      console.log(result);
    })

  }

  stopPolling() {

  }


}
export interface SystemPropery {
  id: number,
  title: String, 
  systemid: String,
   email: String, copyright: String, footerTxt: String, homeTxt: String,
  ftpPassword: String, ftpUrl: String, ftpUsername: String
};
