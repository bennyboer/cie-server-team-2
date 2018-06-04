import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../user/user';
import {AccessToken} from './access-token';
import {JwtHelperService} from '@auth0/angular-jwt';
import {Observable, Subject} from 'rxjs';
import {UserService} from '../user/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  readonly ACCESS_TOKEN: string = 'access_token';

  constructor(private http: HttpClient, private jwtHelper: JwtHelperService, private userService: UserService) {
  }

  private authUrl = '/api/auth';

  private loggedInEventEmitter: EventEmitter<boolean> = new EventEmitter<boolean>();

  public login(email: string, password: string) {
    const user = new User();
    user.email = email;
    user.password = password;

    const future = this.http.post<AccessToken>(this.authUrl + '/signin', user);

    future.subscribe(token => {
      if (token.isAdmin) {
        localStorage.setItem(this.ACCESS_TOKEN, token.accessToken);
        this.loggedInEventEmitter.emit(this.isAuthenticated());
      }
    });

    return future;
  }

  public initialize() {
    return this.http.get<User>(this.authUrl);
  }

  public loggedIn(): Observable<boolean> {
    return this.loggedInEventEmitter.asObservable();
  }

  public logout() {
    if (this.isAuthenticated()) {
      localStorage.removeItem(this.ACCESS_TOKEN);
      this.loggedInEventEmitter.emit(false);
    }
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem(this.ACCESS_TOKEN);

    if (token !== null) {
      return !this.jwtHelper.isTokenExpired();
    }

    return false;
  }

}
