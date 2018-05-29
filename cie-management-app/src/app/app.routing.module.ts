import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserComponent} from './user/user-component/user.component';
import {AddUserComponent} from './user/add-user/add-user.component';
import {LoginComponent} from './login/login.component';
import {DashboardComponent} from './dashboard/dashboard.component';

const routes: Routes = [
  {path: 'dashboard', component: DashboardComponent},
  {path: 'login', component: LoginComponent},
  {path: 'users', component: UserComponent},
  {path: 'add-user', component: AddUserComponent},
  {
    path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule {
}
