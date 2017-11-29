import { Component, OnInit, ElementRef } from '@angular/core';
import { FormControl, Validators, FormGroup } from "@angular/forms";
import { UUID } from 'angular2-uuid';
import { ViewChild } from "@angular/core";


@Component({
  selector: 'app-ldshs-edit',
  templateUrl: './ldshs-edit.component.html',
  styleUrls: ['./ldshs-edit.component.css']
})
export class LdshsEditComponent implements OnInit {
  metadataForm: FormGroup;
  securityToken :String ="<LDSH Token>";
  
  
  // @ViewChild("tokenTf") private tokenField: ElementRef;
  

  constructor() {}
  
    ngOnInit() {
      this.metadataForm = new FormGroup({
        title: new FormControl("", [Validators.required]),
        systemId: new FormControl("", [Validators.required]),
        email: new FormControl("", [Validators.required]),
        copyright: new FormControl("", []),
        token: new FormControl("", [])
      });
    }

    generateToken(){
      let uuid = UUID.UUID();
      // this.tokenField.nativeElement.value = uuid;
      this.securityToken = uuid;
    }



    onSubmit() {
      console.log("submit");
    }
  

    getErrorMessage() {
      return this.metadataForm.hasError("required")
        ? "You must enter a value"
        : this.metadataForm.hasError("email")
          ? "Not a valid email"
          : "not valid Mail";
    }
  }
  