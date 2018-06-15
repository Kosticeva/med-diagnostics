package drools.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Prescription implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prescription_id")
	private Integer id;
	
	@Column(nullable = false, name="prescription_plan")
	private String plan;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@Column(name="drug_id")
	private Drug drug;
	
	public Prescription() {}
	
	public Prescription(int id, String plan, Drug drug) {
		super();
		this.id = id;
		this.plan = plan;
		this.drug = drug;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPlan() {
		return plan;
	}
	
	public void setPlan(String plan) {
		this.plan = plan;
	}
	
	public Drug getDrug() {
		return drug;
	}
	
	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	
	@Override
	public String toString() {
		return drug.getName() + "[" + drug.getDrugType() + "]" + ", " + plan;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Prescription)) {
			return false;
		}
		
		Prescription p = (Prescription) o;
		
		if(id.equals(p.getId()) && plan.equals(p.getPlan()) && drug.equals(p.getDrug())) {
			return true;
		}
		
		return false;
	}
}
