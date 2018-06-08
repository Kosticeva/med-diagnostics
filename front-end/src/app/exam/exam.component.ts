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

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
  }

  goToChart() {
    this.router.navigate(["./patient/1"]);
  }

  finishExam() {
    ///alert sa poljima
    ///neki if

    this.router.navigate(["./home"]);
  }
}
