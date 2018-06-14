import { Component, OnInit } from '@angular/core';
import { Patient } from '../model/patient';
import { PatientService } from '../services/patient.service';
import { ChartService } from '../services/chart.service';
import { Chart } from '../model/chart';
import { Allergen } from '../model/allergen';
import { AllergyService } from '../services/allergy.service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  newAllergyOpen: boolean;
  changeFormOpen: boolean;

  patient: Patient;
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
    this.error.firstName = "";
    this.error.lastName = "";
    this.chartService.get(this.patient.id).subscribe(
      (data) => this.chart = data
    );
  }

  openNewAllergy(){
    this.newAllergyOpen = !this.newAllergyOpen;
  }

  openChangeForm() {
    this.newPatient = new Patient(this.patient.firstName, this.patient.lastName, this.patient.allergens, this.patient.id);
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

    this.patientService.put(this.newPatient, this.patient.id).subscribe(
      (data) => {
        this.patient = data;
      }
    )
  }

  addAllergy() {
    this.error.firstName = "";
    this.patient.allergens = [];

    let parts = this.allergies.split(',');
    for(let i=0; i<parts.length; i++){
      const part = parts[i].trim();
      let a = new Allergen(null, part);
      this.allergyService.post(a).subscribe(
        (data) => this.patient.allergens.push(data)
      );
    }

    this.patientService.put(this.patient, this.patient.id).subscribe(
      (data) => {

      },
      error => this.error.firstName = "Nesto je krenulo po zlu");

    this.newAllergyOpen = false;
  }

}
