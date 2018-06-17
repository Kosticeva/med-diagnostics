import { Component, OnInit, Input } from '@angular/core';
import { Drug } from '../../model/drug';
import { Ingredient } from '../../model/ingredient';
import { DrugService } from '../../services/drug.service';
import { IngredientService } from '../../services/ingredient.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-drug',
  templateUrl: './new-drug.component.html',
  styleUrls: ['./new-drug.component.css']
})
export class NewDrugComponent implements OnInit {

  error: String;
  success: String;
  ingredients: String;
  @Input() drug: Drug;
  realIngredients: any[];
  @Input() open: boolean;
  mode: boolean;

  constructor(
    private drugService: DrugService,
    private ingredientService: IngredientService,
    private router: Router
  ) { }

  ngOnInit() {
    this.error = "";
    this.success = "";
    if(this.drug.id === -1){
      this.mode = true;
    }else{
      this.mode = false;
    }

    this.realIngredients = this.drug.ingredients;
    this.stringifyIngrs();
  }

  stringifyIngrs() {
    this.ingredients = "";
    for(let i=0; i<this.drug.ingredients.length; i++){
      this.ingredients += this.drug.ingredients[i].name;
      if(i !== this.drug.ingredients.length-1){
        this.ingredients += ",";
      }
    }
  }


  createDrug() {
    this.success = "";
    this.realIngredients = [];
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
      this.realIngredients.push({'name': part});
    }
    
    this.ingredientService.post(this.realIngredients).subscribe(
      (data) => {
        this.createDrugII(data)
      }
    );
  }

  createDrugII(ings: Ingredient[]){
    this.drug.ingredients = ings;
    if(this.mode){
      this.drugService.post(this.drug).subscribe(
        (data) => {
          this.drug = data;
          this.success = "Lek "+this.drug.name+" uspesno kreiran!";
          this.error = "";
        },
        error => {
          this.error = "Vec postoji lek sa tim imenom";
          this.success = "";
        }
      );
    }else{
      this.drugService.put(this.drug, this.drug.id).subscribe(
        (data) => {
          this.drug = data;
          this.success = "Lek "+this.drug.name+" uspesno izmenjen!";
          this.error = "";
        },
        error => {
          this.error = "Vec postoji lek sa tim imenom";
          this.success = "";
        }
      );
    }
  }
}
