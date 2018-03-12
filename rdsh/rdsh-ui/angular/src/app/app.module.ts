import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './material/material.module';
import {routing} from './app.routes';
import {AppComponent} from './app.component';
import {SettingsComponent} from './settings/settings.component';
import {QueueAdminComponent} from './queue-admin/queue-admin.component';
import {QueueEditComponent} from './queue-edit/queue-edit.component';
import {QueueMonitorComponent} from './queue-monitor/queue-monitor.component';
import {QueueViewComponent} from './queue-view/queue-view.component';
import {HttpClientModule} from "@angular/common/http";
import {LdshsAdminComponent} from "./ldsh/ldshs-admin/ldshs-admin.component";
import {HomeComponent} from "./home/home.component";
import {LdshsEditComponent} from "./ldsh/ldshs-edit/ldshs-edit.component";
import {LdshService} from "./services/ldsh-service.service";
import {OkCancelDialogComponent} from './shared/ok-cancel-dialog.component';
import {SettingsService} from "./services/settings-service.service";
import {AuthGuard} from "./guards/auth.guard";
import { LoginComponent } from './auth/login.component';
import {AuthService} from "./services/auth.service";
import {JwtModule} from "@auth0/angular-jwt";
import {environment} from "../environments/environment";
import { ChannelService } from './services/channel.service';
import { ChartsModule } from 'ng2-charts';
import { LocationStrategy, HashLocationStrategy, APP_BASE_HREF } from '@angular/common';

export function getJwtToken(): string {
  return sessionStorage.getItem(environment.CONSTANTS.JWT_STORAGE_NAME);
}

@NgModule({
  declarations: [
    AppComponent,
    SettingsComponent,
    LdshsAdminComponent,
    QueueAdminComponent,
    HomeComponent,
    LdshsEditComponent,
    QueueEditComponent,
    QueueMonitorComponent,
    QueueViewComponent,
    OkCancelDialogComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule,
    routing,
    HttpClientModule,
    ChartsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: getJwtToken
      }
    })
   ],

  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy},
    {provide: APP_BASE_HREF, useValue: '!'},
    LdshService, SettingsService, AuthGuard, AuthService, ChannelService
  ],
  entryComponents: [OkCancelDialogComponent],
  bootstrap: [AppComponent]
})

export class AppModule {
}
