import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Lecturer} from '../lecturer';

@Component({
  selector: 'app-lecturer-dialog',
  templateUrl: './lecturer-dialog.component.html',
  styleUrls: ['./lecturer-dialog.component.css']
})
export class LecturerDialogComponent implements OnInit {

  lecturer: Lecturer = new Lecturer();

  constructor(private dialogRef: MatDialogRef<LecturerDialogComponent>, @Inject(MAT_DIALOG_DATA) public l: Lecturer) {
    if (l !== undefined && l !== null) {
      this.lecturer = l;
    }
  }

  ngOnInit() {
  }

  finish() {
    this.dialogRef.close(this.lecturer);
  }

}
