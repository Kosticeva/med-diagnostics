package drools.resource;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Symptom;
import drools.service.SymptomService;

@RestController
public class SymptomResource {

	@Autowired
	SymptomService symptomService;
	
	@RequestMapping(value = "/api/symptoms/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Symptom getSymptom(@PathParam("id") int id) {
		return symptomService.findById(id);
	}
	
	@RequestMapping(value = "/api/symptoms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Symptom> getSymptoms() {
		return symptomService.findAll();
	}
	
	@RequestMapping(value = "/api/symptoms", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Symptom newSymptom(Symptom symptom) {
		if(symptom.getId() != null) {
			return null;
		}
		
		return symptomService.createNewSymptom(symptom);
	}
	
	@RequestMapping(value = "/api/symptoms/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Symptom editSymptom(Symptom symptom, @PathParam("id") int id) {
		if(symptom.getId() == null || symptom.getId() != id) {
			return null;
		}
		
		return symptomService.updateSymptom(symptom);
	}
	
	@RequestMapping(value = "/api/symptoms/{id}", method = RequestMethod.DELETE)
	public void deleteSymptom(@PathParam("id") int id) {
		symptomService.deleteSymptom(id);
	}
	
}
