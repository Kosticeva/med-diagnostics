import { Component, OnInit } from '@angular/core';
import { Drug } from '../../model/drug';
import { Ingredient } from '../../model/ingredient';
import { DrugService } from '../../services/drug.service';
import { IngredientService } from '../../services/ingredient.service';

@Component({
  selector: 'app-new-drug',
  templateUrl: './new-drug.component.html',
  styleUrls: ['./new-drug.component.css']
})
export class NewDrugComponent implements OnInit {

  error: String;
  ingredients: String;
  drug: Drug;
  realIngredients: Ingredient[];

  constructor(
    private drugService: DrugService,
    private ingredientService: IngredientService
  ) { }

  ngOnInit() {
    this.error = "";
    this.ingredients = "";
    this.drug = {
      id: undefined,
      name: '',
      drugType: '',
      ingredients: []
    };
    this.realIngredients = [];
  }

  createDrug() {
    if(this.drug.name === ""){
      this.error = "Morate uneti ime za lek";
      return;
    }

    if(this.drug.drugType === ""){
      this.drug.drugType = "OTHER";
    }

    let parts = this.ingredients.split(',');
    for(let i=0; i<parts.length; i++){
      const part = parts[i].trim();
      let ii = new Ingredient(null, part);
      this.ingredientService.post(ii).subscribe(
        (data) => this.realIngredients.push(data)
      );
    }

    this.drug.ingredients = this.realIngredients;
    this.drugService.post(this.drug).subscribe(
      (data) => this.drug = data,
      error => this.error = "Vec postoji lek sa tim imenom"
    );
  }
}
