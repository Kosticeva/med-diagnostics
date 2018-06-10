package drools.service;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import drools.model.Chart;
import drools.service.util.ICSimulator;

@Service
@Scope(value="application", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IntensiveCareService {

	@Autowired
	ChartService chartService;
	
	@PostConstruct
	public void doPatientsIntensiveCare() {
		ICSimulator.getInstance().start();
	}
	
	public IntensiveCareService() {
		ICSimulator.getInstance();
	}

	public HashMap<Integer, Chart> getPatientsInIC() {
		return ICSimulator.getInstance().getPatientsInIC();
	}
	
	public Chart addPatientToIC(Integer id) {
		Chart cc = chartService.findById(id);
		HashMap<Integer, Chart> patientsInIC = getPatientsInIC();
		
		if(!patientsInIC.containsKey(id) && cc != null) {
			patientsInIC.put(id, cc);
			ICSimulator.getInstance().setPatientsInIC(patientsInIC);
		}
		
		return cc;
	}
	
	public Chart removePatientFromIC(Integer id) {
		HashMap<Integer, Chart> patientsInIC = getPatientsInIC();
		
		if(patientsInIC.containsKey(id)) {
			Chart cc = patientsInIC.remove(id);
			ICSimulator.getInstance().setPatientsInIC(patientsInIC);
			return cc;
		}
		
		return null;
	}
	
	public Chart checkIfInIC(Integer id) {
		if(getPatientsInIC().containsKey(id)) {
			return getPatientsInIC().get(id);
		}
		
		return null;
	}
	
}
