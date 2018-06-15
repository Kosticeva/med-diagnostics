package drools.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Patient;
import drools.service.PatientService;

//@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
public class PatientResource {

	@Autowired
	PatientService patientService;
	
	@RequestMapping(value = "/api/patients/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Patient> getPatient(@PathVariable("id") Integer id) {
		if(id != null) {
			Patient p = patientService.findById(id);
			
			return ResponseEntity.ok().body(p);
		}
		return ResponseEntity.badRequest().body(null);
	}
	@RequestMapping(value = "/api/patients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Patient> getPatients() {
		return patientService.findAll();
	}
	
	@RequestMapping(value = "/api/patients", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Patient> newPatient(@RequestBody Patient patient) throws URISyntaxException {
		if(patient.getId() != null) {
			return ResponseEntity.badRequest().body(null);
		}
		
		Patient p = patientService.savePatient(patient);
		if(p!=null) {
			return ResponseEntity.created(new URI("/api/patients/"+p.getId())).body(p);
		}
		
		return ResponseEntity.unprocessableEntity().body(p);
	}
	
	@RequestMapping(value = "/api/patients/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Patient> editPatient(@RequestBody Patient patient, @PathVariable("id") Integer id) {
		if(patient.getId() == null || !patient.getId().equals(id)) {
			System.out.println("URL update sa neodgovarajucim patientom");
			return ResponseEntity.badRequest().body(null);
		}
		
		Patient p = patientService.savePatient(patient);
		if(p != null) {
			return ResponseEntity.ok().body(p);
		}
		
		return ResponseEntity.unprocessableEntity().body(p);
	}
	
	@RequestMapping(value = "/api/patients/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Patient> deletePatient(@PathVariable("id") Integer id) {
		try {
			patientService.deletePatient(id);
		}catch(SQLException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.ok().body(null);
	}
	
}
