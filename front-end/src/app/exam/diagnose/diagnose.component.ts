import { Component, OnInit, Input } from '@angular/core';
import { Disease } from '../../model/disease';
import { DiseaseService } from '../../services/disease.service';
import { QueryService } from '../../services/query.service';
import { Symptom } from '../../model/symptom';
import { Examination } from '../../model/examination';

@Component({
  selector: 'app-diagnose',
  templateUrl: './diagnose.component.html',
  styleUrls: ['./diagnose.component.css']
})
export class DiagnoseComponent implements OnInit {

  diseases: Disease[];
  query: string;
  simptomi: string;
  @Input() exam: Examination;
  msg: string;

  startedSearch: number;

  symptomsOpen: number;

  constructor(
    private diseaseService: DiseaseService,
    private queryService: QueryService
  ) { }

  ngOnInit() {
    this.query = '';
    this.simptomi = '';
    this.diseases = [];
    this.symptomsOpen = -1;
    this.msg = "";
    this.startedSearch = -1;
  }

  showAllPossible() {
    this.startedSearch = 0;
    this.msg = "Pretrazivanje ...";
    this.queryService.findAllPossible(this.exam.symptoms).subscribe(
      (data) => {
        this.diseases = data;
        this.startedSearch = 1;
        this.msg = "Pronadjeno je "+data.length+" mogucih bolesti.";
      }
    );
  }

  showMostPossible() {
    this.startedSearch = 0;
    this.msg = "Pretrazivanje ...";
    this.queryService.findMostProbable(this.exam).subscribe(
      (data) => {
        this.diseases = [data];
        this.startedSearch = 1;
        this.msg = "Pronadjena je najverovatnija bolest.";
      },
      error => {
        this.startedSearch = 2;
        this.msg = "Nije pronadjena nijedna bolest. Probajte da unesete jos simptoma.";
      }
    );
  }

  findSymptoms(disease: Disease) {
    this.startedSearch = 0;
    this.msg = "Pretrazivanje ...";
    this.simptomi = '';
    if(this.symptomsOpen != disease.id){
      this.symptomsOpen = disease.id;
      this.queryService.findAllSymptoms(disease).subscribe(
        (data) => {
          this.simpsToString(data)
          this.startedSearch = -1;
          this.msg = "";
        }
      );
    }else{
      this.symptomsOpen = -1;
    }
  }
  
  simpsToString(data: Symptom[]){
    this.simptomi = '';
    for(let i=0; i<data.length; i++){
      this.simptomi += data[i].name +"<br />";
    }
  }

  chooseDisease(diseasee: Disease) {
    this.exam.disease = diseasee;
    this.startedSearch = -1;
    this.msg = "";
  }

  findDisease(){
    this.startedSearch = 0;
    this.msg = "Pretrazivanje ...";
    this.diseaseService.getByName(this.query).subscribe(
      (data) => {
        this.diseases = data;
        this.startedSearch = 1;
        this.msg = "";
      }
    );
  }
}
