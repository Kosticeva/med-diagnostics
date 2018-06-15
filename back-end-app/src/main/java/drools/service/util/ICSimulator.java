package drools.service.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import org.kie.api.runtime.KieSession;

import drools.SampleApp;
import drools.model.Chart;
import drools.model.IntensiveCareReport;
import drools.model.reports.DialysisEvent;
import drools.model.reports.HeartBeatEvent;
import drools.model.reports.OxygenEvent;

public class ICSimulator extends Thread{
	
	private HashMap<Integer, IntensiveCareReport> patientsInIC;
	private static ICSimulator instance;
	
	private ICSimulator() {
		this.patientsInIC = new HashMap<Integer, IntensiveCareReport>();
	}
	
	public static ICSimulator getInstance() {
		if(instance == null) {
			instance = new ICSimulator();
		}
		
		return instance;
	}
	
	public IntensiveCareReport addPatientToMap(Integer id, Chart chart){
		if(!patientsInIC.containsKey(id)) {
			IntensiveCareReport icr = new IntensiveCareReport(true, true, true, chart);
			patientsInIC.put(id, icr);
			return icr;
		}
		
		return null;
	}

	public IntensiveCareReport removePatientFromMap(Integer id) {
		if(patientsInIC.containsKey(id)) {
			IntensiveCareReport cc = patientsInIC.remove(id);
			return cc;
		}
		
		return null;
	}
	
	public Collection<IntensiveCareReport> getPatientsInIc(){
		return patientsInIC.values();
	}
	
	public IntensiveCareReport getPatientInIc(Integer id) {
		if(patientsInIC.containsKey(id)) {
			return patientsInIC.get(id);
		}
		
		return null;
	}
	
	@Override
	public void run() {
		Random rHb = new Random();
		Random rOx = new Random();
		Random rPi = new Random();
		
		try {
			KieSession ks = SampleApp.kieContainer().newKieSession();
			
			while(true) {
				System.out.println("Beep("+patientsInIC.size()+")");
				
				for(int i=0; i<5; i++) {

					ks.getAgenda().getAgendaGroup("alerts").setFocus();
					
					for(IntensiveCareReport p: patientsInIC.values()) {
						int ph = rHb.nextInt(2);
						
						for(int j = 1; j<=ph+1; j++) {
							HeartBeatEvent h = new HeartBeatEvent(p.getChart());
							ks.insert(h);
							Thread.sleep(50);
						}
						
						int po = rPi.nextInt(3);
						
						double amm = 0.0;
						for(int j=1; j<=po+1; j++) {
							amm += 0.005;
						}
						
						DialysisEvent d = new DialysisEvent(p.getChart(), amm);
						ks.insert(d);
						
						int pp = rOx.nextInt(4);
						
						double lvl = 100.0;
						for(int j=1; j<=pp+1; j++) {
							lvl -= 10;
						}
						
						OxygenEvent o = new OxygenEvent(p.getChart(), lvl);
						ks.insert(o);

						ks.insert(p);
					}
					Thread.sleep(500);
				}
				
				ks.fireAllRules();
				//runbitch
			}
		}catch(Exception e) {
			System.err.println("Bitch im not stopping for no one");
			e.printStackTrace();
		}
	}
}
