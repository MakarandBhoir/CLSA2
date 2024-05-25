import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from '../model/student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-list-students',
  templateUrl: './list-students.component.html',
  styleUrls: []
})
export class ListStudentsComponent implements OnInit {
  students: Student[];

  constructor(private service: StudentService, private route: ActivatedRoute, private route2: Router) {
  }

  ngOnInit() {
    this.students = this.route.snapshot.data['students'];
    //console.log('Hello: '+this.students);
  }

  // logOff user
  logout(): void {
    if (sessionStorage.getItem("username") != null) {
      sessionStorage.removeItem("username");
      this.route2.navigate(['/login']);
    }
  }

  // Delete Student
  deleteStudent(student: Student): void {
    let result = confirm('Do you want to delete the student?')
    if (result) {
      this.service.deleteStudent(student.studentId)
        .subscribe(data => {
          this.students = this.students.filter(s => s !== student);
        });
    }
  };

  // Modify Student
  editStudent(student: Student): void {
    localStorage.removeItem("editStudentId");
    localStorage.setItem("editStudentId", student.studentId.toString());
    this.route2.navigate(['edit-student']);
  };

}
