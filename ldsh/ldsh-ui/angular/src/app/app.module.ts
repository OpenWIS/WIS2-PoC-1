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




@NgModule({
  declarations: [
    AppComponent,
    DatasetsMainComponent,
    DatasetsAdminComponent,
    RDSHComponent,
    AWISCComponent,
    SettingsComponent,
    DatasetViewComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    SubmitFormModule,
    ReactiveFormsModule,
    routing,
    HttpClientModule,
  ],
  
  
  providers: [],
   bootstrap: [AppComponent]
})


export class AppModule {}




