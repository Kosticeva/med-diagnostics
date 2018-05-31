package drools.model;

import java.util.List;

import drools.model.enums.DrugType;

public class Drug extends Allergen{

	private List<Ingredient> ingredients;
	private DrugType drugType;
	
	public Drug() {
		super();
	}
	
	public Drug(int id, String name, List<Ingredient> ingredients, DrugType drugType) {
		super(id, name);
		this.ingredients = ingredients;
		this.drugType = drugType;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public DrugType getDrugType() {
		return drugType;
	}
	
	public void setDrugType(DrugType drugType) {
		this.drugType = drugType;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

}
