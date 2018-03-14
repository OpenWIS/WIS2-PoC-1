import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SubmitFormModule } from './submit-form/submit-form.module';
import { MaterialModule } from './material/material.module';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { routing } from './app.routes';
import { AppComponent } from './app.component';
import { DatasetsMainComponent } from './datasets-main/datasets-main.component';
import { DatasetsAdminComponent } from './datasets-admin/datasets-admin.component';
import { RDSHComponent } from './rdsh/rdsh.component';
import { AWISCComponent } from './awisc/awisc.component';
import {SettingsComponent} from './settings/settings.component';
import { DatasetViewComponent } from './dataset-view/dataset-view.component';
import { LoginComponent } from './login/login.component';
import {AuthGuard} from "./guards/auth.guard";
import {JwtModule} from "@auth0/angular-jwt";
import { AuthService } from './services/auth.service';
import {OkCancelDialogComponent} from './shared/ok-cancel-dialog.component';
import { LocationStrategy, HashLocationStrategy, APP_BASE_HREF } from '@angular/common';
import { environment } from '../environments/environment';

export function getJwtToken(): string {
  return sessionStorage.getItem(environment.CONSTANTS.JWT_STORAGE_NAME);
}

@NgModule({
  declarations: [
    AppComponent,
    DatasetsMainComponent,
    DatasetsAdminComponent,
    RDSHComponent,
    AWISCComponent,
    SettingsComponent,
    DatasetViewComponent,
    OkCancelDialogComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    SubmitFormModule,
    ReactiveFormsModule,
    routing,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: getJwtToken
      }
    })
  ],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy},
    {provide: APP_BASE_HREF, useValue: '!'},
    AuthGuard, AuthService],
  entryComponents: [OkCancelDialogComponent],
   bootstrap: [AppComponent]
})


export class AppModule {}




