import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RDSHComponent } from './rdsh.component';
import { ReactiveFormsModule } from '@angular/forms';  
import { MaterialModule } from '../material/material.module';
import { AppComponent } from '../app.component';


@NgModule({
  imports: [
    CommonModule,  ReactiveFormsModule ,
    MaterialModule
  ],
  declarations: [RDSHComponent],
  exports: [RDSHComponent]
})
export class RDSHModule {}
 