package drools.model;

public class Diagnose {

	private int id;
	private Disease disease;
	
	public Diagnose() {}

	public Diagnose(int id, Disease disease) {
		super();
		this.id = id;
		this.disease = disease;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}
	
	
}
