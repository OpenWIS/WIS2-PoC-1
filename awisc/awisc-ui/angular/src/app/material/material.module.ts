import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  MatSidenavModule, MatListModule, MatIconModule, MatToolbarModule
  , MatInputModule, MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule
  , MatTabsModule, MatTableModule, MatCheckboxModule, MatRadioModule, MatAutocompleteModule,
   MatChipsModule,  MatTooltipModule, 

} from '@angular/material';

import {MatExpansionModule} from '@angular/material/expansion';

//  


// , MatCardModule, MatIconModule,
// MatMenuModule, MatExpansionPanel 
//  MatSlideToggleModule, ,
//  , MatSnackBarModule, 
//    MatSliderModule, , MatButtonToggleModule

// MatAccordion 

const MATERIAL_MODULES: any[] = [
  MatSidenavModule, MatListModule, MatIconModule, MatToolbarModule, MatInputModule,
  MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule,
  MatTabsModule, MatTableModule,MatCheckboxModule, MatRadioModule, MatAutocompleteModule,
  MatChipsModule,  MatTooltipModule , MatExpansionModule
];

// MatAccordion , , MatExpansionPanel

// , MatCardModule, MatIconModule, 
// MatListModule, MatMenuModule, 
// MatSlideToggleModule, 
// MatToolbarModule, MatSnackBarModule, MatSidenavModule,
// MatButtonToggleModule, MatSliderModule,



@NgModule({
  imports: [
    CommonModule,
    MATERIAL_MODULES
  ],
  declarations: [],
  exports: [MATERIAL_MODULES]
})
export class MaterialModule { }
