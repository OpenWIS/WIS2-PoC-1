import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SubmitFormComponent } from './submit-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { AppComponent } from '../app.component';
import { TrimValueAccessorModule } from 'ng-trim-value-accessor';


@NgModule({
  imports: [
    TrimValueAccessorModule,
    CommonModule, 
    ReactiveFormsModule,
    MaterialModule
  ],
  declarations: [SubmitFormComponent],
  // bootstrap:[AppComponent],
  exports: [SubmitFormComponent]
})
export class SubmitFormModule { }
