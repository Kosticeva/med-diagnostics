package drools.model;

import java.util.List;

public class Chart {

	private List<Examination> examinations;
	private IntensiveCare intensiveCare;
	private Patient patient;
	
	public Chart() {}

	public Chart(List<Examination> examinations, IntensiveCare intensiveCare, Patient patient) {
		super();
		this.examinations = examinations;
		this.intensiveCare = intensiveCare;
		this.patient = patient;
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

	public IntensiveCare getIntensiveCare() {
		return intensiveCare;
	}

	public void setIntensiveCare(IntensiveCare intensiveCare) {
		this.intensiveCare = intensiveCare;
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
