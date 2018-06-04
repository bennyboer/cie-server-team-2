import {Component, OnInit} from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Campus} from './campus';
import {CampusDialogComponent} from './campus-dialog/campus-dialog.component';
import {CampusService} from './campus.service';

@Component({
  selector: 'app-campus',
  templateUrl: './campus.component.html',
  styleUrls: ['./campus.component.css']
})
export class CampusComponent implements OnInit {

  displayedColumns = ['id', 'name', 'actions'];

  campuses: Campus[] = null;
  dataSource: MatTableDataSource<Campus> = null;

  constructor(private dialog: MatDialog, private campusService: CampusService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.campusService.getCampuses()
      .subscribe(data => {
        this.campuses = data;
        this.dataSource = new MatTableDataSource<Campus>(this.campuses);
      });
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  createCampus() {
    const dialogRef = this.dialog.open(CampusDialogComponent, {
      height: '400px',
      width: '500px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined && result != null) {
        this.campusService.createCampus(result).subscribe((campus: Campus) => {
          this.snackBar.open('Campus with id ' + campus.id + ' has been created.', 'OK', {
            duration: 2000
          });

          this.campuses.push(campus);
          this.dataSource = new MatTableDataSource<Campus>(this.campuses);
        });
      }
    });
  }

  editCampus(campus: Campus) {
    const dialogRef = this.dialog.open(CampusDialogComponent, {
      height: '400px',
      width: '500px',
      data: campus
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined && result != null) {
        this.campusService.updateCampus(result).subscribe((c: Campus) => {
          this.snackBar.open('Campus with id ' + c.id + ' has been updated.', 'OK', {
            duration: 2000
          });

          this.campuses = this.campuses.filter(camp => camp.id !== c.id);
          this.campuses.push(c);
          this.dataSource = new MatTableDataSource<Campus>(this.campuses);
        });
      }
    });
  }

  deleteCampus(campus: Campus) {
    this.campusService.deleteCampus(campus)
      .subscribe(data => {
        this.campuses = this.campuses.filter(c => c !== campus);
        this.dataSource = new MatTableDataSource<Campus>(this.campuses);

        this.snackBar.open('Campus with id ' + campus.id + ' has been deleted.', 'OK', {
          duration: 1000
        });
      });
  }

}
