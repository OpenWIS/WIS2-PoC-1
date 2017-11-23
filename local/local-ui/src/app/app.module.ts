import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SubmitFormModule } from './submit-form/submit-form.module';
import { MaterialModule } from './material/material.module';


import { routing } from './app.routes';

import { AppComponent } from './app.component';
import { DatasetsMainComponent } from './datasets-main/datasets-main.component';
import { RDSHComponent } from './rdsh/rdsh.component';
import {SettingsComponent} from './settings/settings.component';



@NgModule({
  declarations: [
    AppComponent,
    DatasetsMainComponent,
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




