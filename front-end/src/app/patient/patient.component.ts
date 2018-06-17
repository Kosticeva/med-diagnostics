import { Component, OnInit } from '@angular/core';
import { Patient } from '../model/patient';
import { PatientService } from '../services/patient.service';
import { ChartService } from '../services/chart.service';
import { Chart } from '../model/chart';
import { Allergen } from '../model/allergen';
import { AllergyService } from '../services/allergy.service';
import { Examination } from '../model/examination';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { LoginService } from '../services/login.service';
import { ExamService } from '../services/exam.service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  newAllergyOpen: boolean;
  changeFormOpen: boolean;

  newPatient: Patient;
  chart: Chart;
  allergies: string;
  error: {
    firstName: string,
    lastName: string
  };

  constructor(
    private patientService: PatientService,
    private chartService: ChartService,
    private allergyService: AllergyService,
    private loginService: LoginService,
    private http: HttpClient,
    private router: Router,
    private examService: ExamService
  ) { }

  ngOnInit() {
    this.newAllergyOpen = false;
    this.changeFormOpen = false;
    this.error = {
      firstName: "",
      lastName: ""
    };
    this.chart = {
      patient: {
        firstName: '',
        lastName: '',
        id: null,
        allergens: []
      },
      examinations: [],
      id: null
    };
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
    let loc = window.location.href;
    const parts = loc.split('/');
    this.chartService.get(parts[parts.length-1]).subscribe(
      (data) => {
        this.chart = data;
        this.stringTheAllergies(this.chart.patient.allergens);
        this.newPatient = this.chart.patient;
      }
    );
  }

  stringTheAllergies(allergies: Allergen[]){
    this.allergies = "";
    for(let i=0; i<allergies.length; i++){
      this.allergies += allergies[i].name+', ';
    }
  }

  openNewAllergy(){
    this.newAllergyOpen = !this.newAllergyOpen;
  }

  openChangeForm() {
    this.changeFormOpen = !this.changeFormOpen;
  }

  changePatient() {
    this.error.firstName = "";

    if(this.newPatient.firstName === ""){
      this.error.firstName = "Morate uneti ime za pacijenta";
      return;
    }

    this.error.firstName = "";

    if(this.newPatient.lastName === ""){
      this.error.lastName = "Morate uneti prezime za korisnika";
      return;
    }

    this.error.lastName = "";

    this.patientService.put(this.newPatient, this.chart.patient.id).subscribe(
      (data) => {
        this.chart.patient = data;
      }
    )
  }

  deleteExam(ex: Examination) {
    this.examService.delete(ex.id).subscribe(
      (data) => {
        this.chart.examinations.splice(this.chart.examinations.indexOf(ex), 1);
        this.chartService.put(this.chart, this.chart.id).subscribe(
          (data) => {
            this.chart = data;
          }
        )
      }
    )
  }

}
