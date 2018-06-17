import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Campus, Location} from '../campus';
import set = Reflect.set;

@Component({
  selector: 'app-campus-dialog',
  templateUrl: './campus-dialog.component.html',
  styleUrls: ['./campus-dialog.component.css']
})
export class CampusDialogComponent implements OnInit {

  campus: Campus = new Campus();
  base64Image: string;

  constructor(private dialogRef: MatDialogRef<CampusDialogComponent>, @Inject(MAT_DIALOG_DATA) public c: Campus) {
    if (c !== undefined && c !== null) {
      this.campus = c;
    } else {
      this.campus.location = new Location();
    }
  }

  ngOnInit() {
  }

  fileSelected(event) {
    const fileList: FileList = event.target.files;

    if (fileList.length === 1) {
      const file: File = fileList[0];

      const reader: FileReader = new FileReader();

      reader.onload = () => {
        this.base64Image = reader.result.split(',')[1];
      };

      reader.readAsDataURL(file);
    }
  }

  finish() {
    this.dialogRef.close({
      campus: this.campus,
      base64Image: this.base64Image
    });
  }

}
