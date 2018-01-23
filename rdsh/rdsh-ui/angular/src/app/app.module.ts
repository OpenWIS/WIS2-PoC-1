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
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule,
    routing,
    HttpClientModule
  ],
  providers: [
    LdshService
  ],
  entryComponents: [OkCancelDialogComponent],
  bootstrap: [AppComponent]
})

export class AppModule {
}
