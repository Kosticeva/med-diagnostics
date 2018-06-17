package drools.resource;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Allergy;
import drools.model.Drug;
import drools.model.Patient;
import drools.service.ReportsService;

@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
public class ReportsResource {

	@Autowired
	ReportsService reportsService;
	
	@RequestMapping(value="/api/reports/chronics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Patient> getPossibleChronicPatients(){
		KieSession ks = AuthenticationResource.getKieSessionOf();
		if(ks == null) {
			return null;
		}
		
		return reportsService.findChronics(ks);
	}
	
	@RequestMapping(value="/api/reports/addicts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Patient> getPossibleAddictPatients(){
		KieSession ks = AuthenticationResource.getKieSessionOf();
		if(ks == null) {
			return null;
		}
		
		return reportsService.findAddicts(ks);
	}
	
	@RequestMapping(value="/api/reports/weaks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Patient> getPossibleWeakPatients(){
		KieSession ks = AuthenticationResource.getKieSessionOf();
		if(ks == null) {
			return null;
		}
		
		return reportsService.findWeaks(ks);
	}
	
	@RequestMapping(value="/api/reports/allergies/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes=MediaType.APPLICATION_JSON)
	public List<Allergy> checkPatientAllergies(@PathVariable("id") Integer patientId, @RequestBody Drug drug){
		KieSession ks = AuthenticationResource.getKieSessionOf();
		if(ks == null) {
			return null;
		}
		
		return reportsService.checkAllergyWarnings(patientId, drug, ks);
	}
	
}
