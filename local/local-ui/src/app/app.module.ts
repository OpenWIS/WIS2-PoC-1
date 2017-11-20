import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {
  MatSidenavModule,  MatListModule, MatIconModule, MatToolbarModule

  
} from '@angular/material';

// MatButtonModule, MatCardModule, MatIconModule,
// MatMenuModule, MatTooltipModule,
//  MatSlideToggleModule, MatInputModule, MatCheckboxModule,
//  , MatSnackBarModule, 
//  MatTabsModule, MatSelectModule, MatDatepickerModule, MatNativeDateModule,
//  MatAutocompleteModule, MatRadioModule, MatSliderModule, MatTableModule, MatButtonToggleModule



import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';

const MATERIAL_MODULES: any[] = [
  MatSidenavModule,  MatListModule,MatIconModule, MatToolbarModule
];

// MatButtonModule, MatCardModule, MatIconModule,
// MatListModule, MatMenuModule, MatTooltipModule,
// MatSlideToggleModule, MatInputModule, MatCheckboxModule,
// MatToolbarModule, MatSnackBarModule, MatSidenavModule,
// MatTabsModule, MatSelectModule, MatDatepickerModule, MatNativeDateModule,
// MatButtonToggleModule, MatAutocompleteModule, MatRadioModule, MatSliderModule,
// MatTableModule




@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MATERIAL_MODULES
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
