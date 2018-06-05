package drools.model;

public class Prescription {

	private int id;
	private String plan;
	private Drug drug;
	
	public Prescription() {}
	
	public Prescription(int id, String plan, Drug drug) {
		super();
		this.id = id;
		this.plan = plan;
		this.drug = drug;
	}
	
	public int getId() {
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
