import {Component, OnInit} from '@angular/core';
import {MatDialog, MatDialogRef, MatSnackBar, MatTableDataSource} from '@angular/material';
import {User} from '../user/user';
import {Course} from './course';
import {CourseService} from './course.service';
import {CourseDialogComponent} from './course-dialog/course-dialog.component';

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
    'semesterWeekHourse',
    'courseStatus',
    'lecturer',
    'department',
    'location',
    'actions'
  ];

  courses: Course[] = null;
  dataSource: MatTableDataSource<User> = null;

  constructor(private snackBar: MatSnackBar, private courseService: CourseService, private dialog: MatDialog) {
  }

  ngOnInit() {
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
      console.log(`Dialog result: ${result}`);
    });
  }

  editCourse() {
    console.log('Edit course');
  }

  deleteCourse() {
    console.log('Delete course');
  }

}
