import { Component, OnInit, Input } from '@angular/core';
import { Prescription } from '../../model/prescription';
import { Drug } from '../../model/drug';
import { DrugService } from '../../services/drug.service';

@Component({
  selector: 'app-therapy',
  templateUrl: './therapy.component.html',
  styleUrls: ['./therapy.component.css']
})
export class TherapyComponent implements OnInit {

  drugs: Drug[];
  query: string;
  @Input() prescription: Prescription;

  constructor(
    private drugService: DrugService
  ) { }

  ngOnInit() {
    this.drugs = [];
    this.query = '';
  }

  chooseDrug(drug: Drug) {
    this.prescription.drug = drug;
  }

  findDrug() {
    this.drugService.getByName(this.query).subscribe(
      (data) => this.drugs = data
    );
  }

}
