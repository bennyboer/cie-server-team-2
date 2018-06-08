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
  color: string;

  constructor(private dialogRef: MatDialogRef<DepartmentDialogComponent>, @Inject(MAT_DIALOG_DATA) public d: Department) {
    if (d !== undefined && d !== null) {
      this.department = d;

      // Convert to hex string color
      if (this.department.color !== undefined && this.department.color !== null) {
        this.color = '#' + this.department.color.toString(16).padStart(6, '0');
      }
    }
  }

  ngOnInit() {
  }

  finish() {
    if (this.color !== undefined && this.color !== null && this.color.length > 0) {
      // Convert color hex string to number
      this.department.color = parseInt(this.color.substring(1, this.color.length), 16);
    }

    this.dialogRef.close(this.department);
  }

}
