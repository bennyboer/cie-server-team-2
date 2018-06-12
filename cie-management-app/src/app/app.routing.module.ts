import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserComponent} from './user/user-component/user.component';
import {LoginComponent} from './login/login.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {UserTableComponent} from './user/table/user-table.component';
import {CreateUserComponent} from './user/create/create-user.component';
import {EditUserComponent} from './user/edit/edit-user.component';
import {CourseComponent} from './course/course.component';
import {CampusComponent} from './campus/campus.component';
import {DepartmentComponent} from './department/department.component';
import {LecturerComponent} from './lecturer/lecturer.component';
import {SettingsComponent} from './settings/settings.component';

const routes: Routes = [
  {path: 'dashboard', component: DashboardComponent},
  {path: 'login', component: LoginComponent},
  {path: 'courses', component: CourseComponent},
  {path: 'locations', component: CampusComponent},
  {path: 'departments', component: DepartmentComponent},
  {path: 'lecturers', component: LecturerComponent},
  {path: 'settings', component: SettingsComponent},
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
