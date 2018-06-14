import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReportService } from '../services/report.service';
import { Patient } from '../model/patient';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

  chronics: Patient[];
  addicts: Patient[];
  weaks: Patient[];

  constructor(
    private reportsService: ReportService
  ) { }

  ngOnInit() {
    this.reportsService.addicts().subscribe(
      (data) => this.addicts = data
    );
    this.reportsService.weaks().subscribe(
      (data) => this.weaks = data
    );
    this.reportsService.chronics().subscribe(
      (data) => this.chronics = data
    )
  }

  navBack() {
    window.history.back();
  }

  getLink(id: number): string{
    return '../patients/'+id;
  }

}
