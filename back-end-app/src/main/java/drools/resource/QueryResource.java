package drools.resource;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Disease;
import drools.model.Examination;
import drools.model.Symptom;
import drools.service.QueryService;

@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
public class QueryResource {
	
	@Autowired
	QueryService queryService;
	
	@RequestMapping(value="/api/query/most-probable/{chartId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Disease> getMostProbableDisease(@RequestBody Examination exam, @PathVariable("chartId") Integer chartId) {
		
		Disease d = queryService.getMostProbable(chartId, exam);
		if(d != null)
			return ResponseEntity.ok().body(d);
		
		return ResponseEntity.unprocessableEntity().body(null);
	}
	
	@RequestMapping(value="/api/query/possible", method=RequestMethod.PUT,  produces = MediaType.APPLICATION_JSON, consumes=MediaType.APPLICATION_JSON)
	public List<Disease> checkSymptomsAndDiseases(@RequestBody List<Symptom> symptoms) {
		return queryService.getAllPossibleDiseases(symptoms);
	}
	
	@RequestMapping(value="/api/query/symptoms", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes=MediaType.APPLICATION_JSON)
	public List<Symptom> checkDiseasesAndSymptoms(@RequestBody Disease d) {
		return queryService.getDiseaseSymptoms(d);
	}
	
}
