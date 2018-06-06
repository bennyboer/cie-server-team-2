import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './user';
import {Course} from '../course/course';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  private userUrl = '/api/users';

  public getUsers() {
    return this.http.get<User[]>(this.userUrl);
  }

  public getUser(id: number) {
    return this.http.get<User>(this.userUrl + '/' + id);
  }

  public deleteUser(user: User) {
    return this.http.delete(this.userUrl + '/' + user.id);
  }

  public createUser(user: User) {
    return this.http.post<User>(this.userUrl, user);
  }

  public updateUser(user: User) {
    return this.http.put(this.userUrl, user);
  }

  public getCurrentUser() {
    return this.http.get<User>(this.userUrl + '/current');
  }

  public getUsersWhoSelectedCourse(course: Course) {
    return this.http.get<User[]>(`${this.userUrl}/selected-course/${course.id}`);
  }

}
