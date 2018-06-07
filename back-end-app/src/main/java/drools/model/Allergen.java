package drools.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Allergen {
	
	@Column(nullable = false)
	protected String name;
	
	public Allergen() {}

	public Allergen(String name) {
		this.name = name;
	}
	
	public abstract String getName();
	
	public abstract void setName(String name);
}
