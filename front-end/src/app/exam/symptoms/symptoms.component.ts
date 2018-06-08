import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-symptoms',
  templateUrl: './symptoms.component.html',
  styleUrls: ['./symptoms.component.css']
})
export class SymptomsComponent implements OnInit {

  formShown: boolean;

  constructor() { }

  ngOnInit() {
    this.formShown = false;
  }

  public showForm() {
    this.formShown = !this.formShown;
  }

  addSymptom() {

  }

  removeSymptom() {
    
  }


}
