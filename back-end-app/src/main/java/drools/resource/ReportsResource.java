package drools.resource;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Allergy;
import drools.model.Chart;
import drools.model.Patient;
import drools.service.ReportsService;

@RestController
public class ReportsResource {

	@Autowired
	ReportsService reportsService;
	
	@RequestMapping(value="/api/reports/chronics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Patient> getPossibleChronicPatients(){
		return reportsService.findChronics();
	}
	
	@RequestMapping(value="/api/reports/addicts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Patient> getPossibleAddictPatients(){
		return reportsService.findAddicts();
	}
	
	@RequestMapping(value="/api/reports/weaks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Patient> getPossibleWeakPatients(){
		return reportsService.findWeaks();
	}
	
	@RequestMapping(value="/api/reports/allergies", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes=MediaType.APPLICATION_JSON)
	public List<Allergy> checkPatientAllergies(@RequestBody Chart chart){
		return reportsService.checkAllergyWarnings(chart);
	}
	
}
