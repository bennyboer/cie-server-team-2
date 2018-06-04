import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Department} from '../department';

@Component({
  selector: 'app-department-dialog',
  templateUrl: './department-dialog.component.html',
  styleUrls: ['./department-dialog.component.css']
})
export class DepartmentDialogComponent implements OnInit {

  department: Department = new Department();

  constructor(private dialogRef: MatDialogRef<DepartmentDialogComponent>, @Inject(MAT_DIALOG_DATA) public d: Department) {
    if (d !== undefined && d !== null) {
      this.department = d;
    }
  }

  ngOnInit() {
  }

  finish() {
    this.dialogRef.close(this.department);
  }

}
