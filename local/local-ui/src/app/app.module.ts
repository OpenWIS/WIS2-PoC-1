import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { SubmitFormModule } from './submit-form/submit-form.module';
import { MaterialModule } from './material/material.module';
import { DatasetsMainComponent } from './datasets-main/datasets-main.component';
import { routing } from './app.routes';



@NgModule({
  declarations: [
    AppComponent,
    DatasetsMainComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    SubmitFormModule,
    routing   
  ],
  
  
  providers: [],
   bootstrap: [AppComponent]
})


export class AppModule {}




