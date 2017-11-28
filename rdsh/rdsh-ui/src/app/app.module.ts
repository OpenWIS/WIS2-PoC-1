import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { routing } from './app.routes';
import { AppComponent } from './app.component';
import { DatasetsMainComponent } from './datasets-main/datasets-main.component';
import {SettingsComponent} from './settings/settings.component';
import { LdshsAdminComponent } from './ldshs-admin/ldshs-admin.component';
import { QueueAdminComponent } from './queue-admin/queue-admin.component';

// import { SubmitFormModule } from './submit-form/submit-form.module';
// import { DatasetsAdminComponent } from './datasets-admin/datasets-admin.component';
// import { RDSHComponent } from './rdsh/rdsh.component';
// import { DatasetViewComponent } from './dataset-view/dataset-view.component';



@NgModule({
  declarations: [
    AppComponent,
    DatasetsMainComponent,
    SettingsComponent,
    LdshsAdminComponent,
    QueueAdminComponent,
    // DatasetsAdminComponent,
    // RDSHComponent,
    // DatasetViewComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    // SubmitFormModule,
    ReactiveFormsModule,
    routing   
  ],
  
  
  providers: [],
   bootstrap: [AppComponent]
})


export class AppModule {}




