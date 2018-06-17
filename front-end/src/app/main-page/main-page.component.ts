import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private router: Router,
    private loginService: LoginService
  ) { }

  ngOnInit() {
   this.http.get('http://localhost:8080/authenticate/'+this.loginService.getDoctor()).subscribe(
      (data) => {
        //
      },
      error => {
        this.router.navigate(['/login']);
      }
    );
  }

}
