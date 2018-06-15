import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PatientService } from '../services/patient.service';
import { Patient } from '../model/patient';
import { ChartService } from '../services/chart.service';
import { ExamService } from '../services/exam.service';
import { Chart } from '../model/chart';
import { Examination } from '../model/examination';
import { Doctor } from '../model/doctor';

@Component({
  selector: 'app-start-exam',
  templateUrl: './start-exam.component.html',
  styleUrls: ['./start-exam.component.css']
})
export class StartExamComponent implements OnInit {

  formShown: boolean;
  patients: Chart[];
  query: string;
  exam: Examination;
  patient: Chart;
  searching: boolean

  constructor(
    private router: Router,
    private chartService: ChartService,
    private patientService: PatientService,
    private examService: ExamService
  ) { }

  ngOnInit() {
    this.formShown = false;
    this.patients = [];
    this.query = "";
    this.searching = false;
    this.exam = {
      id: null,
      doctor: null,
      date: null,
      prescription: null,
      symptoms: [],
      disease: null
    };
    this.patient = {
      id: -1,
      patient: null,
      examinations: []
    };
    /*this.chartService.getAll().subscribe(
      (data) => {
        this.patients = data;
      }
    )*/
  }

  showForm() {
    this.formShown = !this.formShown;
  }

  goForward() {
    this.exam.doctor = new Doctor("c", "d", "k", "a", "REGULAR", 1);
    this.examService.post(this.exam).subscribe(
      (data) => {
        this.exam = data;
        this.patient.examinations.push(this.exam);
        this.chartService.put(this.patient, this.patient.id).subscribe(
          (data) => {
            this.router.navigate(["/exam/"+this.exam.id]);
          }
        );
      }
    );
  }

  navBack() {
    window.history.back();
  }

  choosePatient(chart: Chart) {
    this.patient = chart;
  }

  findPatients() {
    if(this.query.length > 0){
      this.searching = true;
      this.patientService.getByName(this.query).subscribe(
        (data) => {
          this.patients = data;
          this.searching = false;
        }
      );
    }
  }

  getLink(id: number): string{
    return '../../patient/'+id;
  }

}
