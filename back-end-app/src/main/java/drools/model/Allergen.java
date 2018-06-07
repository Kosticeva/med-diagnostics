package drools.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Allergen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected Integer id;
	
	@Column(nullable = false)
	protected String name;
	
	public Allergen() {}

	public Allergen(int id, String name) {
		this.name = name;
	}
	
	public abstract String getName();
	
	public abstract void setName(String name);
	
	public abstract Integer getId();
	
	public abstract void setId(int id);
}
