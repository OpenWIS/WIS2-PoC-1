import { ModuleWithProviders } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

// component list:
import { SettingsComponent } from "./settings/settings.component";
import { AwiscMainComponent } from "./awisc-main/awisc-main.component";
import { AwiscLoginComponent } from "./awisc-login/awisc-login.component";
import { AwiscSearchComponent } from "./awisc-search/awisc-search.component";

import {LdshsAdminComponent} from "./ldsh/ldshs-admin/ldshs-admin.component";
import {LdshsEditComponent} from "./ldsh/ldshs-edit/ldshs-edit.component";
import {AuthGuard} from "./guards/auth.guard";


export const routerConfig: Routes = [
  {
    path: "home",
    component: AwiscMainComponent
  },
  {
    path: "login",
    component: AwiscLoginComponent
  },
  {
    path: "awiscSearch",
    component: AwiscSearchComponent
  },
  {
    path: "settings",
    component: SettingsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "ldshs",
    component: LdshsAdminComponent,
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
