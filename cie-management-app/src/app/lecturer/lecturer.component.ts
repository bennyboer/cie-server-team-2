import {Component, OnInit} from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {LecturerDialogComponent} from '../lecturer/lecturer-dialog/lecturer-dialog.component';
import {LecturerService} from '../lecturer/lecturer.service';
import {Lecturer} from '../lecturer/lecturer';

@Component({
  selector: 'app-lecturer',
  templateUrl: './lecturer.component.html',
  styleUrls: ['./lecturer.component.css']
})
export class LecturerComponent implements OnInit {

  displayedColumns = ['id', 'name', 'email', 'actions'];

  lecturers: Lecturer[] = null;
  dataSource: MatTableDataSource<Lecturer> = null;

  constructor(private dialog: MatDialog, private lecturerService: LecturerService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.lecturerService.getLecturers()
      .subscribe(data => {
        this.lecturers = data;
        this.dataSource = new MatTableDataSource<Lecturer>(this.lecturers);
      });
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  createLecturer() {
    const dialogRef = this.dialog.open(LecturerDialogComponent, {
      width: '500px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined && result != null) {
        this.lecturerService.createLecturer(result).subscribe((lec: Lecturer) => {
          this.snackBar.open('Lecturer with id ' + lec.id + ' has been created.', 'OK', {
            duration: 2000
          });

          this.lecturers.push(lec);
          this.dataSource = new MatTableDataSource<Lecturer>(this.lecturers);
        });
      }
    });
  }

  editLecturer(lecturer: Lecturer) {
    const dialogRef = this.dialog.open(LecturerDialogComponent, {
      width: '500px',
      data: lecturer
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined && result != null) {
        this.lecturerService.updateLecturer(result).subscribe((l: Lecturer) => {
          this.snackBar.open('Lecturer with id ' + l.id + ' has been updated.', 'OK', {
            duration: 2000
          });

          this.lecturers = this.lecturers.filter(lec => lec.id !== l.id);
          this.lecturers.push(l);
          this.dataSource = new MatTableDataSource<Lecturer>(this.lecturers);
        });
      }
    });
  }

  deleteLecturer(lecturer: Lecturer) {
    this.lecturerService.deleteLecturer(lecturer)
      .subscribe(data => {
        this.lecturers = this.lecturers.filter(c => c !== lecturer);
        this.dataSource = new MatTableDataSource<Lecturer>(this.lecturers);

        this.snackBar.open('Lecturer with id ' + lecturer.id + ' has been deleted.', 'OK', {
          duration: 1000
        });
      });
  }
}
