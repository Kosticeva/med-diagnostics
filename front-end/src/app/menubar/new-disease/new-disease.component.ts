import { Component, OnInit, Input } from '@angular/core';
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
  success: string;
  symptoms: string;
  disease: Disease;
  realSymptoms: any[];
  @Input() open: boolean;

  constructor(
    private diseaseService: DiseaseService,
    private symptomService: SymptomService,
    private linkService: LinkService
  ) { }

  ngOnInit() {
    this.error = "";
    this.success = "";
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
    this.success = "";

    let parts = this.symptoms.split(',');
    for(let i=0; i<parts.length; i++){
      const part = parts[i].trim();
      this.realSymptoms.push({
        'name': part
      });
    }

    this.symptomService.post(this.realSymptoms).subscribe(
      (data) => {
        this.createDiseaseII(data)
      }
    );
  }

  createDiseaseII(symps: any[]) {
    this.diseaseService.post(this.disease).subscribe(
      (data) => {
        this.disease = data;
        this.createLinks(data, symps);
      },
      error => { this.error = "Nesto je krenulo po zlu"; }
    );
  }

  createLinks(d: Disease, symptoms: Symptom[]) {
    for(let i=0; i<symptoms.length; i++) {
      this.linkService.putLink(d.id, symptoms[i].id).subscribe(
        data => {
          this.error = "";
          this.success = "Bolest "+d.name+" uspesno kreirana!";
        },
        error => {
          this.error = "Nesto je krenulo po zlu";
          this.success = "";
        });
    }
  }
}
