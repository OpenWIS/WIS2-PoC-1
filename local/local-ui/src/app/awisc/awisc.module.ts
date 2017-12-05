import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AWISCComponent } from './awisc.component';
import { ReactiveFormsModule } from '@angular/forms';  
import { MaterialModule } from '../material/material.module';
import { AppComponent } from '../app.component';


@NgModule({
  imports: [
    CommonModule,  ReactiveFormsModule ,
    MaterialModule
  ],
  declarations: [AWISCComponent],
  exports: [AWISCComponent]
})
export class AWISCModule {}
 