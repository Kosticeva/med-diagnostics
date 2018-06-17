import { Component, OnInit, Input } from '@angular/core';
import { DiseaseService } from '../../services/disease.service';
import { Disease } from '../../model/disease';
import { Symptom } from '../../model/symptom';
import { SymptomService } from '../../services/symptom.service';
import { LinkService } from '../../services/link.service';
import { QueryService } from '../../services/query.service';

@Component({
  selector: 'app-new-disease',
  templateUrl: './new-disease.component.html',
  styleUrls: ['./new-disease.component.css']
})
export class NewDiseaseComponent implements OnInit {

  error: string;
  success: string;
  symptoms: string;
  @Input() disease: Disease;
  realSymptoms: any[];
  @Input() open: boolean;
  mode: boolean;

  beforeSymptoms: Symptom[];

  constructor(
    private diseaseService: DiseaseService,
    private symptomService: SymptomService,
    private linkService: LinkService,
    private queryService: QueryService
  ) { }

  ngOnInit() {
    this.error = "";
    this.success = "";
    this.symptoms= "";
    this.beforeSymptoms = [];
    if(this.disease.id === -1){
      this.mode = true;
    }else{
      this.mode = false;
    }
    this.realSymptoms = [];
    this.queryService.findAllSymptoms(this.disease).subscribe(
      (data) => {
        this.stringifySymps(data);
      }
    )
  }

  stringifySymps(data: Symptom[]) {
    this.symptoms = "";
    for(let i=0; i<data.length; i++){
      this.symptoms += data[i].name;
      if(i !== data.length-1){
        this.symptoms += ",";
      }
    }
    this.beforeSymptoms = data;
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
        if(data == null){
          data = [];
        }
        this.createDiseaseII(data)
      }
    );
  }

  createDiseaseII(symps: any[]) {
    if(this.mode){
      this.diseaseService.post(this.disease).subscribe(
        (data) => {
          this.disease = data;
          this.createLinks(data, symps);
        },
        error => { this.error = "Nesto je krenulo po zlu"; }
      );
    }else {
      this.diseaseService.put(this.disease, this.disease.id).subscribe(
        (data) => {
          this.disease = data;
          this.createLinks(data, symps);
        },
        error => { this.error = "Nesto je krenulo po zlu"; }
      );
    }
  }

  createLinks(d: Disease, symptoms: Symptom[]) {

    this.linkService.removeLink(d.id, this.beforeSymptoms).subscribe(
      (data) => {
        this.linkService.putLink(d.id, symptoms).subscribe(
          data => {
            this.error = "";
            if(this.mode)
              this.success = "Bolest "+d.name+" uspesno kreirana!";
            else
            this.success = "Bolest "+d.name+" uspesno izmenjena!";
          },
          error => {
            this.error = "Nesto je krenulo po zlu";
            this.success = "";
          });
      }
    );

    
  }
}
