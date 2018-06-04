import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserComponent} from './user/user-component/user.component';
import {LoginComponent} from './login/login.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {UserTableComponent} from './user/table/user-table.component';
import {CreateUserComponent} from './user/create/create-user.component';
import {EditUserComponent} from './user/edit/edit-user.component';

const routes: Routes = [
  {path: 'dashboard', component: DashboardComponent},
  {path: 'login', component: LoginComponent},
  {
    path: 'users', component: UserComponent, children: [
      {path: '', redirectTo: 'overview', pathMatch: 'full'},
      {path: 'overview', component: UserTableComponent},
      {path: 'edit/:id', component: EditUserComponent},
      {path: 'create', component: CreateUserComponent}
    ]
  },
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
