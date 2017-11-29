import { ModuleWithProviders } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

// component list:
import { SettingsComponent } from "./settings/settings.component";
import { LdshsAdminComponent } from "./ldshs-admin/ldshs-admin.component";
import { QueueAdminComponent } from "./queue-admin/queue-admin.component";
import { LdshsMainComponent } from "./ldshs-main/ldshs-main.component";
import { LdshsEditComponent } from "./ldshs-edit/ldshs-edit.component";
import { QueueEditComponent } from "./queue-edit/queue-edit.component";

// import { DatasetsAdminComponent } from "./datasets-admin/datasets-admin.component";
// import { SubmitFormComponent } from "./submit-form/submit-form.component";
// import { RDSHComponent } from "./rdsh/rdsh.component";
// import { DatasetViewComponent } from "./dataset-view/dataset-view.component";

export const routerConfig: Routes = [
  {
    path: "home",
    component: LdshsMainComponent
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
    path: "ldshEdit",
    component: LdshsEditComponent
  },

  {
    path: "queueEdit",
    component: QueueEditComponent
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
