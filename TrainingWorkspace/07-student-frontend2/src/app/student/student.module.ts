import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddStudentComponent } from './add-student/add-student.component';
import { StudentService } from './student.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ListStudentsComponent } from './list-students/list-students.component';
import { AddStudentCanDeactivateGuardService } from './guards/add-student-can-deactivate-guard-service';
import { StudentResolver } from './guards/student-resolver';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { StudentHttpInterceptor } from './student-http-intercetor';
import { EditStudentComponent } from './edit-student/edit-student.component';
import { EditStudentCanActivateGuard } from './guards/edit-student-can-activate-guard-service';
import { LoginComponent } from './login/login.component';
import { StudentCanActivateGuardService } from './guards/student-can-activate-guard-service';



@NgModule({
  declarations: [AddStudentComponent, ListStudentsComponent, EditStudentComponent, LoginComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [AddStudentComponent, ListStudentsComponent],
  providers: 
  [
    StudentService, AddStudentCanDeactivateGuardService, StudentResolver, EditStudentCanActivateGuard, StudentCanActivateGuardService,
    {provide: HTTP_INTERCEPTORS, useClass: StudentHttpInterceptor, multi: true}
  ]
})
export class StudentModule { }
