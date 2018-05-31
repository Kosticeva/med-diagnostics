package drools.model;

import java.util.Date;
import java.util.List;

public class Examination {

	private int id;
	private Date date;
	private Doctor doctor;
	private List<Symptom> symptoms;
	private Diagnose diagnose;
	private Prescription prescription;
	
	public Examination() {}

	public Examination(int id, Date date, Doctor doctor, List<Symptom> symptoms, Diagnose diagnose,
			Prescription prescription) {
		super();
		this.id = id;
		this.date = date;
		this.doctor = doctor;
		this.symptoms = symptoms;
		this.diagnose = diagnose;
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

	public Diagnose getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(Diagnose diagnose) {
		this.diagnose = diagnose;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	
}
