package drools.resource;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Patient;
import drools.service.PatientService;

@RestController
public class PatientResource {

	@Autowired
	PatientService patientService;
	
	@RequestMapping(value = "/api/patients/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Patient getPatient(@PathParam("id") int id) {
		return patientService.findById(id);
	}
	
	@RequestMapping(value = "/api/patients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Patient> getPatients() {
		return patientService.findAll();
	}
	
	@RequestMapping(value = "/api/patients", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Patient newPatient(Patient patient) {
		if(patient.getId() != null) {
			return null;
		}
		
		return patientService.createNewPatient(patient);
	}
	
	@RequestMapping(value = "/api/patients/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Patient editPatient(Patient patient, @PathParam("id") int id) {
		if(patient.getId() == null || patient.getId() != id) {
			return null;
		}
		
		return patientService.updatePatient(patient);
	}
	
	@RequestMapping(value = "/api/patients/{id}", method = RequestMethod.DELETE)
	public void deletePatient(@PathParam("id") int id) {
		patientService.deletePatient(id);
	}
	
}
