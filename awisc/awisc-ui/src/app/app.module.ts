import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { routing } from './app.routes';
import { AppComponent } from './app.component';
import {SettingsComponent} from './settings/settings.component';
import { AwiscMainComponent } from './awisc-main/awisc-main.component';
import { AwiscSearchComponent } from './awisc-search/awisc-search.component';


@NgModule({
  declarations: [
    AppComponent,
    SettingsComponent,
    AwiscMainComponent,
    AwiscSearchComponent,
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




