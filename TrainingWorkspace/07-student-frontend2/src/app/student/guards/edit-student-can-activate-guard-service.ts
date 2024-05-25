import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

export class EditStudentCanActivateGuard implements CanActivate {
    //constructor(private router2:Router){}
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        //alert('CanActivate Guard');
        let studentId = localStorage.getItem("editStudentId");
        if (!studentId) {
            alert("Invalid action.")
      //     this.router2.navigate(['list-students']);
            return false;
        }
        return true;
    }

}