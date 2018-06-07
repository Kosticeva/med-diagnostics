package drools.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import drools.model.enums.DrugType;

@Entity
public class Drug extends Allergen{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ingredient> ingredients;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private DrugType drugType;
	
	public Drug() {
		super();
	}
	
	public Drug(int id, String name, List<Ingredient> ingredients, DrugType drugType) {
		super(name);
		this.id = id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
