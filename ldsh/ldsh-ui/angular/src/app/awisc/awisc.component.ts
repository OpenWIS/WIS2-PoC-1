import { Component, OnInit, ElementRef } from "@angular/core";
import { FormControl, Validators, FormGroup } from "@angular/forms";
import { ViewChild } from "@angular/core";
import { AppComponent } from "../app.component";
import { DataService } from "../data.service";
import { MatSnackBar } from "@angular/material";
import { Router } from "@angular/router";
import { RemoteSystem } from "../dto/RemoteSystem.dto";
import { ProxyService } from "../services/proxy.service";
import { environment } from "../../environments/environment";
import { ForwardRequestDTO } from "../dto/ForwardRequest.dto";


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


  constructor(private dataService: DataService, public snackBar: MatSnackBar, private proxyService:ProxyService, private router: Router) {
    AppComponent.selectedMenuItem = 'awisc';
  }

  ngOnInit() {
    this.loadAwisc(false);
  }


  loadAwisc(poptValidation: boolean) {
    this.dataService.getCall("getAwisc").then(result => {
      if (result.url.length>0 && result.token.length>0){
       this.checkAwiscStatus(result, poptValidation);
      }
      this.buldForm(result);
    })
  }



  buldForm(rs: RemoteSystem) {


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

    this.loadAwisc(true);

  }


  onSave() {

    if (this.metadataForm.valid) {
      
      var messageObject = new Object();
      let rdsh = this.metadataForm.value;
      rdsh.type = "AWISC";

      messageObject['message'];
      messageObject['name'] = rdsh.name;
      messageObject['token'] = rdsh.token;
      messageObject['status'] = rdsh.status;
      messageObject['type'] = rdsh.type;
      messageObject['url'] = rdsh.url;
      messageObject['id'] = this.awisc_id;

      this.dataService.create("saveRemote", messageObject).subscribe(onNext => {
        this.snackBar.open('AWISC was saved successfully.', null, {
          duration: 5000,
          verticalPosition: 'top'
        });
      }, onError => {
        console.log(onError);
        this.snackBar.open('There was a problem saving the AWISC.', null, {
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

  onCancel() {
    this.router.navigate(['/datasets']);
  }

  checkAwiscStatus(rs: RemoteSystem, poptValidation: boolean): any {

    let request: ForwardRequestDTO = new ForwardRequestDTO();
    request.method = "GET";
    request.uri = rs.url + environment.CONSTANTS.AWISC_ROOT + "ldsh/token/" + rs.token;
    request.data = null;

    this.proxyService.forward(request).subscribe(response => {
      this.registrationStatus = "Verified";
      this.popValidationMsg(poptValidation);

    }, error => {
      console.log(error);
      this.registrationStatus = error.name;
      this.snackBar.open('There was a problem registering with the AWISC: ' + error.message, null, {
        duration: 5000,
        verticalPosition: 'top'
      });
    });

    // this.dataService.awiscCall(rs.url+"", "ldsh/token/" + rs.token).then(replay => {

    //   this.registrationStatus = "Verified";
    //   this.popValidationMsg(poptValidation);
    // }, onError => {
    //   console.log(onError);
    //   this.registrationStatus = onError.name;
    //   this.snackBar.open('There was a problem registering to the AWISC: ' + onError.message, null, {
    //     duration: 5000,
    //     verticalPosition: 'top'
    //   });
    // });

  }


  private popValidationMsg(poptValidation: boolean) {
    if (poptValidation) {
      this.snackBar.open('LDSH was registered to AWISC successfully.', null, {
        duration: 5000,
        verticalPosition: 'top'
      });
    }
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
