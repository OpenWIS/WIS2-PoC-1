import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  MatSidenavModule, MatListModule, MatIconModule, MatToolbarModule
  , MatInputModule, MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule
  , MatTabsModule, MatTableModule, MatCheckboxModule, MatRadioModule, MatAutocompleteModule,
   MatChipsModule, MatTooltipModule,

} from '@angular/material';

// , MatCardModule, MatIconModule,
// MatMenuModule, 
//  MatSlideToggleModule, ,
//  , MatSnackBarModule, 
//    MatSliderModule, , MatButtonToggleModule



const MATERIAL_MODULES: any[] = [
  MatSidenavModule, MatListModule, MatIconModule, MatToolbarModule, MatInputModule,
  MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule,
  MatTabsModule, MatTableModule,MatCheckboxModule, MatRadioModule, MatAutocompleteModule,
  MatChipsModule, MatTooltipModule,
];

// , MatCardModule, MatIconModule,
// MatListModule, MatMenuModule, 
// MatSlideToggleModule, , ,
// MatToolbarModule, MatSnackBarModule, MatSidenavModule,
// MatButtonToggleModule, , , MatSliderModule,



@NgModule({
  imports: [
    CommonModule,
    MATERIAL_MODULES
  ],
  declarations: [],
  exports: [MATERIAL_MODULES]
})
export class MaterialModule { }
