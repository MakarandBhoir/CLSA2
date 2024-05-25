import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Login } from '../model/login';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: []
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  submitted: boolean = false;
  invalidLogin: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private router2: ActivatedRoute, private service: StudentService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['test@test.com', Validators.required],
      password: ['test', Validators.required]
    });
  }

  login() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    let login: Login = new Login(this.loginForm.controls.username.value, this.loginForm.controls.password.value);

    this.service.loginService(login).subscribe(data => {
      //alert('data: '+JSON.stringify(data["jwt"]));
      sessionStorage.setItem("username", data["jwt"]);
      this.router.navigate(['list-students']);
    },
      error => {
        alert('Invalid login/password entered');
      })
  }
}
