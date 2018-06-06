import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserComponent} from './user/user-component/user.component';
import {UserService} from './user/user.service';
import {AppRoutingModule} from './app.routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {JwtModule} from '@auth0/angular-jwt';
import {LoginComponent} from './login/login.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {ProtectedDirective} from './directives/protected.directive';
import {CustomMaterialModule} from './material.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CreateUserComponent} from './user/create/create-user.component';
import {EditUserComponent} from './user/edit/edit-user.component';
import {UserTableComponent} from './user/table/user-table.component';
import {CourseComponent} from './course/course.component';
import {CourseService} from './course/course.service';
import {CourseDialogComponent} from './course/course-dialog/course-dialog.component';
import {LecturerComponent} from './lecturer/lecturer.component';
import {CampusComponent} from './campus/campus.component';
import {DepartmentComponent} from './department/department.component';
import {DepartmentDialogComponent} from './department/department-dialog/department-dialog.component';
import {CampusDialogComponent} from './campus/campus-dialog/campus-dialog.component';
import {LecturerDialogComponent} from './lecturer/lecturer-dialog/lecturer-dialog.component';
import {LecturerService} from './lecturer/lecturer.service';
import {DepartmentService} from './department/department.service';
import {CampusService} from './campus/campus.service';
import {CourseSelectionsDialogComponent} from './course/course-selections-dialog/course-selections-dialog.component';
import { YesNoDialogComponent } from './util/yes-no-dialog/yes-no-dialog.component';
import { TruncatePipe } from './pipes/truncate/truncate.pipe';
import { NotificationDialogComponent } from './notifications/notification-dialog/notification-dialog.component';

export function tokenGetter() {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    CreateUserComponent,
    EditUserComponent,
    UserTableComponent,
    LoginComponent,
    DashboardComponent,
    ProtectedDirective,
    CourseComponent,
    CourseDialogComponent,
    LecturerComponent,
    CampusComponent,
    DepartmentComponent,
    DepartmentDialogComponent,
    CampusDialogComponent,
    LecturerDialogComponent,
    CourseSelectionsDialogComponent,
    YesNoDialogComponent,
    TruncatePipe,
    NotificationDialogComponent
  ],
  entryComponents: [
    CourseDialogComponent,
    CampusDialogComponent,
    DepartmentDialogComponent,
    LecturerDialogComponent,
    CourseSelectionsDialogComponent,
    YesNoDialogComponent,
    NotificationDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        whitelistedDomains: ['localhost:8080', 'localhost:4200'],
        blacklistedRoutes: []
      }
    })
  ],
  providers: [
    UserService,
    CourseService,
    LecturerService,
    DepartmentService,
    CampusService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
