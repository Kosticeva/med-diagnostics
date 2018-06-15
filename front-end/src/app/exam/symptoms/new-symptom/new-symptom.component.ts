import { Component, OnInit } from '@angular/core';
import { SymptomService } from '../../../services/symptom.service';
import { Symptom } from '../../../model/symptom';

@Component({
  selector: 'app-new-symptom',
  templateUrl: './new-symptom.component.html',
  styleUrls: ['./new-symptom.component.css']
})
export class NewSymptomComponent implements OnInit {

  symptom: Symptom
  error: string;

  constructor(
    private symptomService: SymptomService
  ) { }

  ngOnInit() {
    this.symptom = {
      name: '',
      id: undefined
    };
    this.error = '';
  }

  createSymptom() {
    if(this.symptom.name === ''){
      this.error = "Morate uneti neki naziv";
      return;
    };

    this.error = '';

    this.symptomService.post([this.symptom]).subscribe(
      (data) => this.symptom = data
    );
  }

}
