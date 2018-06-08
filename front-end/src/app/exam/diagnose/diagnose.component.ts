import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-diagnose',
  templateUrl: './diagnose.component.html',
  styleUrls: ['./diagnose.component.css']
})
export class DiagnoseComponent implements OnInit {

  dropdownOpen: boolean;

  constructor() { }

  ngOnInit() {
    this.dropdownOpen = false;
  }

  public openDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  showAllPossible() {

  }

  showMostPossible() {

  }

  chooseDisease() {
    
  }
}
