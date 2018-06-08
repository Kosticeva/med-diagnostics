import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menubar',
  templateUrl: './menubar.component.html',
  styleUrls: ['./menubar.component.css']
})
export class MenubarComponent implements OnInit {

  newDoctorOpen: boolean;
  newDiseaseOpen: boolean;
  newDrugOpen: boolean;
  dropdownOpen: boolean;

  constructor() { }

  ngOnInit() {
    this.newDiseaseOpen = false;
    this.newDoctorOpen = false;
    this.newDrugOpen = false;
    this.dropdownOpen = false;
  }

  public openNewDoctor() {
    this.newDoctorOpen = !this.newDoctorOpen;
  }

  public openNewDisease() {
    this.newDiseaseOpen = !this.newDiseaseOpen;
  }

  public openNewDrug() {
    this.newDrugOpen = !this.newDrugOpen;
  }

  openDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

}
