import { Component, OnInit } from '@angular/core';
import { DiseaseService } from '../../services/disease.service';
import { Disease } from '../../model/disease';
import { Symptom } from '../../model/symptom';
import { SymptomService } from '../../services/symptom.service';
import { LinkService } from '../../services/link.service';

@Component({
  selector: 'app-new-disease',
  templateUrl: './new-disease.component.html',
  styleUrls: ['./new-disease.component.css']
})
export class NewDiseaseComponent implements OnInit {

  error: string;
  symptoms: string;
  disease: Disease;
  realSymptoms: Symptom[];

  constructor(
    private diseaseService: DiseaseService,
    private symptomService: SymptomService,
    private linkService: LinkService
  ) { }

  ngOnInit() {
    this.error = "";
    this.symptoms= "";
    this.disease = {
      id: undefined,
      name: ''
    };
    this.realSymptoms = [];
  }

  createDisease() {
    
    if(this.disease.name === "") {
      this.error = "Unesite ime za bolest";
      return;
    }

    this.error = "";

    let parts = this.symptoms.split(',');
    for(let i=0; i<parts.length; i++){
      const part = parts[i].trim();
      let s = new Symptom(null, part);
      this.symptomService.post(s).subscribe(
        (data) => this.realSymptoms.push(data)
      );
    }

    this.diseaseService.post(this.disease).subscribe(
      (data) => {
        this.disease = data;
        this.createLinks();
      },
      error => { this.error = "" }
    );
  }

  createLinks() {
    for(let i=0; i<this.realSymptoms.length; i++) {
      this.linkService.putLink(this.disease, this.realSymptoms[i]);
    }
  }
}
