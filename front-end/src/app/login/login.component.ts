import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { Doctor } from '../model/doctor';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  doctor: Doctor;
  errors = {
    username: '',
    password: ''
  };

  constructor(
    private loginService: LoginService
  ) { }

  ngOnInit() {
    this.doctor = new Doctor("", "", "", "", 0);
  }

  logIn() {
    if(this.doctor.username === "") {
      this.errors.username = "Morate uneti korisnicko ime";
      return;
    }

    this.errors.username = "";
    
    if(this.doctor.password === ""){
      this.errors.password = "Morate uneti sifru";
      return;
    }

    this.errors.password = "";
    
    this.loginService.login(this.doctor);
  }
}
