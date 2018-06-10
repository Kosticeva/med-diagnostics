package drools.resource;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Chart;
import drools.model.Disease;
import drools.model.Symptom;
import drools.service.QueryService;

@RestController
public class QueryResource {
	
	@Autowired
	QueryService queryService;
	
	@RequestMapping(value="/api/query/most-probable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Disease getMostProbableDisease(@RequestBody Chart chart) {
		return queryService.getMostProbable(chart);
	}
	
	@RequestMapping(value="/api/query/possible", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public List<Disease> getPossibleDiseases(@RequestBody List<Symptom> symptoms) {
		return queryService.getAllPossibleDiseases(symptoms);
	}
	
	@RequestMapping(value="/api/query/symptoms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public List<Symptom> getAllSymptoms(@RequestBody Disease disease){
		return queryService.getDiseaseSymptoms(disease);
	}
	
}
