import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  MatSidenavModule, MatListModule, MatIconModule, MatToolbarModule
  , MatInputModule, MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule
  , MatTabsModule, MatTableModule
} from '@angular/material';

// , MatCardModule, MatIconModule,
// MatMenuModule, MatTooltipModule,
//  MatSlideToggleModule, MatCheckboxModule,
//  , MatSnackBarModule, 
//  MatAutocompleteModule, MatRadioModule, MatSliderModule, , MatButtonToggleModule



const MATERIAL_MODULES: any[] = [
  MatSidenavModule, MatListModule, MatIconModule, MatToolbarModule, MatInputModule,
  MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule,
  MatTabsModule, MatTableModule
];

// , MatCardModule, MatIconModule,
// MatListModule, MatMenuModule, MatTooltipModule,
// MatSlideToggleModule, , MatCheckboxModule,
// MatToolbarModule, MatSnackBarModule, MatSidenavModule,
// MatButtonToggleModule, MatAutocompleteModule, MatRadioModule, MatSliderModule,



@NgModule({
  imports: [
    CommonModule,
    MATERIAL_MODULES
  ],
  declarations: [],
  exports: [MATERIAL_MODULES]
})
export class MaterialModule { }
