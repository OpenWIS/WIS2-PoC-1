import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UUID} from 'angular2-uuid';
import {ActivatedRoute, Router} from '@angular/router';
import {LdshService} from "../../services/ldsh-service.service";
import {MatSnackBar} from "@angular/material";
import {LDSHDTO} from "../../dto/LDSH.dto";


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
      title: ['', [Validators.required]],
      systemId: ['', [Validators.required]],
      contactEmail: ['', [Validators.required]],
      token: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      // Get data of requested LDSH.
      let ldshId = params["ldshId"];
      if (ldshId) {
        this.ldshService.get(ldshId).subscribe(
          onNext => {
            this.ldshForm.controls["id"].setValue(onNext["id"]);
            this.ldshForm.controls["title"].setValue(onNext["title"]);
            this.ldshForm.controls["systemId"].setValue(onNext["systemId"]);
            this.ldshForm.controls["contactEmail"].setValue(onNext["contactEmail"]);
            this.ldshForm.controls["token"].setValue(onNext["token"]);
          }, onError => {
            console.error(onError);
            this.snackBar.open('There was a problem fetching the LDSH.', null, {
              duration: 5000,
              verticalPosition: 'top'
            });
          });
      }
    });
  }

  // Generates a new token for LDSH.
  generateToken() {
    this.ldshForm.controls["token"].setValue( UUID.UUID());
  }

  // Save LDSH handler.
  onSubmit({ value, valid }: { value: LDSHDTO, valid: boolean }) {
    this.ldshService.save(value).subscribe(
      onNext => {
        this.snackBar.open('LDSH was saved successfully.', null, {
          duration: 5000,
          verticalPosition: 'top'
        });
        this.router.navigate(['/ldshs']);
      }, onError => {
        console.log(onError);
        this.snackBar.open('There was a problem saving the LDSH.', null, {
          duration: 5000,
          verticalPosition: 'top'
        });
      });
  }

}
