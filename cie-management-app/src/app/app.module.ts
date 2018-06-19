import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserComponent} from './user/user-component/user.component';
import {UserService} from './user/user.service';
import {AppRoutingModule} from './app.routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {JwtHelperService, JwtModule} from '@auth0/angular-jwt';
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
import {YesNoDialogComponent} from './util/yes-no-dialog/yes-no-dialog.component';
import {TruncatePipe} from './pipes/truncate/truncate.pipe';
import {NotificationDialogComponent} from './notifications/notification-dialog/notification-dialog.component';
import {NotificationService} from './notifications/notification.service';
import {OwlDateTimeModule, OwlNativeDateTimeModule} from 'ng-pick-datetime';
import {CourseAppointmentDialogComponent} from './course/course-appointment-dialog/course-appointment-dialog.component';
import {CourseAppointmentService} from './course/course-appointment.service';
import {HexPipe} from './pipes/hexpipe/hex.pipe';
import {ColorPickerModule} from 'ngx-color-picker';
import {SettingsService} from './util/settings/settings.service';
import {SettingsComponent} from './settings/settings.component';
import {CourseImportDialogComponent} from './course/course-import-dialog/course-import-dialog.component';
import {CowAnimComponent} from './util/cow-anim/cow-anim.component';

const helper = new JwtHelperService();

export function tokenGetter() {
  let accessToken = localStorage.getItem('access_token');

  if (accessToken !== undefined && accessToken !== null && helper.isTokenExpired(accessToken)) {
    localStorage.removeItem('access_token');
    accessToken = null;

    alert('Your login session expired, reloading the page now.');
    window.location.reload(); // Reload page.
  }

  return accessToken;
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
    HexPipe,
    NotificationDialogComponent,
    CourseAppointmentDialogComponent,
    SettingsComponent,
    CourseImportDialogComponent,
    CowAnimComponent
  ],
  entryComponents: [
    CourseDialogComponent,
    CampusDialogComponent,
    DepartmentDialogComponent,
    LecturerDialogComponent,
    CourseSelectionsDialogComponent,
    YesNoDialogComponent,
    NotificationDialogComponent,
    CourseAppointmentDialogComponent,
    CourseImportDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    ColorPickerModule,
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
    CampusService,
    NotificationService,
    CourseAppointmentService,
    SettingsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
