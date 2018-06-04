import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material';
import {Course} from '../course';

@Component({
  selector: 'app-course-dialog',
  templateUrl: './course-dialog.component.html',
  styleUrls: ['./course-dialog.component.css']
})
export class CourseDialogComponent implements OnInit {

  course: Course = new Course();

  constructor(public dialogRef: MatDialogRef<CourseDialogComponent>) { }

  ngOnInit() {
  }

  finish() {
    this.dialogRef.close(this.course);
  }

}
