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

@Entity
public class Disease {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Symptom> symptoms;
	
	public Disease() {}

	public Disease(int id, String name, List<Symptom> symptoms) {
		super();
		this.id = id;
		this.name = name;
		this.symptoms = symptoms;
	}

	public Integer getId() {
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
	
	public List<Symptom> getSymptoms(){
		return this.symptoms;
	}
	
	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}


}
