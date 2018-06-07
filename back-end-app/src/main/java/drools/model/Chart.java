package drools.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Chart{

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Examination> examinations;
	
	@OneToOne(fetch = FetchType.EAGER)
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
	
}
