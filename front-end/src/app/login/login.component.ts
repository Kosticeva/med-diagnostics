import { Component, OnInit } from '@angular/core';
import { Doctor } from '../model/doctor';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errors : {
    username: string,
    password: string,
    main: string
  };

  data: Doctor;

  constructor(
    private loginService: LoginService,
    private router: Router
  ) { }

  ngOnInit() {
    this.data = {
      licenceId: -1,
      username: '',
      password: '',
      firstName: '',
      lastName: '',
      type: 'REGULAR'
    };
    this.errors = {
      username: '',
      password: '',
      main:''
    }
  }

  logIn() {
    if(this.data.username === "") {
      this.errors.username = "Morate uneti korisnicko ime";
      return;
    }

    this.errors.username = "";
    
    if(this.data.password === ""){
      this.errors.password = "Morate uneti sifru";
      return;
    }

    this.errors.password = "";
    
    this.loginService.login(this.data);
  }
}
