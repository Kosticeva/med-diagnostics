package drools.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Chart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Examination> examinations;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Patient patient;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Chart() {}

	public Chart(List<Examination> examinations, Patient patient, Integer id) {
		super();
		this.examinations = examinations;
		this.patient = patient;
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		String retVal = "Patient: " + patient.toString();
		for(Examination m: examinations) {
			retVal += "\n-----------------------------\n";
			retVal += m.toString();
		}
		
		return retVal;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Chart)) {
			return false;
		}
		
		Chart c = (Chart) o;
		
		if(id.equals(c.getId()) && patient.equals(c.getPatient())) {
			for(Examination ee: examinations) {
				if(!c.getExaminations().contains(ee)){
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
}
