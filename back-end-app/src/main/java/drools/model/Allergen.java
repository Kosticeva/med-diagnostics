package drools.model;

public abstract class Allergen {
	
	protected int id;
	protected String name;
	
	public Allergen() {}

	public Allergen(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public abstract String getName();
	public abstract int getId();
	
	public abstract void setName(String name);
	public abstract void setId(int id);
}
