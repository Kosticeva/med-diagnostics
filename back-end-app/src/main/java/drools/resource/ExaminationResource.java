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

import drools.model.Examination;
import drools.service.ExaminationService;

@RestController
@Path("/api/examinations")
public class ExaminationResource {

	@Autowired
	ExaminationService examinationService;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Examination getExamination(@PathParam("id") int id) {
		return examinationService.findById(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Examination> getExaminations() {
		return examinationService.findAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Examination newExamination(Examination examination) {
		if(examination.getId() != null) {
			return null;
		}
		
		return examinationService.createNewExamination(examination);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Examination editExamination(Examination examination, @PathParam("id") int id) {
		if(examination.getId() == null || examination.getId() != id) {
			return null;
		}
		
		return examinationService.updateExamination(examination);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteExamination(@PathParam("id") int id) {
		examinationService.deleteExamination(id);
	}
	
}
