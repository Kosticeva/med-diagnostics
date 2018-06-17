package drools.model.reports;

import drools.model.Patient;

public class PatientReport {
	
	private Patient patient;
	private int type;

	public PatientReport() {}

	public PatientReport(Patient patient, int type) {
		super();
		this.patient = patient;
		this.type = type;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
