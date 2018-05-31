package drools.model;

import java.util.HashMap;

public class Chart {

	private HashMap<Integer, Examination> examinations;
	private IntensiveCare intensiveCare;
	
	public Chart() {}

	public Chart(HashMap<Integer, Examination> examinations, IntensiveCare intensiveCare) {
		super();
		this.examinations = examinations;
		this.intensiveCare = intensiveCare;
	}

	public HashMap<Integer, Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(HashMap<Integer, Examination> examinations) {
		this.examinations = examinations;
	}

	public IntensiveCare getIntensiveCare() {
		return intensiveCare;
	}

	public void setIntensiveCare(IntensiveCare intensiveCare) {
		this.intensiveCare = intensiveCare;
	}
	
}
