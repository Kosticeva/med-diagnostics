import { Component, OnInit } from '@angular/core';
import { $ } from 'protractor';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';
import { Examination } from '../model/examination';
import { Patient } from '../model/patient';
import { Doctor } from '../model/doctor';
import { Symptom } from '../model/symptom';
import { PrescriptionService } from '../services/prescription.service';
import { Chart } from '../model/chart';
import { ExamService } from '../services/exam.service';
import { ChartService } from '../services/chart.service';
import { ReportService } from '../services/report.service';

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ExamComponent implements OnInit {

  sympsOpen: boolean;
  diagOpen: boolean;
  therOpen: boolean;
  exam: Examination;
  chart: Chart;
  doctor: Doctor;

  constructor(
    private router: Router,
    private prescriptionService: PrescriptionService,
    private examService: ExamService,
    private chartService: ChartService,
    private reportsService: ReportService
  ) { }

  ngOnInit() {
    this.sympsOpen = false;
    this.diagOpen = false;
    this.therOpen = false;
  }

  goToChart() {
    this.router.navigate(["./patient/"+this.chart.patient.id]);
  }

  openSymps() {
    this.sympsOpen = !this.sympsOpen;
  }

  openDiag() {
    this.diagOpen = !this.diagOpen;
  }

  openTher() {
    this.therOpen = !this.therOpen;
  }

  finishExam() {
    this.reportsService.allergies().subscribe(
      (data) => {
        if(data.length != 0){
          let allergies = '';
          for(let i=0; i<data.length; i++){
            allergies += data[i].name + '\n';
          }

          alert("Pacijent ima alergiju na "+ allergies);
        }else {
          this.prescriptionService.post(this.exam.prescription).subscribe(
            (data) => {
              this.exam.prescription = data;
              this.examService.post(this.exam).subscribe(
                (data) => {
                  this.exam = data;
                  this.chart.examinations.push(this.exam);
                  this.chartService.post(this.chart).subscribe(
                    (data) => {
                      this.chart = data;
                      this.router.navigate(["./home"]);
                    }
                  );
                }
              );
            }
          );
        }
      }
    );
  }

  removeSymptom(symptom: Symptom) {
    this.exam.symptoms.splice(this.exam.symptoms.indexOf(symptom), 1);
  }
}
