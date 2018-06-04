import {Directive, OnDestroy} from '@angular/core';
import {AuthService} from '../login/auth.service';
import {Router} from '@angular/router';
import {Location} from '@angular/common';
import {MatSnackBar} from '@angular/material';

@Directive({
  selector: '[protected]'
})
export class ProtectedDirective implements OnDestroy {

  private sub: any = null;

  constructor(private authService: AuthService, private router: Router, private location: Location, private snackBar: MatSnackBar) {
    if (!authService.isAuthenticated()) {
      this.location.replaceState('/'); // Clears browser history so they can't navigate with back button
      this.router.navigateByUrl('/login');

      authService.initialize().subscribe(result => {
        if (result != null) {
          alert('There is no administrator currently set, so a default one has been created. ' +
            'Log in with: \nEmail: ' + result.email + '\nPassword: admin');
        }
      });
    }

    this.sub = this.authService.loggedIn().subscribe((loggedIn) => {
      if (!loggedIn) {
        this.snackBar.open('You have been logged out.', 'OK', {
          duration: 3000
        });

        this.location.replaceState('/'); // Clears browser history so they can't navigate with back button
        this.router.navigateByUrl('/login');
      }
    });
  }

  ngOnDestroy() {
    if (this.sub != null) {
      this.sub.unsubscribe();
    }
  }
}
