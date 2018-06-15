package drools.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import drools.model.enums.DrugType;

@Entity
public class Drug implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="drug_id")
	protected Integer id;
	
	@Column(nullable = false, unique = true, name="drug_name")
	protected String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="drug_ingredients",
			joinColumns = @JoinColumn(name="drug_id"),
			inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private List<Ingredient> ingredients;
	
	@Column(nullable = false, name="drug_type")
	@Enumerated(EnumType.STRING)
	private DrugType drugType;
	
	public Drug() {
		super();
	}
	
	public Drug(Integer id, String name, List<Ingredient> ingredients, DrugType drugType) {
		this.id = id;
		this.name = name;
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
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Drug)) {
			return false;
		}
		
		Drug d = (Drug) o;
		
		if(id.equals(d.getId()) && name.equals(d.getName())
				&& drugType == d.getDrugType()) {
			for(Ingredient ii: ingredients) {
				if(!d.getIngredients().contains(ii)){
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}

}
