package drools.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Symptom;
import drools.service.SymptomService;

@RestController
@Path("/api/symptoms")
public class SymptomResource {

	@Autowired
	SymptomService symptomService;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Symptom getSymptom(@PathParam("id") int id) {
		return symptomService.findById(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Symptom> getSymptoms() {
		return symptomService.findAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Symptom newSymptom(Symptom symptom) {
		if(symptom.getId() != null) {
			return null;
		}
		
		return symptomService.createNewSymptom(symptom);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Symptom editSymptom(Symptom symptom, @PathParam("id") int id) {
		if(symptom.getId() == null || symptom.getId() != id) {
			return null;
		}
		
		return symptomService.updateSymptom(symptom);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteSymptom(@PathParam("id") int id) {
		symptomService.deleteSymptom(id);
	}
	
}
