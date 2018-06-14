import { Component, OnInit } from '@angular/core';
import { IntensiveCareService } from '../services/intensive-care.service';
import { Patient } from '../model/patient';

@Component({
  selector: 'app-intensive-care',
  templateUrl: './intensive-care.component.html',
  styleUrls: ['./intensive-care.component.css']
})
export class IntensiveCareComponent implements OnInit {

  newPatientOpen: boolean;
  icPatients: Patient[];

  constructor(
    private icService: IntensiveCareService
  ) { }

  ngOnInit() {
    this.newPatientOpen = false;
    this.icService.getAllInIc().subscribe(
      (data) => this.icPatients = data
    );
  }

  navBack() {
    window.history.back();
  }

  openNewPatient() {
    this.newPatientOpen = !this.newPatientOpen;
  }

  removeIcPatient(id: number){
    this.icService.removeFromIc(id).subscribe(
      (data) => {
        this.icService.getAllInIc().subscribe(
          (data1) => this.icPatients = data1
        )
      }
    );
  }

}
