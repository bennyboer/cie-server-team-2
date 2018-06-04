import {Component, OnInit} from '@angular/core';
import {User} from '../user';
import {Router} from '@angular/router';
import {UserService} from '../user.service';
import {MatSnackBar, MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {

  users: User[] = null;

  displayedColumns = ['id', 'firstName', 'lastName', 'email', 'admin', 'actions'];
  dataSource: MatTableDataSource<User> = null;

  constructor(private router: Router, private userService: UserService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.userService.getUsers()
      .subscribe(data => {
        this.users = data;
        this.dataSource = new MatTableDataSource<User>(this.users);
      });
  }

  deleteUser(user: User): void {
    this.userService.deleteUser(user)
      .subscribe(data => {
        this.users = this.users.filter(u => u !== user);
        this.dataSource = new MatTableDataSource<User>(this.users);

        this.snackBar.open('User with id ' + user.id + ' has been deleted.', 'OK', {
          duration: 1000
        });
      });
  }

  editUser(user: User): void {
    this.router.navigate(['/users/edit', user.id]);
  }

  createUser(): void {
    this.router.navigate(['/users/create']);
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

}
