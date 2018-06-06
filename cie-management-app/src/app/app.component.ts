import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from './login/auth.service';
import {Router} from '@angular/router';
import {MatDialog, MatSnackBar} from '@angular/material';
import {NotificationDialogComponent} from './notifications/notification-dialog/notification-dialog.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  title = 'Courses in English';

  isLoggedIn = false;
  private loggedInObservable: any = null;

  constructor(private authService: AuthService,
              private router: Router,
              private dialog: MatDialog,
              private snackBar: MatSnackBar) {
  }

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

  sendNotification() {
    const dialogRef = this.dialog.open(NotificationDialogComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.snackBar.open('Notification has been successfully sent!', 'OK', {
          duration: 3000
        });
      }
    });
  }

}
