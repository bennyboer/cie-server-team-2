import {Directive, OnDestroy} from '@angular/core';
import {AuthService} from '../login/auth.service';
import {Router} from '@angular/router';
import {Location} from '@angular/common';

@Directive({
  selector: '[protected]'
})
export class ProtectedDirective implements OnDestroy {

  private sub: any = null;

  constructor(private authService: AuthService, private router: Router, private location: Location) {
    if (!authService.isAuthenticated()) {
      this.location.replaceState('/'); // Clears browser history so they can't navigate with back button
      this.router.navigate(['']);
      this.router.navigateByUrl('/login');
    }

    this.sub = this.authService.loggedIn().subscribe((loggedIn) => {
      if (!loggedIn) {
        alert('You have been logged out.');

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
