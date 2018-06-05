package drools.model;

import java.util.Date;
import java.util.List;

public class Examination {

	private int id;
	private Date date;
	private Doctor doctor;
	private List<Symptom> symptoms;
	private Disease disease;
	private Prescription prescription;
	
	public Examination() {}

	public Examination(int id, Date date, Doctor doctor, List<Symptom> symptoms, Disease disease,
			Prescription prescription) {
		super();
		this.id = id;
		this.date = date;
		this.doctor = doctor;
		this.symptoms = symptoms;
		this.disease = disease;
		this.prescription = prescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
}
