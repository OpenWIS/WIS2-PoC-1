import { ModuleWithProviders } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

// component list:
import { SettingsComponent } from "./settings/settings.component";
import { AwiscMainComponent } from "./awisc-main/awisc-main.component";
import { AwiscSearchComponent } from "./awisc-search/awisc-search.component";


export const routerConfig: Routes = [
  {
    path: "home",
    component: AwiscMainComponent
  },
  {
    path: "awiscSearch",
    component: AwiscSearchComponent
  },
  {
    path: "settings",
    component: SettingsComponent
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
