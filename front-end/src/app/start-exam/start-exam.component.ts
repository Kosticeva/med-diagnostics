import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-start-exam',
  templateUrl: './start-exam.component.html',
  styleUrls: ['./start-exam.component.css']
})
export class StartExamComponent implements OnInit {

  formShown: boolean;

  constructor(
    public router: Router
  ) { }

  ngOnInit() {
    this.formShown = false;
  }

  showForm() {
    this.formShown = !this.formShown;
  }

  goForward() {
    this.router.navigate(["/exam/1"])
  }

  navBack() {
    window.history.back();
  }

  choosePatient() {
    
  }

}
