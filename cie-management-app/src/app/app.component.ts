import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from './login/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  title = 'CiE - Management Interface';

  isLoggedIn = false;
  private loggedInObservable: any = null;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isAuthenticated();

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

  navigateTo(url: string): void {
    this.router.navigateByUrl(url);
  }

}
