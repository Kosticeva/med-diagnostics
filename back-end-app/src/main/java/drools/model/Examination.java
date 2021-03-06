package drools.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Entity
@Role(Role.Type.EVENT)
@Timestamp("date")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer"})
public class Examination  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exam_id")
	private Integer id;
	
	@Column(nullable = false, name="exam_date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	//@Column(name="doctor_id")
	private Doctor doctor;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="exam_symptoms",
			joinColumns = @JoinColumn(name="exam_id"),
			inverseJoinColumns = @JoinColumn(name="symptoms_id")
		)
	private List<Symptom> symptoms;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@Column(name="disease_id")
	private Disease disease;
	
	@OneToOne(fetch = FetchType.LAZY)
	//@Column(name="prescription_id")
	private Prescription prescription;
	
	public Examination() {}

	public Examination(Integer id, Date date, Doctor doctor, List<Symptom> symptoms, Disease disease,
			Prescription prescription) {
		super();
		this.id = id;
		this.date = date;
		this.doctor = doctor;
		this.symptoms = symptoms;
		this.disease = disease;
		this.prescription = prescription;
	}

	public  Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<Symptom> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	
	@Override
	public String toString() {
		String retVal = "\t-Date: "+date+
				"\n\t-Doctor: "+doctor.toString()+
				"\n\t-Symptoms: ";
		for(Symptom s: symptoms) {
			retVal += "\n\t\t*"+s.getName();
		}
		if(disease != null) {
			retVal += "\n\t-Diagnose: "+disease.getName();
		}
		if(prescription != null) {
			retVal += "\n\t-Therapy: "+prescription.toString();
		}
		
		return retVal;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Examination)) {
			return false;
		}
		
		Examination e = (Examination) o;
		
		if(id.equals(e.getId()) && doctor.equals(e.getDoctor()) && date.equals(e.getDate())
				&& prescription.equals(e.getPrescription()) && disease.equals(e.getDisease())) {
			for(Symptom ss: symptoms) {
				if(!e.getSymptoms().contains(ss)){
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
}
