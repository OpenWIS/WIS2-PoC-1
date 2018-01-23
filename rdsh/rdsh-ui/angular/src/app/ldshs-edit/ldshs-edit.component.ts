import { Component, OnInit, ElementRef } from '@angular/core';
import { FormControl, Validators, FormGroup } from "@angular/forms";
import { UUID } from 'angular2-uuid';
import { ViewChild } from "@angular/core";
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-ldshs-edit',
  templateUrl: './ldshs-edit.component.html',
  styleUrls: ['./ldshs-edit.component.css']
})
export class LdshsEditComponent implements OnInit {
  metadataForm: FormGroup;
  securityToken: String = "<LDSH Token>";
  paramsObj: Object;


  ldsh = {
    id: "",
    title: "",
    systemId: "",
    email: "",
    copyright: "",
    token: "",
  };

  // @ViewChild("tokenTf") private tokenField: ElementRef;
  ldshList = [
    {
      id: "31",
      title: "Ath_weath",
      systemId: "321",
      email: "ath@weath.com",
      copyright: "no",
      token: "3e617e30-0127-4366-0c43-51e3bcf0ca47",
    },
    {
      id: "32",
      title: "Thes_weath",
      systemId: "231",
      email: "thes@weath.com",
      copyright: "yes",
      token: "a233a609-5584-2bad-3479-cf5b0812d840",

    },
    {
      id: "33",
      title: "Her_weath",
      systemId: "211",
      email: "her@weath.com",
      copyright: "no",
      token: "0000b779-2b42-a1dd-ef81-3875750cafb3",
    }];


  constructor(private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(params => {
      this.paramsObj = { ...params.keys, ...params };
    });
    let id = this.paramsObj["params"]["id"];
    let ldsh = this.ldshList.find(i => i.id === id);

    // let dataset = this.dataSetList.find(i => i.id === id);


    if (ldsh == null) {

      this.metadataForm = new FormGroup({
        title: new FormControl("", [Validators.required]),
        systemId: new FormControl("", [Validators.required]),
        email: new FormControl("", [Validators.required]),
        copyright: new FormControl("", []),
        // token: new FormControl("", [])
      });
    } else {

      this.metadataForm = new FormGroup({
        title: new FormControl(ldsh.title, [Validators.required]),
        systemId: new FormControl(ldsh.systemId, [Validators.required]),
        email: new FormControl(ldsh.email, [Validators.required]),
        copyright: new FormControl(ldsh.copyright, []),
        // token: new FormControl({ value: ldsh.token, disable: true }, [])
      });
      this.securityToken = ldsh.token;
    }
  }
  generateToken() {
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
