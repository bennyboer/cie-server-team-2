import {Component, OnInit} from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Course} from './course';
import {CourseService} from './course.service';
import {CourseDialogComponent} from './course-dialog/course-dialog.component';
import {UserService} from '../user/user.service';
import {CourseSelectionsDialogComponent} from './course-selections-dialog/course-selections-dialog.component';
import {YesNoDialogComponent} from '../util/yes-no-dialog/yes-no-dialog.component';
import {CourseAppointmentService} from './course-appointment.service';
import {CourseImportDialogComponent} from './course-import-dialog/course-import-dialog.component';

@Component({
  selector: 'app-course-management',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  displayedColumns = [
    'id',
    'name',
    'description',
    'room',
    'availableSlots',
    'ects',
    'usCredits',
    'semesterWeekHours',
    'courseStatus',
    'lecturer',
    'lecturer',
    'location',
    'actions'
  ];

  courses: Course[] = null;
  dataSource: MatTableDataSource<Course> = null;

  constructor(private snackBar: MatSnackBar,
              private courseService: CourseService,
              private userService: UserService,
              private courseAppointmentService: CourseAppointmentService,
              private dialog: MatDialog) {
  }

  ngOnInit() {
    this.refresh();
  }

  refresh() {
    this.courseService.getCourses()
      .subscribe(data => {
        this.courses = data;
        this.dataSource = new MatTableDataSource<Course>(this.courses);
      });
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  createCourse() {
    const dialogRef = this.dialog.open(CourseDialogComponent, {
      height: '400px',
      width: '500px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined && result != null) {
        const appointments = result.courseAppointments;

        this.courseService.createCourse(result).subscribe((c: Course) => {
          this.snackBar.open('Course with id ' + c.id + ' has been created.', 'OK', {
            duration: 2000
          });

          this.courses.push(c);
          this.dataSource = new MatTableDataSource<Course>(this.courses);

          // Update appointments on server side.
          this.courseAppointmentService.updateCourseAppointments(c.id, appointments).subscribe(apps => {
            c.courseAppointments = apps;
          });
        });
      }
    });
  }

  editCourse(course: Course) {
    const dialogRef = this.dialog.open(CourseDialogComponent, {
      height: '400px',
      width: '500px',
      data: course
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined && result != null) {
        const appointments = result.courseAppointments;

        this.courseService.updateCourse(result).subscribe((c: Course) => {
          this.snackBar.open('Course with id ' + c.id + ' has been updated.', 'OK', {
            duration: 2000
          });

          this.courses = this.courses.filter(cou => cou.id !== c.id);
          this.courses.push(c);
          this.dataSource = new MatTableDataSource<Course>(this.courses);

          // Update appointments on server side.
          this.courseAppointmentService.updateCourseAppointments(c.id, appointments).subscribe(apps => {
            c.courseAppointments = apps;
          });
        });
      }
    });
  }

  deleteCourse(course: Course) {
    this.courseService.deleteCourse(course)
      .subscribe(data => {
        this.courses = this.courses.filter(c => c !== course);
        this.dataSource = new MatTableDataSource<Course>(this.courses);

        this.snackBar.open('Course with id ' + course.id + ' has been deleted.', 'OK', {
          duration: 1000
        });
      });
  }

  clearCourseSelections(): void {
    const dialogRef = this.dialog.open(YesNoDialogComponent, {
      data: 'Do you really want to delete all course selections? This cannot be undone.'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.courseService.deleteSelections().subscribe(success => {
          if (success) {
            this.snackBar.open('Deleted all course selections.', 'OK', {
              duration: 3000
            });
          }
        });
      }
    });
  }

  viewSelections(course: Course) {
    this.userService.getUsersWhoSelectedCourse(course).subscribe(users => {
      this.dialog.open(CourseSelectionsDialogComponent, {
        width: '500px',
        data: users
      });
    });
  }

  importCourses() {
    const dialogRef = this.dialog.open(CourseImportDialogComponent, {
      width: '700px'
    });

    dialogRef.afterClosed().subscribe(() => {
      this.refresh();
    });
  }

  startLottery(): void {
    this.courseService.startLottery().subscribe(result => {
      this.snackBar.open('Course lottery finished.', 'OK', {
        duration: 5000
      });
    });
  }

}
