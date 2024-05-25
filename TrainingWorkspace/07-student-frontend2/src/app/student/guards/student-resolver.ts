import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Student } from '../model/student';
import { StudentService } from '../student.service';
@Injectable()
export class StudentResolver implements Resolve<Student[]>
{
    constructor(private service: StudentService) {
    }
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        // return "Hello World!";
        return this.service.getAllStudents();
    }
}