import {Lecturer} from '../lecturer/lecturer';
import {Campus} from '../campus/campus';
import {Department} from '../department/department';

export class Course {

  id: number;
  name: string;
  description: string;
  room: string;
  availableSlots: number;
  ects: number;
  usCredits: number;
  semesterWeekHours: number;
  duration: number;
  courseStatus: string;

  lecturer: Lecturer;
  department: Department;
  location: Campus;
  courseAppointments: CourseAppointment[];

}

export class CourseAppointment {

  id: number;
  weekday: number;
  startHour: number;
  startMinute: number;
  duration: number;

}
