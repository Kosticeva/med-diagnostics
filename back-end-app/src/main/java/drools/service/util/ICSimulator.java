package drools.service.util;

import java.util.HashMap;

import drools.model.Chart;

public class ICSimulator extends Thread{

	private HashMap<Integer, Chart> patientsInIC;
	private static ICSimulator instance;
	
	private ICSimulator() {
		this.patientsInIC = new HashMap<Integer, Chart>();
	}
	
	public static ICSimulator getInstance() {
		if(instance == null) {
			return new ICSimulator();
		}
		
		return instance;
	}
	
	public HashMap<Integer, Chart> getPatientsInIC(){
		return patientsInIC;
	}

	public void setPatientsInIC(HashMap<Integer, Chart> patientsInIC) {
		this.patientsInIC = patientsInIC;
	}
	

	public void run() {
		//ovde smisli neku logiku
	}
}
