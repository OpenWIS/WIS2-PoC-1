import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SettingsComponent } from './settings.component';
import { ReactiveFormsModule } from '@angular/forms';  
import { MaterialModule } from '../material/material.module';
import { AppComponent } from '../app.component';


@NgModule({
  imports: [
    CommonModule,  ReactiveFormsModule ,
    MaterialModule
  ],
  declarations: [SettingsComponent],
  exports: [SettingsComponent]
})
export class SettingsModule {}
 