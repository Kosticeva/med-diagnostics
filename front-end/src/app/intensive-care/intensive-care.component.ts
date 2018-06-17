import { Component, OnInit } from '@angular/core';
import { IntensiveCareService } from '../services/intensive-care.service';
import { Patient } from '../model/patient';
import { LoginService } from '../services/login.service';
import { HttpClient } from '@angular/common/http';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-intensive-care',
  templateUrl: './intensive-care.component.html',
  styleUrls: ['./intensive-care.component.css']
})
export class IntensiveCareComponent implements OnInit {

  newPatientOpen: boolean;
  icPatients: Patient[];
  searching: boolean;

  constructor(
    private icService: IntensiveCareService,
    private loginService: LoginService,
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit() {
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
    this.searching = true;
    this.newPatientOpen = false;
    this.icService.getAllInIc().subscribe(
      (data) => {
        this.icPatients = data;
        this.searching = false;
      }
    );
  }

  navBack() {
    window.history.back();
  }

  openNewPatient() {
    this.newPatientOpen = !this.newPatientOpen;
  }

  removeIcPatient(id: number){
    this.icService.removeFromIc(id).subscribe(
      (data) => {
        this.searching = true;
        this.icService.getAllInIc().subscribe(
          (data1) => {
            this.icPatients = data1;
            this.searching = false;
          }
        )
      }
    );
  }

  doRefresh(){
    this.searching = true;
    this.icService.getAllInIc().subscribe(
      (data1) => {
        this.icPatients = data1;
        this.searching = false;
      }
    );
  }

}
