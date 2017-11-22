import { Component} from '@angular/core';
import { SubmitFormModule } from './submit-form/submit-form.module';
import { RouterModule, Routes } from '@angular/router'; 


export const routerConfig: Routes = [
  {
      path: 'home',
       component: SubmitFormModule
  },
  {
      path: 'submit',
      component: SubmitFormModule
  },
  {
      path: 'about',
      component: SubmitFormModule
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


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
// imports:    RouterModule.forRoot([{path:'', component: AppComponent }] );


})


export class AppComponent {
  title = 'app';
}

