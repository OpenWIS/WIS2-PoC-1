import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { routing } from './app.routes';
import { AppComponent } from './app.component';
import {SettingsComponent} from './settings/settings.component';
import { LdshsAdminComponent } from './ldshs-admin/ldshs-admin.component';
import { QueueAdminComponent } from './queue-admin/queue-admin.component';
import { LdshsMainComponent } from './ldshs-main/ldshs-main.component';
import { LdshsEditComponent } from './ldshs-edit/ldshs-edit.component';
import { QueueEditComponent } from './queue-edit/queue-edit.component';



@NgModule({
  declarations: [
    AppComponent,
    SettingsComponent,
    LdshsAdminComponent,
    QueueAdminComponent,
    LdshsMainComponent,
    LdshsEditComponent,
    QueueEditComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule,
    routing   
  ],
  
  
  providers: [],
   bootstrap: [AppComponent]
})


export class AppModule {}




