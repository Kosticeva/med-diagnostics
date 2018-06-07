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

import drools.model.Prescription;
import drools.service.PrescriptionService;

@RestController
@Path("/api/prescriptions")
public class PrescriptionResource {

	@Autowired
	PrescriptionService prescriptionService;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Prescription getPrescription(@PathParam("id") int id) {
		return prescriptionService.findById(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Prescription> getPrescriptions() {
		return prescriptionService.findAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Prescription newPrescription(Prescription prescription) {
		if(prescription.getId() != null) {
			return null;
		}
		
		return prescriptionService.createNewPrescription(prescription);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Prescription editPrescription(Prescription prescription, @PathParam("id") int id) {
		if(prescription.getId() == null || prescription.getId() != id) {
			return null;
		}
		
		return prescriptionService.updatePrescription(prescription);
	}
	
	@DELETE
	@Path("/{id}")
	public void deletePrescription(@PathParam("id") int id) {
		prescriptionService.deletePrescription(id);
	}
	
}
