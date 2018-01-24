import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormControl, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
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
import {HttpClientModule} from "@angular/common/http";
import { HttpModule, Http } from '@angular/http';
import { RestClient } from './_services/rest.client';
import { ErrorUtil } from './_services/error.util';
import { SuccessUtil } from './_services/success.util';
import { MatSnackBarModule } from "@angular/material";
import { AuthModule} from './_modules/auth.module';

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
    FormsModule,
    ReactiveFormsModule,
    routing,
    HttpClientModule,
    HttpModule,
    MatSnackBarModule,
    AuthModule
  ],
  
  
  providers: [RestClient, ErrorUtil, SuccessUtil],
   bootstrap: [AppComponent]
})


export class AppModule {}




