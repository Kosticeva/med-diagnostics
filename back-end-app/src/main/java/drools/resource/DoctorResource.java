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

import drools.model.Doctor;
import drools.service.DoctorService;

@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
public class DoctorResource {

	@Autowired
	DoctorService doctorService;
	
	@RequestMapping(value = "/api/doctors/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Doctor> getDoctor(@PathVariable("id") Integer id) {
		if(id != null) {
			Doctor d = doctorService.findById(id);
			
			return ResponseEntity.ok().body(d);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@RequestMapping(value = "/api/doctors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Doctor> getDoctors() {
		return doctorService.findAll();
	}
	
	@RequestMapping(value = "/api/doctors", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Doctor> newDoctor(@RequestBody Doctor doctor) throws URISyntaxException {
		if(doctor.getLicenceId() != null) {
			return ResponseEntity.badRequest().body(null);
		}
		
		Doctor d = doctorService.createNewDoctor(doctor);
		if(d!=null) {
			return ResponseEntity.created(new URI("/api/doctors/"+d.getLicenceId())).body(d);
		}
		
		return ResponseEntity.unprocessableEntity().body(d);
	}
	
	@RequestMapping(value = "/api/doctors/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Doctor> editDoctor(@RequestBody Doctor doctor, @PathVariable("id") Integer id) {
		if(doctor.getLicenceId() == null || !doctor.getLicenceId().equals(id)) {
			System.out.println("URL update sa neodgovarajucim doctorom");
			return ResponseEntity.badRequest().body(null);
		}
		
		Doctor d = doctorService.updateDoctor(doctor);
		if(d != null) {
			return ResponseEntity.ok().body(d);
		}
		
		return ResponseEntity.unprocessableEntity().body(d);
	}
	
	@RequestMapping(value = "/api/doctors/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Doctor> deleteDoctor(@PathVariable("id") Integer id) {
		try {
			doctorService.deleteDoctor(id);
		}catch(SQLException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.ok().body(null);
	}
	
}
