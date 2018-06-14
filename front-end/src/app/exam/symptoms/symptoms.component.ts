import { Component, OnInit, Input } from '@angular/core';
import { Symptom } from '../../model/symptom';
import { SymptomService } from '../../services/symptom.service';

@Component({
  selector: 'app-symptoms',
  templateUrl: './symptoms.component.html',
  styleUrls: ['./symptoms.component.css']
})
export class SymptomsComponent implements OnInit {

  formShown: boolean;
  allSymptoms: Symptom[];
  @Input() symptoms: Symptom[];
  query: string;

  constructor(
    private symptomService: SymptomService
  ) { }

  ngOnInit() {
    this.formShown = false;
    this.query = '';
    this.symptomService.getAll().subscribe(
      (data) => this.allSymptoms = data
    );
  }

  public showForm() {
    this.formShown = !this.formShown;
  }

  findSymptoms(){
    this.symptomService.getByName(this.query).subscribe(
      (data) => this.allSymptoms = data
    );
  }

  addSymptom(symptom: Symptom) {
    this.symptoms.push(symptom);
  }

  removeSymptom(symptom: Symptom) {
    this.symptoms.splice(this.symptoms.indexOf(symptom), 1);
  }


}
