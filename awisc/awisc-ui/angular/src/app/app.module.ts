import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import {
  FormControl,
  Validators,
  ReactiveFormsModule,
  FormsModule
} from "@angular/forms";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MaterialModule } from "./material/material.module";
import { routing } from "./app.routes";
import { AppComponent } from "./app.component";
import { SettingsComponent } from "./settings/settings.component";
import { AwiscMainComponent } from "./awisc-main/awisc-main.component";
import { AwiscLoginComponent } from "./awisc-login/awisc-login.component";
import { AwiscSearchComponent } from "./awisc-search/awisc-search.component";
import { SearchResultsComponent } from "./search-results/search-results.component";
import { LdshsAdminComponent } from "./ldsh/ldshs-admin/ldshs-admin.component";
import { LdshsEditComponent } from "./ldsh/ldshs-edit/ldshs-edit.component";
import { LdshService } from "./_services/rest/ldsh.service";
import { HttpClientModule } from "@angular/common/http";
import { RestClient } from "./_services/rest/rest-client.service";
import { LoginService } from "./_services/rest/login.service";
import { SettingsService } from "./_services/rest/settings.service";
import { SearchService } from "./_services/rest/search.service";
import { WmoCodesService } from "./_services/rest/wmo-codes.service";
import { ErrorUtil } from "./_services/error.util";
import { SuccessUtil } from "./_services/success.util";
import { MatSnackBarModule } from "@angular/material";
import { JwtModule } from "@auth0/angular-jwt";
import { environment } from "../environments/environment";
import { AuthGuard } from "./guards/auth.guard";
import { OkCancelDialogComponent } from "./_shared/ok-cancel-dialog.component";
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

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
    OkCancelDialogComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    routing,
    HttpClientModule,
    MatSnackBarModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return sessionStorage.getItem(environment.CONSTANTS.JWT_STORAGE_NAME);
        }
      }
    })
  ],

  providers: [
    {provide: LocationStrategy, useClass: HashLocationStrategy},
    AuthGuard,
    RestClient,
    LoginService,
    SettingsService,
    SearchService,
    WmoCodesService,
    LdshService,
    ErrorUtil,
    SuccessUtil
  ],
  entryComponents: [OkCancelDialogComponent],
  bootstrap: [AppComponent]
})
export class AppModule {}
