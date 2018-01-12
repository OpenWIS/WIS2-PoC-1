import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { routing } from './app.routes';
import { AppComponent } from './app.component';
import {SettingsComponent} from './settings/settings.component';
import { AwiscMainComponent } from './awisc-main/awisc-main.component';
import { AwiscLoginComponent } from './awisc-login/awisc-login.component';
import { AwiscSearchComponent } from './awisc-search/awisc-search.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { LdshsAdminComponent } from './ldshs-admin/ldshs-admin.component';
import { LdshsEditComponent } from './ldshs-edit/ldshs-edit.component';
import { LdshsViewComponent } from './ldshs-view/ldshs-view.component';


@NgModule({
  declarations: [
    AppComponent,
    SettingsComponent,
    AwiscMainComponent,
    AwiscLoginComponent,
    AwiscSearchComponent,
    SearchResultsComponent,
    LdshsAdminComponent,
    LdshsEditComponent,
    LdshsViewComponent
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




