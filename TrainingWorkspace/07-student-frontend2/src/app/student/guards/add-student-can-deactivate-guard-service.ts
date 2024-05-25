import { Injectable } from '@angular/core';
import { CanDeactivate } from '@angular/router';
import { AddStudentComponent } from '../add-student/add-student.component';

@Injectable()
export class AddStudentCanDeactivateGuardService implements CanDeactivate<AddStudentComponent>
{
    canDeactivate(component: AddStudentComponent): boolean
    {
        if(component.studentForm.dirty){
            return confirm('Are you sure?');
        }else{
            return true;
        }
    }
}