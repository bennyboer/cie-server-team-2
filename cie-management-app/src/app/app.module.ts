import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AddUserComponent} from './user/add-user/add-user.component';
import {UserComponent} from './user/user-component/user.component';
import {UserService} from './user/user.service';
import {AppRoutingModule} from './app.routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {JwtModule} from '@auth0/angular-jwt';
import { LoginComponent } from './login/login.component';

export function tokenGetter() {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AddUserComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        whitelistedDomains: ['localhost:8080', 'localhost:4200'],
        blacklistedRoutes: []
      }
    })
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule {}
