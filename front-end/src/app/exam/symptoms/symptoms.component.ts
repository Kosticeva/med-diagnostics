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
  searching: boolean;

  constructor(
    private symptomService: SymptomService
  ) { }

  ngOnInit() {
    this.formShown = false;
    this.query = '';
    this.searching = false;
    //this.symptomService.getAll().subscribe(
    //  (data) => this.allSymptoms = data
    //);
  }

  public showForm() {
    this.formShown = !this.formShown;
  }

  findSymptoms(){
    if(this.query.length > 0){
      this.searching = true;
      this.symptomService.getByName(this.query).subscribe(
        (data) => {
          this.allSymptoms = data;
          this.searching = false;
        }
      );
    }
  }

  addSymptom(symptom: Symptom) {
    if(this.checkIfIn) {
      this.symptoms.push(symptom);
    }
  }

  removeSymptom(symptom: Symptom) {
    for(let i=0; i<this.symptoms.length; i++) {
      if(this.symptoms[i].id === symptom.id){
        this.symptoms.splice(i, 1);
      }
    }
  }

  checkIfIn(symptom: Symptom): boolean{
    for(let i=0; i<this.symptoms.length; i++) {
      if(this.symptoms[i].id === symptom.id){
        return true;
      }
    }

    return false;
  }

  checkIfOut(symptom: Symptom){
    for(let i=0; i<this.symptoms.length; i++) {
      if(this.symptoms[i].id === symptom.id){
        return false;
      }
    }

    return true;
  }

}
