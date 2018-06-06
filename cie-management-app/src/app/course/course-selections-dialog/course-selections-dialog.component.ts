import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {User} from '../../user/user';

@Component({
  selector: 'app-course-selections-dialog',
  templateUrl: './course-selections-dialog.component.html',
  styleUrls: ['./course-selections-dialog.component.css']
})
export class CourseSelectionsDialogComponent implements OnInit {

  users: User[];

  constructor(public dialogRef: MatDialogRef<CourseSelectionsDialogComponent>, @Inject(MAT_DIALOG_DATA) public u: User[]) {
    if (u !== undefined && u !== null) {
      this.users = u;
    }
  }

  ngOnInit(): void {
  }

}
