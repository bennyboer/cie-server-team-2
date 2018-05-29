import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../user/user';
import {AccessToken} from './access-token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  private authUrl = '/api/auth';

  public login(email: string, password: string) {
    const user = new User();
    user.email = email;
    user.password = password;

    const future = this.http.post<AccessToken>(this.authUrl + '/signin', user);

    future.subscribe(token => {
      localStorage.setItem('access_token', token.accessToken);
    });

    return future;
  }

}
