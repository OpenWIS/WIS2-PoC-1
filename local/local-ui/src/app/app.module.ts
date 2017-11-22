import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { SubmitFormModule } from './submit-form/submit-form.module';
import { MaterialModule } from './material/material.module';




@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    SubmitFormModule
   
  ],
  
  
  providers: [],
   bootstrap: [AppComponent]
})


export class AppModule {}




