import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from '../user';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {UserService} from '../user.service';
import {FormControl, Validators} from '@angular/forms';
import {MyErrorStateMatcher} from '../../login/login.component';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit, OnDestroy {

  sub: any;
  user: User = null;

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new MyErrorStateMatcher();

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService) {
  }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      const id = +params['id'];

      this.userService.getUser(id).subscribe(user => {
        this.user = user;
      });
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  submitUser(): void {
    this.userService.updateUser(this.user).subscribe(result => {
      this.router.navigate(['/users']);
    });
  }

  back(): void {
    this.router.navigate(['/users']);
  }

}
