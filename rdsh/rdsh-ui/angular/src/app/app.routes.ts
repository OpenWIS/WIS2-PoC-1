import { ModuleWithProviders } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { SettingsComponent } from "./settings/settings.component";
import { QueueAdminComponent } from "./queue-admin/queue-admin.component";
import { QueueEditComponent } from "./queue-edit/queue-edit.component";
import { QueueMonitorComponent } from "./queue-monitor/queue-monitor.component";
import { QueueViewComponent } from "./queue-view/queue-view.component";
import {LdshsAdminComponent} from "./ldsh/ldshs-admin/ldshs-admin.component";
import {HomeComponent} from "./home/home.component";
import {LdshsEditComponent} from "./ldsh/ldshs-edit/ldshs-edit.component";
import {AuthGuard} from "./guards/auth.guard";
import {LoginComponent} from "./auth/login.component";

export const routerConfig: Routes = [
  {
    path: "home",
    component: HomeComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "ldshs",
    component: LdshsAdminComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "channels",
    component: QueueAdminComponent
  },
  {
    path: "settings",
    component: SettingsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "ldsh-edit/:ldshId",
    component: LdshsEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "ldsh-edit",
    component: LdshsEditComponent,
    canActivate: [AuthGuard]
  },
  // {
  //   path: "queueEdit",
  //   component: QueueEditComponent
  // },
  {
    path: "monitor",
    component: QueueMonitorComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "channelView",
    component: QueueViewComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "",
    redirectTo: "/home",
    pathMatch: "full"
  },
  {
    path: "**",
    redirectTo: "/home",
    pathMatch: "full"
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routerConfig);
