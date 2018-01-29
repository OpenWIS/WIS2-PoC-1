import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {environment} from "../../environments/environment";

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {
  }

  canActivate(next: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    // Check if the user has a JWT - if not, redirect to login.
    let jwt = sessionStorage.getItem(environment.CONSTANTS.JWT_STORAGE_NAME);
    if (!jwt) {
      this.router.navigate(['/login']);
      return false;
    } else {
      return true;
    }
  }
}
