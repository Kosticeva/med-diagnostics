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

import drools.model.Disease;
import drools.service.DiseaseService;

@RestController
@Path("/api/diseases")
public class DiseaseResource {

	@Autowired
	DiseaseService diseaseService;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Disease getDisease(@PathParam("id") int id) {
		return diseaseService.findById(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Disease> getDiseases() {
		return diseaseService.findAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Disease newDisease(Disease disease) {
		if(disease.getId() != null) {
			return null;
		}
		
		return diseaseService.createNewDisease(disease);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Disease editDisease(Disease disease, @PathParam("id") int id) {
		if(disease.getId() == null || disease.getId() != id) {
			return null;
		}
		
		return diseaseService.updateDisease(disease);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteDisease(@PathParam("id") int id) {
		diseaseService.deleteDisease(id);
	}
	
}