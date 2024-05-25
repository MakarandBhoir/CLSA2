import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

export class StudentCanActivateGuardService implements CanActivate {
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        //alert('Activating: '+sessionStorage.getItem("username"));
        if (sessionStorage.getItem("username") == null) {
            alert('Login to access this page.');
            return false;
        } else {
            return true;
        }
    }

}