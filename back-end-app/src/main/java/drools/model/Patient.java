package drools.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Patient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, name="patient_first_name")
	private String firstName;
	
	@Column(nullable = false, name="patient_last_name")
	private String lastName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="patient_id")
	private Integer id;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="patient_allergies",
			joinColumns = @JoinColumn(name="patient_id"),
			inverseJoinColumns = @JoinColumn(name = "allergy_id")
		)
	private List<Allergy> allergens;
	
	public Patient() {}
	
	public Patient(String firstName, String lastName, int id, List<Allergy> allergens) {
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
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Allergy> getAllergens() {
		return allergens;
	}
	
	public void setAllergens(List<Allergy> allergens) {
		this.allergens = allergens;
	}
	
	@Override
	public String toString() {
		return lastName + ", " + firstName;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Patient)) {
			return false;
		}
		
		Patient p = (Patient) o;
		
		if(id.equals(p.getId()) && firstName.equals(p.getFirstName())
				&& lastName.equals(p.getLastName())) {
			for(Allergy aa: allergens) {
				if(!p.getAllergens().contains(aa)){
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
}
