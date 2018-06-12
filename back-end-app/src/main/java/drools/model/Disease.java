package drools.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Disease  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="disease_id")
	private Integer id;
	
	@Column(nullable = false, unique = true, name="disease_name")
	private String name;
	
	//@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//private List<Symptom> symptoms;
	
	public Disease() {}

	public Disease(int id, String name, List<Symptom> symptoms) {
		super();
		this.id = id;
		this.name = name;
		//this.symptoms = symptoms;
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
	
	/*public List<Symptom> getSymptoms(){
		return this.symptoms;
	}
	
	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}*/

	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Disease)) {
			return false;
		}
		
		Disease d = (Disease) o;
		
		if(id.equals(d.getId()) && name.equals(d.getName())) {
			return true;
		}
		
		return false;
	}
}
