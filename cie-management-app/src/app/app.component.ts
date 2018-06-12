import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from './login/auth.service';
import {Router} from '@angular/router';
import {MatDialog, MatSnackBar} from '@angular/material';
import {NotificationDialogComponent} from './notifications/notification-dialog/notification-dialog.component';
import {SettingsService} from './util/settings/settings.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  title = 'Courses in English';

  isLoggedIn = false;
  private loggedInObservable: any = null;

  private bgMusic: HTMLAudioElement = null;

  constructor(private authService: AuthService,
              private router: Router,
              private dialog: MatDialog,
              private snackBar: MatSnackBar,
              private settingsService: SettingsService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isAuthenticated();

    this.loggedInObservable = this.authService.loggedIn().subscribe(isLoggedIn => {
      this.isLoggedIn = isLoggedIn;
    });

    if (this.settingsService.backgroundMusicEnabled.value) {
      this.playMusic();
    }

    this.settingsService.backgroundMusicEnabled.observe().subscribe(isEnabled => {
      if (isEnabled) {
        this.playMusic();
      } else {
        this.bgMusic.pause();
      }
    });
  }

  private playMusic() {
    if (this.bgMusic === null) {
      this.bgMusic = new Audio('/assets/bg-music.mp3');
      this.bgMusic.controls = false;
      this.bgMusic.load();
      this.bgMusic.addEventListener('ended', () => {
        this.bgMusic.currentTime = 0;
        this.bgMusic.play();
      }, false);
    }

    this.bgMusic.currentTime = 0;
    this.bgMusic.play();
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
