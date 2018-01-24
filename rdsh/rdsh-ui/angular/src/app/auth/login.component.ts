import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material";
import {AuthDTO} from "../dto/Auth.dto";
import {AuthService} from "../services/auth.service";
import {Router} from '@angular/router';
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = null;

  constructor(public snackBar: MatSnackBar, private fb: FormBuilder,
              private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  // Save LDSH handler.
  onSubmit({ value }: { value: AuthDTO }) {
    console.log(value);
    this.authService.login(value).subscribe(
      onNext => {
        // Save the JWT to be used in future requests.
        sessionStorage.setItem(environment.CONSTANTS.JWT_STORAGE_NAME, onNext["jwt"]);
        // Navigate to the default admin page.
        this.router.navigate(['/ldshs']);
      }, onError => {
        console.log(onError);
        this.snackBar.open('Authentication was unsuccessful.', null, {
          duration: 5000,
          verticalPosition: 'top'
        });
      });
  }
}
