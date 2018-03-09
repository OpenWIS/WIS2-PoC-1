import { Component, OnInit, ElementRef } from "@angular/core";
import { FormControl, Validators, FormGroup } from "@angular/forms";
import { ViewChild } from "@angular/core";
import { AppComponent } from "../app.component";
import { DataService } from "../data.service";
import { MatSnackBar } from "@angular/material";
import { Router } from "@angular/router";
import { RemoteSystem } from "../dto/RemoteSystem.dto";
import { environment } from "../../environments/environment";


@Component({
  selector: "app-rdsh",
  templateUrl: "./rdsh.component.html",
  styleUrls: ["./rdsh.component.css"],
  providers: [DataService]
})
export class RDSHComponent implements OnInit {

  metadataForm: FormGroup;
  rdsh_id: number;


  @ViewChild("readingTpSel") private readingTpSel: ElementRef;

  registrationStatus: String = "Not registered"


  constructor(private dataService: DataService, public snackBar: MatSnackBar, private router: Router, ) {
    AppComponent.selectedMenuItem = 'rdsh';
  }

  ngOnInit() {

    this.loadRdsh(false);

  }


  loadRdsh(poptValidation: boolean) {

    this.dataService.getCall("getRdsh").then(result => {

      this.checkRdshStatus(result, poptValidation);

      this.buldForm(result);
    })
  }


  checkRdshStatus(rs: RemoteSystem, poptValidation: boolean): any {

    this.dataService.remoteCall("" + rs.url + environment.CONSTANTS.RDSH_ROOT + "ldsh/token/" + rs.token).then(replay => {
      this.registrationStatus = "Verified";

      this.popValidateOk(poptValidation);

    }, onError => {
      console.log(onError);
      this.registrationStatus = onError.name;
      this.snackBar.open('There was a problem registering to the RDSH: ' + onError.message, null, {
        duration: 5000,
        verticalPosition: 'top'
      });
    });
  }


  private popValidateOk(poptValidation: boolean) {
    if (poptValidation) {
      this.snackBar.open('RDSH was registered successfully.', null, {
        duration: 5000,
        verticalPosition: 'top'
      });
    }
  }


  buldForm(rs: RemoteSystem) {

    if (rs == null) {

      this.metadataForm = new FormGroup({
        name: new FormControl('', [Validators.required]),
        url: new FormControl('', [Validators.required]),
        token: new FormControl('', [Validators.required]),
      });
    } else {
      this.rdsh_id = rs.id;
      this.metadataForm = new FormGroup({
        name: new FormControl(rs.name, [Validators.required]),
        url: new FormControl(rs.url, [Validators.required]),
        token: new FormControl(rs.token, [Validators.required]),
      });
    }
  }


  onSubmit() {
    this.loadRdsh(true);
  }


  onSave() {

    var messageObject = new Object();
    let rdsh = this.metadataForm.value;

    if (this.metadataForm.valid) {

      rdsh.type = "RDSH";
      messageObject['message'];
      messageObject['name'] = rdsh.name;
      messageObject['token'] = rdsh.token;
      messageObject['status'] = rdsh.status;
      messageObject['type'] = rdsh.type;
      messageObject['url'] = rdsh.url;
      messageObject['id'] = this.rdsh_id;

      this.dataService.create("saveRemote", messageObject).subscribe(onNext => {
        this.snackBar.open('RDSH was saved successfully.', null, {
          duration: 5000,
          verticalPosition: 'top'
        });
        this.router.navigate(['/datasets']);
      }, onError => {
        console.log(onError);
        this.snackBar.open('There was a problem saving the RDSH.', null, {
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


  getErrorMessage() {
    return this.metadataForm.hasError("required")
      ? "You must enter a value"
      : this.metadataForm.hasError("email")
        ? "Not a valid email"
        : "not valid Mail";
  }

  onCancel() {
    this.router.navigate(['/datasets']);
  }

  ngAfterViewInit() {
    if (!AppComponent.menuOpen) {
      document.getElementById('sitenav').click();
    }
  }
}

