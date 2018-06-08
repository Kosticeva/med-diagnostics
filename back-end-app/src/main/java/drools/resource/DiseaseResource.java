package drools.resource;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Disease;
import drools.service.DiseaseService;

@RestController
public class DiseaseResource {

	@Autowired
	DiseaseService diseaseService;
	
	@RequestMapping(value = "/api/diseases/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Disease getDisease(@PathParam("id") int id) {
		return diseaseService.findById(id);
	}
	
	@RequestMapping(value = "/api/diseases", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Disease> getDiseases() {
		return diseaseService.findAll();
	}
	
	@RequestMapping(value = "/api/diseases", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Disease newDisease(Disease disease) {
		if(disease.getId() != null) {
			return null;
		}
		
		return diseaseService.createNewDisease(disease);
	}
	
	@RequestMapping(value = "/api/diseases/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Disease editDisease(Disease disease, @PathParam("id") int id) {
		if(disease.getId() == null || disease.getId() != id) {
			return null;
		}
		
		return diseaseService.updateDisease(disease);
	}
	
	@RequestMapping(value = "/api/diseases/{id}", method = RequestMethod.DELETE)
	public void deleteDisease(@PathParam("id") int id) {
		diseaseService.deleteDisease(id);
	}
	
}
