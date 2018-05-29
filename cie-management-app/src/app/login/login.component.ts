import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from './auth.service';
import {Location} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string;
  password: string;

  showError = false;

  constructor(private authService: AuthService, private router: Router, private location: Location) { }

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
