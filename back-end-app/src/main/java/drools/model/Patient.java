package drools.model;

import java.util.List;

public class Patient{

	private String firstName;
	private String lastName;
	
	private int id;
	
	private Chart medicalChart;
	private List<Allergen> allergens;
	
	public Patient() {}
	
	public Patient(String firstName, String lastName, int id, Chart medicalChart, List<Allergen> allergens) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.medicalChart = medicalChart;
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
	public Chart getMedicalChart() {
		return medicalChart;
	}
	public void setMedicalChart(Chart medicalChart) {
		this.medicalChart = medicalChart;
	}
	public List<Allergen> getAllergens() {
		return allergens;
	}
	public void setAllergens(List<Allergen> allergens) {
		this.allergens = allergens;
	}
	
}
