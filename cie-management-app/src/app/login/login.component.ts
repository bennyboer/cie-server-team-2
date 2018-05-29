import { Component, OnInit } from '@angular/core';
import {AuthService} from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string;
  password: string;

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  tryLogin() {
    this.authService.login(this.email, this.password).subscribe(result => {
      console.log(result);
    });
  }

}
