import { Component, OnInit } from '@angular/core';
import {MatDialog, MatTableDataSource} from '@angular/material';
import {User} from '../user/user';
import {Campus} from './campus';
import {CampusDialogComponent} from './campus-dialog/campus-dialog.component';

@Component({
  selector: 'app-campus',
  templateUrl: './campus.component.html',
  styleUrls: ['./campus.component.css']
})
export class CampusComponent implements OnInit {

  displayedColumns = ['id', 'name', 'actions'];

  campuses: Campus[] = null;
  dataSource: MatTableDataSource<User> = null;

  constructor(private dialog: MatDialog) { }

  ngOnInit() {
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  createCampus() {
    const dialogRef = this.dialog.open(CampusDialogComponent, {
      height: '80%',
      width: '50%',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  editCampus() {
    console.log('Edit campus');
  }

  deleteCampus() {
    console.log('Delete campus');
  }

}
