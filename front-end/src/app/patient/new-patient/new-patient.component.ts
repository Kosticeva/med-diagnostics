import { Component, OnInit, Input } from '@angular/core';
import { PatientService } from '../../services/patient.service';
import { ChartService } from '../../services/chart.service';
import { Patient } from '../../model/patient';
import { AllergyService } from '../../services/allergy.service';
import { Allergen } from '../../model/allergen';
import { Chart } from '../../model/chart';

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
  realAllergens: any[];
  @Input() currChart: Chart; 

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

    this.patient = {
      firstName: '',
      lastName: '',
      allergens: [],
      id: null
    };
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
      this.realAllergens.push({'name': part});
    }

    this.allergyService.post(this.realAllergens).subscribe(
      (data) => {
        this.createPatientI(data);
      }
    );
  }

  createPatientI(allergies: Allergen[]){
    this.patient.allergens = allergies;
    this.patientService.post(this.patient).subscribe(
      (data) => { 
        this.patient = data;
        this.currChart.patient = this.patient;
        this.currChart.examinations = [];
        this.currChart.id = null;
        this.chartService.post(this.currChart).subscribe(
          (data) => {
            this.currChart = data;
          }
        )
      },
      error => this.error.firstName = "Nesto je krenulo po zlu"
    );
  }

}
