package drools.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredient implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ingredient_id")
	protected Integer id;
	
	@Column(nullable = false, unique = true, name="ingredient_name")
	protected String name;
	
	public Ingredient() {}
	
	public Ingredient(int id, String name) {
		this.id = id;
		this.name = name;
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

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Ingredient)) {
			return false;
		}
		
		Ingredient i = (Ingredient) o;
		
		if(id.equals(i.getId()) && name.equals(i.getName())) {
			return true;
		}
		
		return false;
	}

}
