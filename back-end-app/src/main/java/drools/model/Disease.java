package drools.model;

import java.util.List;

public class Disease {

	private int id;
	private String name;
	private int type;
	private List<Symptom> symptoms;
	
	public Disease() {}

	public Disease(int id, String name, int type, List<Symptom> symptoms) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.symptoms = symptoms;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}
	
	public List<Symptom> getSymptoms(){
		return this.symptoms;
	}
	
	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	public void setType(int type) {
		this.type = type;
	}

}
