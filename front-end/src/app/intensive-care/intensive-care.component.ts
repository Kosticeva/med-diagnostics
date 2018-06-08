import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-intensive-care',
  templateUrl: './intensive-care.component.html',
  styleUrls: ['./intensive-care.component.css']
})
export class IntensiveCareComponent implements OnInit {

  newPatientOpen: boolean;

  constructor() { }

  ngOnInit() {
    this.newPatientOpen = false;
  }

  navBack() {
    window.history.back();
  }

  openNewPatient() {
    this.newPatientOpen = !this.newPatientOpen;
  }

  removeIcPatient(){

  }

}
