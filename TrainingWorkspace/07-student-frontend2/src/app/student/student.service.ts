import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from './model/login';
import { Student } from './model/student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  basePath: string = "http://localhost:9091/student-api/students/";

  constructor(private http: HttpClient) {
  }
  loginService(login: Login) {
    //alert("loginService: "+JSON.stringify(login));
    //return this.http.post("http://localhost:9091/student-api/user/login.do", login);
    return this.http.post("http://localhost:9091/student-api/students/login.do", login);
  }
  addStudent(student: Student): Observable<Object> {
    return this.http.post(this.basePath, student, { responseType: 'text' });
  }
  getStudentById(studentId: number): Observable<Student> {
    return this.http.get<Student>(this.basePath + '' + studentId);
  }
  getAllStudents(): Observable<Student[]> {
    // the result of get function. get fun will give you Observable<Student[]>
    return this.http.get<Student[]>(this.basePath);
  }
  deleteStudent(studentId: number): Observable<Student> {
    return this.http.delete<Student>(this.basePath + '' + studentId);
  }
  updateStudent(student: Student): Observable<Object> {
    return this.http.put(this.basePath, student);
  }

}
