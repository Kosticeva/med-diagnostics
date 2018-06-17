package drools.resource;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Disease;
import drools.model.Examination;
import drools.model.Symptom;
import drools.service.QueryService;
import drools.SampleApp;

@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
public class QueryResource {
	
	@Autowired
	QueryService queryService;
	
	@RequestMapping(value="/api/query/most-probable", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Disease> getMostProbableDisease(@RequestBody Examination exam) {
		
		KieSession ks = AuthenticationResource.getKieSessionOf();
		if(ks == null) {
			return ResponseEntity.badRequest().body(null);
		}
		
		Disease d = queryService.getMostProbable(exam, ks);
		if(d != null)
			return ResponseEntity.ok().body(d);
		
		return ResponseEntity.unprocessableEntity().body(null);
	}
	
	@RequestMapping(value="/api/query/possible", method=RequestMethod.PUT,  produces = MediaType.APPLICATION_JSON, consumes=MediaType.APPLICATION_JSON)
	public List<Disease> checkSymptomsAndDiseases(@RequestBody List<Symptom> symptoms) {
		KieSession ks = AuthenticationResource.getKieSessionOf();
		if(ks == null) {
			return null;
		}
		
		return queryService.getAllPossibleDiseases(symptoms, ks);
	}
	
	@RequestMapping(value="/api/query/symptoms", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes=MediaType.APPLICATION_JSON)
	public List<Symptom> checkDiseasesAndSymptoms(@RequestBody Disease d) {
		KieSession ks = AuthenticationResource.getKieSessionOf();
		if(ks == null) {
			return null;
		}
		
		return queryService.getDiseaseSymptoms(d, ks);
	}
	
}