import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from './auth.service';
import {Location} from '@angular/common';
import {Router} from '@angular/router';
import {ErrorStateMatcher, MatSnackBar} from '@angular/material';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new MyErrorStateMatcher();

  email: string;
  password: string;

  showError = false;

  constructor(private authService: AuthService, private router: Router, private location: Location, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
  }

  tryLogin() {
    this.authService.login(this.email, this.password).subscribe(token => {
      if (token.isAdmin) {
        this.location.replaceState('/');
        this.router.navigateByUrl('/dashboard');
        this.snackBar.open('Logged in successfully!', 'OK', {
          duration: 2000
        })
      } else {
        this.showError = true;
        this.snackBar.open('The user you tried to log in with does not have administrator privileges.', 'OK', {
          duration: 3000
        });
      }
    }, error => {
      this.showError = true;
      this.snackBar.open('The given credentials do not apply to any user. Try again.', 'OK', {
        duration: 3000
      });
    });
  }

}

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
