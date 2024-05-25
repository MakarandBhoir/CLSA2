import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddStudentCanDeactivateGuardService } from './student/guards/add-student-can-deactivate-guard-service';
import { AddStudentComponent } from './student/add-student/add-student.component';
import { EditStudentCanActivateGuard } from './student/guards/edit-student-can-activate-guard-service';
import { EditStudentComponent } from './student/edit-student/edit-student.component';
import { ListStudentsComponent } from './student/list-students/list-students.component';
import { LoginComponent } from './student/login/login.component';
import { StudentResolver } from './student/guards/student-resolver';
import { StudentCanActivateGuardService } from './student/guards/student-can-activate-guard-service';


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path:'login', component: LoginComponent},
  {
    path: 'list-students',
    component: ListStudentsComponent, resolve: {
      students: StudentResolver
    }, canActivate: [StudentCanActivateGuardService]
  },
  {
    path: 'add-student', component: AddStudentComponent,
    canDeactivate: [AddStudentCanDeactivateGuardService],
    canActivate: [StudentCanActivateGuardService]
  },
  {
    path:'edit-student', component:EditStudentComponent, 
    canActivate: [StudentCanActivateGuardService, EditStudentCanActivateGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
