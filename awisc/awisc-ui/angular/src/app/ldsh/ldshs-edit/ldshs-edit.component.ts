import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UUID} from 'angular2-uuid';
import {ActivatedRoute, Router} from '@angular/router';
import {LdshService} from "../../_services/rest/ldsh.service";
import {MatSnackBar} from "@angular/material";
import {LdshDTO} from "../../_dto/Ldsh.dto";

@Component({
  selector: 'app-ldshs-edit',
  templateUrl: './ldshs-edit.component.html',
  styleUrls: ['./ldshs-edit.component.scss']
})
export class LdshsEditComponent implements OnInit {
  ldshForm: FormGroup = null;

  constructor(private activatedRoute: ActivatedRoute, private ldshService: LdshService,
              public snackBar: MatSnackBar, private fb: FormBuilder, private router: Router) {
    // Render an empty form for new entries.
    this.ldshForm = this.fb.group({
      id: [''],
      name: ['', [Validators.required]],
      systemId: ['', [Validators.required]],
      contactEmail: ['', [Validators.required]],
      token: ['', [Validators.required]]
    });
  }

  onLdshGetSuccess = (response) => {
    var data = JSON.parse(response['_body']);
    this.ldshForm.controls["id"].setValue(data["id"]);
    this.ldshForm.controls["name"].setValue(data["name"]);
    this.ldshForm.controls["systemId"].setValue(data["systemId"]);
    this.ldshForm.controls["contactEmail"].setValue(data["contactEmail"]);
    this.ldshForm.controls["token"].setValue(data["token"]);
  };

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      // Get data of requested LDSH.
      let ldshId = params["ldshId"];
      if (ldshId) {
        this.ldshService.get(ldshId, this.onLdshGetSuccess, null);
      }
    });
  }

  // Generates a new token for LDSH.
  generateToken() {
    this.ldshForm.controls["token"].setValue( UUID.UUID());
  }

  // Save LDSH handler.
  onSubmit({ value }: { value: LdshDTO }) {
    this.ldshService.save(value, null, null);
  }

}
