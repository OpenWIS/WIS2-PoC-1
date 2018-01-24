import { Component, OnInit, ElementRef } from "@angular/core";
import { FormControl, Validators, FormGroup } from "@angular/forms";
import { ViewChild } from "@angular/core";
import { AppComponent } from "../app.component";
import { DataService } from "../data.service";


@Component({
  selector: "app-awisc",
  templateUrl: "./awisc.component.html",
  styleUrls: ["./awisc.component.css"],
  providers: [DataService]
})
export class AWISCComponent implements OnInit {
  metadataForm: FormGroup;
  awisc_id: number;

  @ViewChild("readingTpSel") private readingTpSel: ElementRef;

  registrationStatus: String = "Not registered"


  constructor(private dataService: DataService) {
    AppComponent.selectedMenuItem = 'awisc';
  }

  ngOnInit() {

    this.loadAwisc();

  }

  

  loadAwisc() {
    this.dataService.getCall("getAwisc").then(result => {
      console.log(result);

      //TODO FIX THIS CALL TO CALL THE RDSH system...
      //    this.checkRdshStatus(result);
      this.buldForm(result);
    })
  }



  buldForm(rs: RemoteSystem) {

    console.log(rs);

    if (rs == null) {

      this.metadataForm = new FormGroup({
        name: new FormControl('', [Validators.required]),
        url: new FormControl('', [Validators.required]),
        token: new FormControl('', [Validators.required]),
      });
    } else {
      this.awisc_id = rs.id;
      this.metadataForm = new FormGroup({
        name: new FormControl(rs.name, [Validators.required]),
        url: new FormControl(rs.url, [Validators.required]),
        token: new FormControl(rs.token, [Validators.required]),
      });
    }
  }


  onSubmit() {
    console.log("submit AWISC");

    var messageObject = new Object();
    let rdsh = this.metadataForm.value;
    rdsh.type = "AWISC";
    console.log(rdsh);

    messageObject['message'];
    messageObject['name'] = rdsh.name;
    messageObject['token'] = rdsh.token;
    messageObject['status'] = rdsh.status;
    messageObject['type'] = rdsh.type;
    messageObject['url'] = rdsh.url;
    messageObject['id'] = this.awisc_id;

    this.dataService.create("saveRemote", messageObject).subscribe((result) => {
      console.log("I RECEIVEEEEEEEE: ");
      console.log(result);
    });
  }



  checkRdshStatus(rs: RemoteSystem): any {

   console.log("TODO calling " + rs.url);
    this.dataService.getCall("/AmIregistered").then(result => {
      console.log(result);
      this.registrationStatus = result;
    })
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
}

export interface RemoteSystem {
  id: number,
  name: String,
  url: String,
  status: boolean,
  token: String,
  type: String
};