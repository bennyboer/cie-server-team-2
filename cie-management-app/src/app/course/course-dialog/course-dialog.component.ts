import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {Course, CourseAppointment} from '../course';
import {CourseStatus} from '../course-status';
import {Lecturer} from '../../lecturer/lecturer';
import {Department} from '../../department/department';
import {Campus} from '../../campus/campus';
import {CampusService} from '../../campus/campus.service';
import {DepartmentService} from '../../department/department.service';
import {LecturerService} from '../../lecturer/lecturer.service';
import {CourseAppointmentDialogComponent} from '../course-appointment-dialog/course-appointment-dialog.component';
import {CourseAppointmentService} from '../course-appointment.service';

@Component({
  selector: 'app-course-dialog',
  templateUrl: './course-dialog.component.html',
  styleUrls: ['./course-dialog.component.css']
})
export class CourseDialogComponent implements OnInit {

  weekdays = [
    'Sunday',
    'Monday',
    'Tuesday',
    'Wednesday',
    'Thursday',
    'Friday',
    'Saturday'
  ];

  course: Course = new Course();

  lecturers: Lecturer[] = [];
  departments: Department[] = [];
  campuses: Campus[] = [];

  appointments: DialogAppointment[] = [];

  isEdit = false;

  constructor(public dialogRef: MatDialogRef<CourseDialogComponent>,
              private campusService: CampusService,
              private departmentService: DepartmentService,
              private lecturerService: LecturerService,
              private courseAppointmentService: CourseAppointmentService,
              private dialog: MatDialog,
              @Inject(MAT_DIALOG_DATA) public c: Course) {
    if (c !== undefined && c !== null) {
      this.course = c;
      this.isEdit = true;
    }
  }

  ngOnInit() {
    this.campusService.getCampuses().subscribe(campuses => {
      this.campuses = campuses;

      if (this.course.location != null) {
        const c = this.campuses.find(campus => campus.id === this.course.location.id);
        if (c != null) {
          this.course.location = c;
        }
      }
    });

    this.departmentService.getDepartments().subscribe(departments => {
      this.departments = departments;

      if (this.course.department != null) {
        const d = this.departments.find(department => department.id === this.course.department.id);
        if (d != null) {
          this.course.department = d;
        }
      }
    });

    this.lecturerService.getLecturers().subscribe(lecturers => {
      this.lecturers = lecturers;

      if (this.course.lecturer != null) {
        const l = this.lecturers.find(lecturer => lecturer.id === this.course.lecturer.id);
        if (l != null) {
          this.course.lecturer = l;
        }
      }
    });

    if (this.isEdit) {
      // Transform real appointments to dialog appointments because they're easier to manipulate.
      this.appointments = [];

      for (const appointment of this.course.courseAppointments) {
        const newApp: DialogAppointment = new DialogAppointment();

        newApp.id = appointment.id;

        newApp.duration = appointment.duration;

        newApp.date = new Date();
        newApp.date.setHours(appointment.startHour);
        newApp.date.setMinutes(appointment.startMinute);
        newApp.room = appointment.room;

        // Transform to right day (sundays are 0 not 7).
        let day = appointment.weekday + 1;

        if (day === 8) {
          day = 0; // Sundays are zero.
        }

        // Set date to the the first day in the month (monday, thursday, wednesday, ...)
        while (newApp.date.getDay() !== day) {
          newApp.date.setDate(newApp.date.getDate() + 1);
        }

        this.appointments.push(newApp);
      }
    }
  }

  finish() {
    // Save appointments to course.
    const realAppointments: CourseAppointment[] = [];

    for (const app of this.appointments) {
      const appointment: CourseAppointment = new CourseAppointment();

      appointment.duration = app.duration;
      appointment.startHour = app.date.getHours();
      appointment.startMinute = app.date.getMinutes();
      appointment.room = app.room;

      let day = app.date.getDay(); // Sunday is zero, Monday is one, ...

      // Transform to monday == 0, thursday == 1, ...
      day = day - 1;

      if (day === -1) {
        day = 7;
      }

      appointment.weekday = day;

      appointment.id = app.id !== undefined && app.id != null ? app.id : null;

      realAppointments.push(appointment);
    }

    this.course.courseAppointments = realAppointments;

    this.dialogRef.close(this.course);
  }

  getStatusNames(): string[] {
    const names = [];
    for (const member in CourseStatus) {
      const isValueProperty = parseInt(member, 10) >= 0;
      if (!isValueProperty) {
        names.push(member);
      }
    }

    return names;
  }

  addAppointment(): void {
    const appointment = new DialogAppointment();

    this.appointments.push(appointment);

    const dialogRef = this.dialog.open(CourseAppointmentDialogComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined && result != null) {
        appointment.date = result.date;
        appointment.duration = result.duration;
        appointment.room = result.room;
      }
    });
  }

  deleteAppointment(appointment: DialogAppointment) {
    const index = this.appointments.indexOf(appointment, 0);
    if (index > -1) {
      this.appointments.splice(index, 1);
    }

    if (appointment.id !== undefined && appointment.id !== null) {
      this.courseAppointmentService.deleteAppointment(appointment.id).subscribe(result => {
        console.log('Deleted.');
      });
    }
  }

  editAppointment(appointment: DialogAppointment) {
    const dialogRef = this.dialog.open(CourseAppointmentDialogComponent, {
      data: {
        date: appointment.date,
        duration: appointment.duration,
        room: appointment.room
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined && result !== null) {
        appointment.date = result.date;
        appointment.duration = result.duration;
        appointment.room = result.room;
      }
    });
  }

}

export class DialogAppointment {
  id: number;
  date: Date;
  duration: number;
  room: string;
}
