package drools.resource;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Prescription;
import drools.service.PrescriptionService;

@RestController
public class PrescriptionResource {

	@Autowired
	PrescriptionService prescriptionService;
	
	@RequestMapping(value = "/api/prescriptions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Prescription getPrescription(@PathParam("id") int id) {
		return prescriptionService.findById(id);
	}
	
	@RequestMapping(value = "/api/prescriptions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Prescription> getPrescriptions() {
		return prescriptionService.findAll();
	}
	
	@RequestMapping(value = "/api/prescriptions", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Prescription newPrescription(Prescription prescription) {
		if(prescription.getId() != null) {
			return null;
		}
		
		return prescriptionService.createNewPrescription(prescription);
	}
	
	@RequestMapping(value = "/api/prescriptions/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Prescription editPrescription(Prescription prescription, @PathParam("id") int id) {
		if(prescription.getId() == null || prescription.getId() != id) {
			return null;
		}
		
		return prescriptionService.updatePrescription(prescription);
	}
	
	@RequestMapping(value = "/api/prescriptions/{id}", method = RequestMethod.DELETE)
	public void deletePrescription(@PathParam("id") int id) {
		prescriptionService.deletePrescription(id);
	}
	
}
