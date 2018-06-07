package drools.model;

import javax.persistence.Entity;

@Entity
public class Ingredient extends Allergen{
	
	public Ingredient() {
		super();
	}
	
	public Ingredient(int id, String name) {
		super(id, name);
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
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

}
