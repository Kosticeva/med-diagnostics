import { Component, OnInit } from '@angular/core';
import { $ } from 'protractor';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ExamComponent implements OnInit {

  sympsOpen: boolean;
  diagOpen: boolean;
  therOpen: boolean;

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    this.sympsOpen = false;
    this.diagOpen = false;
    this.therOpen = false;
  }

  goToChart() {
    this.router.navigate(["./patient/1"]);
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
    ///alert sa poljima
    ///neki if

    this.router.navigate(["./home"]);
  }

  removeSymptom() {
    
  }
}
