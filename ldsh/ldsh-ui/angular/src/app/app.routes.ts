import { ModuleWithProviders } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

// component list:
import { DatasetsAdminComponent } from "./datasets-admin/datasets-admin.component";
import { SubmitFormComponent } from "./submit-form/submit-form.component";
import { RDSHComponent } from "./rdsh/rdsh.component";
import { AWISCComponent } from "./awisc/awisc.component";
import { SettingsComponent } from "./settings/settings.component";
import { DatasetsMainComponent } from "./datasets-main/datasets-main.component";
import { DatasetViewComponent } from "./dataset-view/dataset-view.component";
import { LoginComponent } from "./login/login.component";
import { AuthGuard } from "./guards/auth.guard";

export const routerConfig: Routes = [
  {
    path: "home",
    component: DatasetsMainComponent,
    data:{title:'Local Data Sharing Hub'}
  },
  {
    path: "login",
    component: LoginComponent,
    data:{title:'Please login'}
  },
  {
    path: "datasets",
    component: DatasetsAdminComponent,
    canActivate: [AuthGuard],
    data:{title:'Datasets page'}
  },
  {
    path: "rdsh",
    component: RDSHComponent,
    canActivate: [AuthGuard],
    data:{title:'RDSH page'}
  },
  {
    path: "awisc",
    component: AWISCComponent,
    canActivate: [AuthGuard],
    data:{title:"AWISC page"}
  },
  {
    path: "settings",
    component: SettingsComponent,
    canActivate: [AuthGuard],
    data:{title:'Settings page'}
  },
  {
    path: "view",
    component: DatasetViewComponent
  },
  // edit mode
  {
    path: "submit",
    component: SubmitFormComponent,
    canActivate: [AuthGuard],
    data:{title:'Dataset page'}
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
