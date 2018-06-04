import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Lecturer} from './lecturer';

@Injectable()
export class LecturerService {

  constructor(private http: HttpClient) {
  }

  private apiUrl = '/api/lecturers';

  public getLecturers() {
    return this.http.get<Lecturer[]>(this.apiUrl);
  }

  public getLecturer(id: number) {
    return this.http.get<Lecturer>(this.apiUrl + '/' + id);
  }

  public deleteLecturer(lecturer: Lecturer) {
    return this.http.delete(this.apiUrl + '/' + lecturer.id);
  }

  public createLecturer(lecturer: Lecturer) {
    return this.http.post(this.apiUrl, lecturer);
  }

  public updateLecturer(lecturer: Lecturer) {
    return this.http.put(this.apiUrl, lecturer);
  }

}
