import { Component, OnInit, Input } from '@angular/core';
import { PatientService } from '../../services/patient.service';
import { Patient } from '../../model/patient';
import { IntensiveCareService } from '../../services/intensive-care.service';

@Component({
  selector: 'app-new-ic-patient',
  templateUrl: './new-ic-patient.component.html',
  styleUrls: ['./new-ic-patient.component.css']
})
export class NewIcPatientComponent implements OnInit {

  patients: Patient[];
  query: string;
  realPatients: Patient[];
  @Input() icPatients: Patient[];

  constructor(
    private patientService: PatientService,
    private icService: IntensiveCareService
  ) { }

  ngOnInit() {
    this.realPatients = [];
    this.patients = [];
    this.query = "";
    this.patientService.getAll().subscribe(
      (data) => this.realPatients = data
    );
  }

  addToIC(id: number) {
    this.icService.addToIc(id).subscribe(
      (data) => {
        this.query = "";
        this.getPatients();
      }
    );
  }

  getPatients() {
    this.patients = [];
    for(let i=0; i<this.realPatients.length; i++){
      if(this.realPatients[i].firstName.startsWith(this.query) || this.realPatients[i].lastName.startsWith(this.query)){
        let flag = false;
        for(let j=0; j<this.icPatients.length; j++) {
          if(this.icPatients[j].id === this.realPatients[i].id){
            flag = true;
            break;
          }
        }

        if(!flag){
          this.patients.push(this.realPatients[i]);
        }
      }
    }
  }
}
