import {Component, OnInit} from '@angular/core';
import {MatDialogRef, MatSnackBar} from '@angular/material';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-course-import-dialog',
  templateUrl: './course-import-dialog.component.html',
  styleUrls: ['./course-import-dialog.component.css']
})
export class CourseImportDialogComponent implements OnInit {

  xmlUrl: string;

  loading = false;

  constructor(public dialogRef: MatDialogRef<CourseImportDialogComponent>, private http: HttpClient, private snackBar: MatSnackBar) {
  }

  finish() {
    this.dialogRef.close();
  }

  ngOnInit(): void {
  }

  importFromXML() {
    this.loading = true;
    this.http.post('/api/courses/import/xml', this.xmlUrl).subscribe(result => {
      this.loading = false;

      this.snackBar.open('Courses imported successfully.', 'OK', {
        duration: 3000
      });

      this.finish();
    }, e => {
      this.loading = false;

      this.snackBar.open('An error happened when trying to import courses from XML.', 'OK', {
        duration: 4000
      });
    });
  }

  importFromNine() {
    this.loading = true;

    this.http.get('/api/courses/import/nine').subscribe(result => {
      this.loading = false;

      this.snackBar.open('Courses imported successfully.', 'OK', {
        duration: 3000
      });

      this.finish();
    }, e => {
      this.loading = false;

      this.snackBar.open('An error happened when trying to import courses from NINE.', 'OK', {
        duration: 4000
      });
    });
  }

}
