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

import drools.model.Prescription;
import drools.service.PrescriptionService;

@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
public class PrescriptionResource {

	@Autowired
	PrescriptionService prescriptionService;
	
	@RequestMapping(value = "/api/prescriptions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Prescription> getPrescription(@PathVariable("id") Integer id) {
		if(id != null) {
			Prescription p = prescriptionService.findById(id);
			
			return ResponseEntity.ok().body(p);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@RequestMapping(value = "/api/prescriptions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Prescription> getPrescriptions() {
		return prescriptionService.findAll();
	}
	
	@RequestMapping(value = "/api/prescriptions", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Prescription> newPrescription(@RequestBody Prescription prescription) throws URISyntaxException {
		if(prescription.getId() != null) {
			return ResponseEntity.badRequest().body(null);
		}
		
		Prescription p = prescriptionService.savePrescription(prescription);
		if(p!=null) {
			return ResponseEntity.created(new URI("/api/prescriptions/"+p.getId())).body(p);
		}
		
		return ResponseEntity.unprocessableEntity().body(p);
	}
	
	@RequestMapping(value = "/api/prescriptions/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Prescription> editPrescription(@RequestBody Prescription prescription, @PathVariable("id") Integer id) {
		if(prescription.getId() == null || !prescription.getId().equals(id)) {
			System.out.println("URL update sa neodgovarajucim prescriptionom");
			return ResponseEntity.badRequest().body(null);
		}
		
		Prescription p = prescriptionService.savePrescription(prescription);
		if(p != null) {
			return ResponseEntity.ok().body(p);
		}
		
		return ResponseEntity.unprocessableEntity().body(p);
	}
	
	@RequestMapping(value = "/api/prescriptions/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Prescription> deletePrescription(@PathVariable("id") Integer id) {
		try {
			prescriptionService.deletePrescription(id);
		}catch(SQLException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.ok().body(null);
	}
	
}
