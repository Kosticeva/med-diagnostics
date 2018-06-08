package drools.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import drools.model.Chart;

@Service
@Scope(value="application", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IntensiveCareService {

	private HashMap<Integer, Chart> patientsInIC;
	
	@Autowired
	ChartService chartService;
	
	public IntensiveCareService() {
		this.patientsInIC = new HashMap<Integer, Chart>();
	}

	public HashMap<Integer, Chart> getPatientsInIC() {
		return patientsInIC;
	}
	
	public Chart addPatientToIC(Integer id) {
		Chart cc = chartService.findById(id);
		if(!patientsInIC.containsKey(id) && cc != null) {
			patientsInIC.put(id, cc);
		}
		
		return cc;
	}
	
	public Chart removePatientFromIC(Integer id) {
		if(patientsInIC.containsKey(id)) {
			return patientsInIC.remove(id);
		}
		
		return null;
	}
	
	public Chart checkIfInIC(Integer id) {
		if(patientsInIC.containsKey(id)) {
			return patientsInIC.get(id);
		}
		
		return null;
	}
	
}
