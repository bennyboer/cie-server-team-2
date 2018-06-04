import {Component, OnInit} from '@angular/core';
import {User} from '../user';
import {Router} from '@angular/router';
import {UserService} from '../user.service';
import {FormControl, Validators} from '@angular/forms';
import {MyErrorStateMatcher} from '../../login/login.component';

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

  constructor(private router: Router, private userService: UserService) {
  }

  ngOnInit(): void {
  }

  createUser(): void {
    this.userService.createUser(this.user)
      .subscribe(data => {
        this.router.navigate(['/users/overview']);
      });
  }

  back(): void {
    this.router.navigate(['/users']);
  }

}
