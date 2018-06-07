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

import drools.model.Drug;
import drools.service.DrugService;

@RestController
@Path("/api/drugs")
public class DrugResource {

	@Autowired
	DrugService drugService;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Drug getDrug(@PathParam("id") int id) {
		return drugService.findById(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Drug> getDrugs() {
		return drugService.findAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Drug newDrug(Drug drug) {
		if(drug.getId() != null) {
			return null;
		}
		
		return drugService.createNewDrug(drug);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Drug editDrug(Drug drug, @PathParam("id") int id) {
		if(drug.getId() == null || drug.getId() != id) {
			return null;
		}
		
		return drugService.updateDrug(drug);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteDrug(@PathParam("id") int id) {
		drugService.deleteDrug(id);
	}
	
}
