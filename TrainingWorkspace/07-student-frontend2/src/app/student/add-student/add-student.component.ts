import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Address } from '../model/address';
import { Student } from '../model/student';
import { StudentService } from '../student.service';

@Component({
  selector: 'add-student',
  templateUrl: './add-student.component.html',
  styleUrls: []
})
export class AddStudentComponent implements OnInit {
  public studentForm: FormGroup;
  constructor(private fb: FormBuilder, private service: StudentService, private router: Router) {

  }

  ngOnInit() {
    this.studentForm = this.fb.group({
      studentId: ['', Validators.required],
      studentName: ['', Validators.required],
      score: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      pin: ['', Validators.required],

    });
  }

  addStudent(): void {
    let address: Address = new Address(this.studentForm.controls.city.value,
      this.studentForm.controls.state.value, this.studentForm.controls.pin.value);

    let student: Student = new Student(this.studentForm.controls.studentId.value,
      this.studentForm.controls.studentName.value,
      this.studentForm.controls.score.value, address);

    this.service.addStudent(student).subscribe(data => {
      alert('Student Data Added.');
      this.studentForm.markAsPristine();
      this.router.navigate(['list-students']);
    })
  }
}
