import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-course-appointment-dialog',
  templateUrl: './course-appointment-dialog.component.html',
  styleUrls: ['./course-appointment-dialog.component.css']
})
export class CourseAppointmentDialogComponent implements OnInit {

  date: Date;
  duration: number;

  isEdit = false;

  constructor(public dialogRef: MatDialogRef<CourseAppointmentDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {
    if (data !== undefined && data !== null) {
      this.date = data.date;
      this.duration = data.duration;
      this.isEdit = true;
    }
  }

  finish() {
    this.dialogRef.close({
      date: this.date,
      duration: this.duration
    });
  }

  ngOnInit(): void {
  }

}
