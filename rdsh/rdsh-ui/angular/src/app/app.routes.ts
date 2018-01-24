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

export const routerConfig: Routes = [
  {
    path: "home",
    component: HomeComponent
  },
  {
    path: "ldshs",
    component: LdshsAdminComponent
  },
  {
    path: "queues",
    component: QueueAdminComponent
  },
  {
    path: "settings",
    component: SettingsComponent
  },
  {
    path: "ldsh-edit/:ldshId",
    component: LdshsEditComponent
  },
  {
    path: "ldsh-edit",
    component: LdshsEditComponent
  },
  {
    path: "queueEdit",
    component: QueueEditComponent
  },
  {
    path: "monitor",
    component: QueueMonitorComponent
  },
  {
    path: "queueView",
    component: QueueViewComponent
  },
  //   {
  //   path: "submit",
  //   component: SubmitFormComponent
  // },
  // {
  //   path: "about",
  //   component: SubmitFormComponent
  // },
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
