import { Component, OnInit } from '@angular/core';
import { Patient } from '../model/patient';
import { PatientService } from '../services/patient.service';
import { ChartService } from '../services/chart.service';
import { Chart } from '../model/chart';
import { Allergen } from '../model/allergen';
import { AllergyService } from '../services/allergy.service';
import { Examination } from '../model/examination';

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
    private allergyService: AllergyService
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
    this.newPatient = new Patient(this.chart.patient.firstName, this.chart.patient.lastName, this.chart.patient.allergens, this.chart.patient.id);
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

  addAllergy() {
    this.error.firstName = "";
    this.newPatient.allergens = [];

    let parts = this.allergies.split(',');
    for(let i=0; i<parts.length; i++){
      const part = parts[i].trim();
      let a = new Allergen(null, part);
      this.allergyService.post(a).subscribe(
        (data) => this.newPatient.allergens.push(data)
      );
    }

    this.patientService.put(this.newPatient, this.newPatient.id).subscribe(
      (data) => {
        this.chart.patient = data;
        this.newPatient = data;
      },
      error => this.error.firstName = "Nesto je krenulo po zlu");

    this.newAllergyOpen = false;
  }

  editPatient() {

  }

  changeExam(ex: Examination) {

  }

  deleteExam(ex: Examination) {

  }

}
