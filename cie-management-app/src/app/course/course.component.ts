import {Component, OnInit} from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Course} from './course';
import {CourseService} from './course.service';
import {CourseDialogComponent} from './course-dialog/course-dialog.component';
import {UserService} from '../user/user.service';

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

  constructor(private snackBar: MatSnackBar, private courseService: CourseService, private userService: UserService, private dialog: MatDialog) {
  }

  ngOnInit() {
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
      height: '80%',
      width: '50%',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined && result != null) {
        this.courseService.createCourse(result).subscribe((c: Course) => {
          this.snackBar.open('Course with id ' + c.id + ' has been created.', 'OK', {
            duration: 2000
          });

          this.courses.push(c);
          this.dataSource = new MatTableDataSource<Course>(this.courses);
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
        this.courseService.updateCourse(result).subscribe((c: Course) => {
          this.snackBar.open('Course with id ' + c.id + ' has been updated.', 'OK', {
            duration: 2000
          });

          this.courses = this.courses.filter(cou => cou.id !== c.id);
          this.courses.push(c);
          this.dataSource = new MatTableDataSource<Course>(this.courses);
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

  startLottery(): void {
    console.log('Start lottery');
  }

  clearCourseSelections(): void {
    console.log('Clear course selections.');
  }

  viewSelections(course: Course) {

  }

}
