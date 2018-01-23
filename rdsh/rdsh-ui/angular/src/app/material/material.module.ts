import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  MatSidenavModule, MatListModule, MatIconModule, MatToolbarModule
  , MatInputModule, MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule
  , MatTabsModule, MatTableModule, MatCheckboxModule, MatRadioModule, MatAutocompleteModule,
  MatChipsModule, MatTooltipModule, MatSnackBar, MatSnackBarModule, MatDialogModule,

} from '@angular/material';

const MATERIAL_MODULES: any[] = [
  MatSidenavModule, MatListModule, MatIconModule, MatToolbarModule, MatInputModule,
  MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule,
  MatTabsModule, MatTableModule,MatCheckboxModule, MatRadioModule, MatAutocompleteModule,
  MatChipsModule, MatTooltipModule, MatSnackBarModule, MatDialogModule
];

@NgModule({
  imports: [
    CommonModule,
    MATERIAL_MODULES
  ],
  declarations: [],
  exports: [MATERIAL_MODULES]
})
export class MaterialModule { }
