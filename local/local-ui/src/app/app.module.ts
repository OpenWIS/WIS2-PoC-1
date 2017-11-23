import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SubmitFormModule } from './submit-form/submit-form.module';
import { MaterialModule } from './material/material.module';


import { routing } from './app.routes';

import { AppComponent } from './app.component';
import { DatasetsMainComponent } from './datasets-main/datasets-main.component';
import { DatasetsAdminComponent } from './datasets-admin/datasets-admin.component';
import { RDSHComponent } from './rdsh/rdsh.component';
import {SettingsComponent} from './settings/settings.component';




@NgModule({
  declarations: [
    AppComponent,
    DatasetsMainComponent,
    DatasetsAdminComponent,
    RDSHComponent,
    SettingsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    SubmitFormModule,
    ReactiveFormsModule,
    routing   
  ],
  
  
  providers: [],
   bootstrap: [AppComponent]
})


export class AppModule {}




