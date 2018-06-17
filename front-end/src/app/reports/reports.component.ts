import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReportService } from '../services/report.service';
import { Patient } from '../model/patient';
import { LoginService } from '../services/login.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

  chronics: Patient[];
  addicts: Patient[];
  weaks: Patient[];
  finished: number;

  constructor(
    private reportsService: ReportService,
    private loginService: LoginService,
    private http: HttpClient,
    private router: Router
  ) { 
  }

  ngOnInit() {
    
    this.finished = 0;
    this.http.get('http://localhost:8080/authenticate/'+this.loginService.getDoctor()).subscribe(
      (data) => {
          if(this.loginService.getDoctor() === undefined){
            this.loginService.setDoctor();
          }
          this.start();
        },
      error => {
        this.router.navigate(['/login']);
      }
    );
  }

  start() {
    this.reportsService.addicts().subscribe(
      (data) => {
        this.addicts = data
        this.finished = this.finished +1;
      }
    );
    this.reportsService.weaks().subscribe(
      (data) => {
        this.weaks = data
        this.finished = this.finished +1;
      }
    );
    this.reportsService.chronics().subscribe(
      (data) => {
        this.chronics = data
        this.finished = this.finished +1;
      }
    )
  }

  navBack() {
    window.history.back();
  }

  getLink(id: number): string{
    return '../patient/'+id;
  }

}
