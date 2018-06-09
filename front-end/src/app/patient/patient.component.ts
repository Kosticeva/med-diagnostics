import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  newAllergyOpen: boolean;

  constructor() { }

  ngOnInit() {
    this.newAllergyOpen = false;
  }

  openNewAllergy(){
    this.newAllergyOpen = !this.newAllergyOpen;
  }

  addAllergy() {
    this.newAllergyOpen = false;
  }

}
