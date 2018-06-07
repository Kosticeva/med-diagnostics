package drools.model;

import java.util.List;

import java.util.List;

public class IntensiveCare {

	private List<Patient> patients;
	
	public IntensiveCare() {}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public IntensiveCare(List<Patient> patients) {
		super();
		this.patients = patients;
	}
	
	
}
