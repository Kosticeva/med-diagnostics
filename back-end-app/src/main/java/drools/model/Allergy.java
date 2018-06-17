package drools.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
//@Table(name="allergy")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer"})
public class Allergy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="allergy_id")
	private Integer id;
	
	@Column(nullable = false, name="allergy_name", unique = true)
	private String name;
	
	/*@ManyToMany(mappedBy = "patient")
	@JsonIgnore
	private Set<Patient> patients = new HashSet<Patient>();*/
	
	public Allergy() {}

	public Allergy(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Allergy)) {
			return false;
		}
		
		Allergy a = (Allergy) o;
		
		if(id.equals(a.getId()) && name.equals(a.getName())){
			return true;
		}
		
		return false;
	}
	
}
