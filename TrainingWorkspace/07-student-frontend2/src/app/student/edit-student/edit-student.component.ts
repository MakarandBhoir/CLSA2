import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Address } from '../model/address';
import { Student } from '../model/student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-edit-student',
  templateUrl: './edit-student.component.html',
  styleUrls: []
})
export class EditStudentComponent implements OnInit {
  public studentForm: FormGroup;
  constructor(private fb: FormBuilder, private service: StudentService, private router: Router) {
  }

  ngOnInit() {
    alert('EditStudentComponent');
    //if(localStorage.getItem("username")!=null){
    // if (true) { 
    // let studentId = localStorage.getItem("editStudentId");
    // if (!studentId) {
    //   alert("Invalid action.")
    //   this.router.navigate(['list-students']);
    //   return;
    // }

    this.studentForm = this.fb.group({
      studentId: ['', Validators.required],
      studentName: ['', Validators.required],
      score: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      pin: ['', Validators.required],
    });

    this.service.getStudentById(+localStorage.getItem("editStudentId"))
      .subscribe(data => {
        this.studentForm.setValue(data);
      });
    //}
    // else
    //     this.router.navigate(['/login']);
  }

  updateStudent() {
    //this.submitted = true;
    if (this.studentForm.invalid) {
      return;
    }
    let address: Address = new Address(this.studentForm.controls.city.value,
      this.studentForm.controls.state.value, this.studentForm.controls.pin.value);

    let student: Student = new Student(this.studentForm.controls.studentId.value,
      this.studentForm.controls.studentName.value,
      this.studentForm.controls.score.value, address);

    this.service.updateStudent(student)
      //.pipe(first())
      .subscribe(
        data => {
          this.router.navigate(['list-students']);
        },
        error => {
          alert(error);
        });
  }
}
