package drools.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String plan;
	
	@ManyToOne(fetch = FetchType.LAZY)
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
	
	public void setId(int id) {
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
}
