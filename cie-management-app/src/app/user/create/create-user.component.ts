import {Component, OnInit} from '@angular/core';
import {User} from '../user';
import {Router} from '@angular/router';
import {UserService} from '../user.service';
import {FormControl, Validators} from '@angular/forms';
import {MyErrorStateMatcher} from '../../login/login.component';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user: User = new User();

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new MyErrorStateMatcher();

  constructor(private router: Router, private userService: UserService, private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  createUser(): void {
    this.userService.createUser(this.user)
      .subscribe(user => {
        this.snackBar.open('User with id ' + user.id + ' has been created.', 'OK', {
          duration: 2000
        });

        this.router.navigate(['/users/overview']);
      });
  }

  back(): void {
    this.router.navigate(['/users']);
  }

}
