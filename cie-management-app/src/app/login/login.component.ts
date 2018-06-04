import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from './auth.service';
import {Location} from '@angular/common';
import {Router} from '@angular/router';
import {ErrorStateMatcher} from '@angular/material';
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

  constructor(private authService: AuthService, private router: Router, private location: Location) {
  }

  ngOnInit() {
  }

  tryLogin() {
    this.authService.login(this.email, this.password).subscribe(result => {
      this.location.replaceState('/');
      this.router.navigateByUrl('/dashboard');
    }, error => {
      this.showError = true;
    });
  }

}

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
