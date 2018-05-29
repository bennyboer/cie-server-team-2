import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from './login/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  title = 'CiE - Management Interface';

  isLoggedIn = false;
  private loggedInObservable: any = null;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.loggedInObservable = this.authService.loggedIn().subscribe(isLoggedIn => {
      this.isLoggedIn = isLoggedIn;
    });
  }

  ngOnDestroy(): void {
    if (this.loggedInObservable != null) {
      this.loggedInObservable.unsubscribe();
    }
  }

  logout(): void {
    this.authService.logout();
  }

}
