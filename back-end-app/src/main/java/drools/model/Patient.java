package drools.model;

import java.util.List;

public class Patient{

	private String firstName;
	private String lastName;
	
	private int id;
	
	private List<Allergen> allergens;
	
	public Patient() {}
	
	public Patient(String firstName, String lastName, int id, List<Allergen> allergens) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.allergens = allergens;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Allergen> getAllergens() {
		return allergens;
	}
	
	public void setAllergens(List<Allergen> allergens) {
		this.allergens = allergens;
	}
	
	@Override
	public String toString() {
		return lastName + ", " + firstName;
	}
}
