import { Component, OnInit } from '@angular/core';
import { PatientService } from '../../services/patient.service';
import { ChartService } from '../../services/chart.service';
import { Patient } from '../../model/patient';
import { AllergyService } from '../../services/allergy.service';
import { Allergen } from '../../model/allergen';

@Component({
  selector: 'app-new-patient',
  templateUrl: './new-patient.component.html',
  styleUrls: ['./new-patient.component.css']
})
export class NewPatientComponent implements OnInit {

  error: {
    firstName: string,
    lastName: string
  }
  patient: Patient;
  allergens: string;
  realAllergens: Allergen[];

  constructor(
    private patientService: PatientService,
    private chartService: ChartService,
    private allergyService: AllergyService
  ) { }

  ngOnInit() {
    this.error = {
      firstName: '',
      lastName: ''
    };

    this.patient = null;
    this.allergens = "";
    this.realAllergens = [];
  }

  createPatient() {
    this.error.firstName = "";

    if(this.patient.firstName === ""){
      this.error.firstName = "Morate uneti ime za pacijenta";
      return;
    }

    this.error.firstName = "";
    if(this.patient.lastName === ""){
      this.patient.lastName = "Morate uneti prezime za korisnika";
      return;
    }

    this.error.lastName = "";

    let parts = this.allergens.split(',');
    for(let i=0; i<parts.length; i++){
      const part = parts[i].trim();
      let a = new Allergen(null, part);
      this.allergyService.post(a).subscribe(
        (data) => this.realAllergens.push(data)
      );
    }

    this.patient.allergens = this.realAllergens;
    this.patientService.post(this.patient).subscribe(
      (data) => { this.patient = data,
      error => this.error.firstName = "Nesto je krenulo po zlu" }
    );
  }
}
