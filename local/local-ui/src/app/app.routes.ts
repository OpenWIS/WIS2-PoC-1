import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

//modules
// import { SubmitFormModule } from './submit-form/submit-form.module';

// component list:
 import { DatasetsMainComponent } from './datasets-main/datasets-main.component';
import {SubmitFormComponent} from './submit-form/submit-form.component';


export const routerConfig: Routes = [
    {
        path: 'home',
         component: DatasetsMainComponent
    },
    {
        path: 'submit',
        component: SubmitFormComponent
    },
    {
        path: 'about',
        component: SubmitFormComponent
    },
    {
        path: '',
        redirectTo: '/home',
        pathMatch: 'full'
    },
    {
        path: '**',
        redirectTo: '/home',
        pathMatch: 'full'
    }
  ];
  

  export const routing: ModuleWithProviders = RouterModule.forRoot(routerConfig);
  
  
