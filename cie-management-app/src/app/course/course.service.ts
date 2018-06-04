import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Course} from './course';

@Injectable()
export class CourseService {

  constructor(private http: HttpClient) {
  }

  private apiUrl = '/api/courses';

  public getCourses() {
    return this.http.get<Course[]>(this.apiUrl);
  }

  public getCourse(id: number) {
    return this.http.get<Course>(this.apiUrl + '/' + id);
  }

  public deleteCourse(course: Course) {
    return this.http.delete(this.apiUrl + '/' + course.id);
  }

  public createCourse(course: Course) {
    return this.http.post(this.apiUrl, course);
  }

  public updateCourse(course: Course) {
    return this.http.put(this.apiUrl, course);
  }

}
