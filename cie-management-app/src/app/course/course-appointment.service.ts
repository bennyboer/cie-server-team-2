import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CourseAppointment} from './course';

@Injectable()
export class CourseAppointmentService {

  constructor(private http: HttpClient) {
  }

  private apiUrl = '/api/course-appointments';

  public updateCourseAppointments(courseId: number, appointments: CourseAppointment[]) {
    return this.http.post<CourseAppointment[]>(`${this.apiUrl}/${courseId}`, appointments);
  }

  public deleteAppointment(appointmentId: number) {
    return this.http.delete(this.apiUrl + '/' + appointmentId);
  }

}
