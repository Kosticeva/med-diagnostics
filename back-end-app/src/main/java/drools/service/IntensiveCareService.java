package drools.service;

import java.util.Collection;
import drools.model.IntensiveCareReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import drools.model.Chart;
import drools.service.util.ICSimulator;

@Service
@Scope(value="singleton")
//@Scope(value="application", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IntensiveCareService {

	@Autowired
	ChartService chartService;
	
	public void startThread() {
		System.out.println("Hey im started");
		ICSimulator.getInstance().start();
	}
	
	public IntensiveCareService() {
	}

	public Collection<IntensiveCareReport> getPatientsInIC() {
		return ICSimulator.getInstance().getPatientsInIc();
	}
	
	public IntensiveCareReport addPatientToIC(Integer id) {
		Chart cc = chartService.findById(id);
		if(cc != null) {
			return ICSimulator.getInstance().addPatientToMap(id, cc);
		}
		
		return null;
	}
	
	public IntensiveCareReport removePatientFromIC(Integer id) {
		return ICSimulator.getInstance().removePatientFromMap(id);
	}
	
	public IntensiveCareReport checkIfInIC(Integer id) {
		return ICSimulator.getInstance().getPatientInIc(id);
	}
	
}
