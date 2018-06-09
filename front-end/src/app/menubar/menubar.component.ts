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
    this.newDiseaseOpen = false;
    this.newDrugOpen = false;
  }

  public openNewDisease() {
    this.newDiseaseOpen = !this.newDiseaseOpen;
    this.newDoctorOpen = false;
    this.newDrugOpen = false;
  }

  public openNewDrug() {
    this.newDrugOpen = !this.newDrugOpen;
    this.newDiseaseOpen = false;
    this.newDoctorOpen = false;
  }

  openDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
    this.newDiseaseOpen = false;
    this.newDoctorOpen = false;
    this.newDrugOpen = false;
    
  }

}
