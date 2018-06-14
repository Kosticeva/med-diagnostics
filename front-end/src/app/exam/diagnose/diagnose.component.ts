import { Component, OnInit, Input } from '@angular/core';
import { Disease } from '../../model/disease';
import { DiseaseService } from '../../services/disease.service';
import { QueryService } from '../../services/query.service';
import { Symptom } from '../../model/symptom';

@Component({
  selector: 'app-diagnose',
  templateUrl: './diagnose.component.html',
  styleUrls: ['./diagnose.component.css']
})
export class DiagnoseComponent implements OnInit {

  diseases: Disease[];
  query: string;
  @Input() disease: Disease;
  @Input() symptoms: Symptom[];
  simptomi: string;

  constructor(
    private diseaseService: DiseaseService,
    private queryService: QueryService
  ) { }

  ngOnInit() {
    this.query = '';
    this.simptomi = '';
    this.diseases = [];
  }

  showAllPossible() {
    this.queryService.findAllPossible(this.symptoms).subscribe(
      (data) => this.diseases = data
    );
  }

  showMostPossible() {
    this.queryService.findAllPossible(this.symptoms).subscribe(
      (data) => this.diseases = data
    );
  }

  findSymptoms(disease: Disease) {
    this.queryService.findAllSymptoms(disease).subscribe(
      (data) => this.simptomi = data
    );
  }

  chooseDisease(diseasee: Disease) {
    this.disease = diseasee;
  }

  findDisease(){
    this.diseaseService.getByName(this.query).subscribe(
      (data) => this.diseases = data
    );
  }
}
