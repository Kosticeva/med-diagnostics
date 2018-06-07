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

import drools.model.Patient;
import drools.service.PatientService;

@RestController
@Path("/api/patients")
public class PatientResource {

	@Autowired
	PatientService patientService;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient getPatient(@PathParam("id") int id) {
		return patientService.findById(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Patient> getPatients() {
		return patientService.findAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Patient newPatient(Patient patient) {
		if(patient.getId() != null) {
			return null;
		}
		
		return patientService.createNewPatient(patient);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Patient editPatient(Patient patient, @PathParam("id") int id) {
		if(patient.getId() == null || patient.getId() != id) {
			return null;
		}
		
		return patientService.updatePatient(patient);
	}
	
	@DELETE
	@Path("/{id}")
	public void deletePatient(@PathParam("id") int id) {
		patientService.deletePatient(id);
	}
	
}
