import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Campus} from '../campus';

@Component({
  selector: 'app-campus-dialog',
  templateUrl: './campus-dialog.component.html',
  styleUrls: ['./campus-dialog.component.css']
})
export class CampusDialogComponent implements OnInit {

  campus: Campus = new Campus();

  constructor(private dialogRef: MatDialogRef<CampusDialogComponent>, @Inject(MAT_DIALOG_DATA) public c: Campus) {
    if (c !== undefined && c !== null) {
      this.campus = c;
    }
  }

  ngOnInit() {
  }

  finish() {
    this.dialogRef.close(this.campus);
  }

}
