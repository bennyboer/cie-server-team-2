import { Component, OnInit } from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Department} from './department';
import {DepartmentService} from './department.service';
import {DepartmentDialogComponent} from './department-dialog/department-dialog.component';

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent implements OnInit {

  displayedColumns = ['id', 'number', 'name', 'color', 'actions'];

  departments: Department[] = null;
  dataSource: MatTableDataSource<Department> = null;

  constructor(private dialog: MatDialog, private departmentService: DepartmentService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.departmentService.getDepartments()
      .subscribe(data => {
        this.departments = data;
        this.dataSource = new MatTableDataSource<Department>(this.departments);
      });
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  createDepartment() {
    const dialogRef = this.dialog.open(DepartmentDialogComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined && result != null) {
        this.departmentService.createDepartment(result).subscribe((dep: Department) => {
          this.snackBar.open('Department with id ' + dep.id + ' has been created.', 'OK', {
            duration: 2000
          });

          this.departments.push(dep);
          this.dataSource = new MatTableDataSource<Department>(this.departments);
        });
      }
    });
  }

  editDepartment(department: Department) {
    const dialogRef = this.dialog.open(DepartmentDialogComponent, {
      width: '500px',
      data: department
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined && result != null) {
        this.departmentService.updateDepartment(result).subscribe((d: Department) => {
          this.snackBar.open('Department with id ' + d.id + ' has been updated.', 'OK', {
            duration: 2000
          });

          this.departments = this.departments.filter(dep => dep.id !== d.id);
          this.departments.push(d);
          this.dataSource = new MatTableDataSource<Department>(this.departments);
        });
      }
    });
  }

  deleteDepartment(department: Department) {
    this.departmentService.deleteDepartment(department)
      .subscribe(data => {
        this.departments = this.departments.filter(c => c !== department);
        this.dataSource = new MatTableDataSource<Department>(this.departments);

        this.snackBar.open('Department with id ' + department.id + ' has been deleted.', 'OK', {
          duration: 1000
        });
      });
  }

  getHexStringFromInt(color: number): string {
    return '#' + color.toString(16).padStart(6, '0');
  }

}
