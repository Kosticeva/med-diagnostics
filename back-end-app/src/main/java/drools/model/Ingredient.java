package drools.model;

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
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

}
