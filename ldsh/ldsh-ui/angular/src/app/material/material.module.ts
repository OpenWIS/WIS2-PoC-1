import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  MatSidenavModule, MatListModule, MatIconModule, MatToolbarModule
  , MatInputModule, MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule
  , MatTabsModule, MatTableModule, MatCheckboxModule, MatRadioModule, MatAutocompleteModule, MatChipsModule, MatSnackBarModule 
} from '@angular/material';

// , MatCardModule, MatIconModule,
// MatMenuModule, MatTooltipModule,
//  MatSlideToggleModule, ,
//    MatSliderModule, , MatButtonToggleModule



const MATERIAL_MODULES: any[] = [
  MatSidenavModule, MatListModule, MatIconModule, MatToolbarModule, MatInputModule,
  MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule,
  MatTabsModule, MatTableModule,MatCheckboxModule, MatRadioModule, MatAutocompleteModule,
   MatChipsModule, MatSnackBarModule 
   
];

// , MatCardModule, MatIconModule,
// MatListModule, MatMenuModule, MatTooltipModule,
// MatSlideToggleModule, , ,
// MatToolbarModule, , MatSidenavModule,
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
