import { Component, OnInit, Input } from '@angular/core';
import { Prescription } from '../../model/prescription';
import { Drug } from '../../model/drug';
import { DrugService } from '../../services/drug.service';
import { Ingredient } from '../../model/ingredient';

@Component({
  selector: 'app-therapy',
  templateUrl: './therapy.component.html',
  styleUrls: ['./therapy.component.css']
})
export class TherapyComponent implements OnInit {

  drugs: Drug[];
  query: string;
  @Input() prescription: Prescription;
  searching: boolean;

  constructor(
    private drugService: DrugService
  ) { }

  ngOnInit() {
    this.drugs = [];
    this.query = '';
    this.searching = false;
  }

  chooseDrug(drug: Drug) {
    this.prescription.drug = drug;
  }

  findDrug() {
    if(this.query.length > 0){
      this.searching = true;
      this.drugService.getByName(this.query).subscribe(
        (data) => {
          this.drugs = data
          this.searching = false;
        }
      );
    }
  }

  ingredientsToString(ingrs: Ingredient[]){
    let retVal = '';
    for(let i=0; i<ingrs.length; i++){
      retVal += ingrs[i].name;
      if(i !== ingrs.length -1){
        retVal += ', ';
      }
    }

    return retVal;
  }

}
