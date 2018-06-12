package drools.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.kie.api.definition.type.Position;

@Entity
public class Link implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="link_id")
	private Long id;
	
	@Position(0)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	//@Column(name="disease_id")
	private Disease disease;
	
	@Position(1)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	//@Column(name="symptom_id")
	private Symptom symptom;

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public Link(Disease disease, Symptom symptom) {
		super();
		this.disease = disease;
		this.symptom = symptom;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Link() {}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Link)) {
			return false;
		}
		
		Link l = (Link) o;
		
		if(!l.getId().equals(id) || !l.getDisease().equals(disease) || !l.getSymptom().equals(symptom)) {
			return false;
		}
		
		return true;
	}
}
