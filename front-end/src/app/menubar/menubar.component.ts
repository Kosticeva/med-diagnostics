import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Drug } from '../model/drug';
import { Disease } from '../model/disease';
import { Doctor } from '../model/doctor';
import { DiseaseService } from '../services/disease.service';
import { DrugService } from '../services/drug.service';
import { DoctorService } from '../services/doctor.service';

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
  
  profileOpen: boolean;

  doctor: Doctor;
  newDoct: Doctor;
  disease: Disease;
  drug: Drug;
  newDrug: Drug;
  newDisease: Disease;

  editOpen: boolean;
  editDoctorOpen: boolean;
  editDiseaseOpen: boolean;
  editDrugOpen: boolean;
  searchFor: number;
  query: string;
  data: any[];

  startDisEdit: boolean;
  startDrugEdit: boolean;

  constructor(
    private loginService: LoginService,
    private diseaseService: DiseaseService,
    private drugService: DrugService,
    private doctorService: DoctorService
  ) { }

  ngOnInit() {
    this.newDiseaseOpen = false;
    this.newDoctorOpen = false;
    this.newDrugOpen = false;
    this.dropdownOpen = false;
    this.profileOpen = false;

    this.newDoct = {
        firstName: '',
        lastName: '',
        username: '',
        password: '',
        type: false,
        licenceId: -1
    };

    this.editOpen = false;
    this.editDiseaseOpen = false;
    this.editDrugOpen = false;
    this.searchFor = -1;
    this.query = '';
    this.data = [];
    this.startDisEdit = false;
    this.startDrugEdit = false;

    this.disease = new Disease(-1, "");
    this.newDisease = new Disease(-1, "");
    this.drug = new Drug(-1, "", "OTHER", []);
    this.newDrug = new Drug(-1, "", "OTHER", []);
    this.doctorService.get(this.loginService.getDoctor()).subscribe(
      (data) => this.doctor = data
    );
  }

  public openNewDoctor() {
    this.newDoctorOpen = !this.newDoctorOpen;
    this.newDiseaseOpen = false;
    this.newDrugOpen = false;

    this.searchFor = -1;
    this.editOpen = false;
    this.editDiseaseOpen = false;
    this.editDoctorOpen = false;
    this.editDrugOpen = false;
  }

  public openNewDisease() {
    this.newDiseaseOpen = !this.newDiseaseOpen;
    this.newDoctorOpen = false;
    this.newDrugOpen = false;

    this.searchFor = -1;
    this.editOpen = false;
    this.editDiseaseOpen = false;
    this.editDoctorOpen = false;
    this.editDrugOpen = false;
  }

  public openNewDrug() {
    this.newDrugOpen = !this.newDrugOpen;
    this.newDiseaseOpen = false;
    this.newDoctorOpen = false;

    this.searchFor = -1;
    this.editOpen = false;
    this.editDiseaseOpen = false;
    this.editDoctorOpen = false;
    this.editDrugOpen = false;
  }

  openDropdown() {
    this.newDoct = {
      firstName: '',
      lastName: '',
      username: '',
      password: '',
      type: false,
      licenceId: -1
  };
    this.disease = new Disease(-1, "");
    this.newDisease = new Disease(-1, "");
    this.drug = new Drug(-1, "", "OTHER", []);
    this.newDrug = new Drug(-1, "", "OTHER", []);

    this.dropdownOpen = !this.dropdownOpen;
    this.newDiseaseOpen = false;
    this.newDoctorOpen = false;
    this.newDrugOpen = false;
    this.profileOpen = false;

    this.searchFor = -1;
    this.editOpen = false;
    this.editDiseaseOpen = false;
    this.editDoctorOpen = false;
    this.editDrugOpen = false;
  }

  openDropdownEdit() {
    this.newDoct = {
      firstName: '',
      lastName: '',
      username: '',
      password: '',
      type: false,
      licenceId: -1
  };
    this.disease = new Disease(-1, "");
    this.newDisease = new Disease(-1, "");
    this.drug = new Drug(-1, "", "OTHER", []);
    this.newDrug = new Drug(-1, "", "OTHER", []);

    this.dropdownOpen = false;
    this.newDiseaseOpen = false;
    this.newDoctorOpen = false;
    this.newDrugOpen = false;

    this.searchFor = -1;
    this.editOpen = !this.editOpen;
    this.editDiseaseOpen = false;
    this.editDoctorOpen = false;
    this.editDrugOpen = false;
    this.profileOpen = false;
  }

  openSearchDrug() {
    this.searchFor = 2;
    this.query = '';
    this.data = [];
    this.startDrugEdit = false;
    this.startDisEdit = false;
  }

  openSearchDisease() {
    this.searchFor = 1;
    this.data = [];
    this.query = '';
    this.startDrugEdit = false;
    this.startDisEdit = false;
  }

  editDrug(drug: Drug) {
    this.drug = drug;
    this.disease = new Disease(-1, "");
    this.startDrugEdit = true;
    this.startDisEdit = false;
  }

  editDisease(disease: Disease){
    this.drug = new Drug(-1, "", "OTHER", []);
    this.disease = disease;
    this.startDrugEdit = false;
    this.startDisEdit = true;
  }

  editData() {
    this.profileOpen = !this.profileOpen;
  }

  getData() {
    if(this.searchFor === 1 && this.query.length > 0){
      this.diseaseService.getByName(this.query).subscribe(
        (data) => {
          this.data = data;
        }
      );
    }else if(this.searchFor === 2 && this.query.length > 0){
      this.drugService.getByName(this.query).subscribe(
        (data) => {
          this.data = data;
        }
      );
    }else{
      this.data = [];
    }
  }

  logout() {
    this.loginService.logout();
  }
}
