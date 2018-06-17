package drools.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.SampleApp;
import drools.model.Doctor;
import drools.repository.DoctorRepository;

@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
public class AuthenticationResource {

	private static Integer doctorId;
	private static KieSession kSession;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	public AuthenticationResource() {
	}
	
	@RequestMapping(value="/login", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST)
	public ResponseEntity<Integer> login(@RequestBody Doctor doctor) {
		 
		List<Doctor> d = doctorRepository.findByUsername(doctor.getUsername());
		if(d.size() == 0) {
			System.out.println("No username "+doctor.getUsername());
			return ResponseEntity.badRequest().body(null);
		}
		
		Doctor dd = d.get(0);
		if(!dd.getPassword().equals(doctor.getPassword())) {
			System.out.println("Bad password: "+dd.getPassword()+" vs "+doctor.getPassword());
			return ResponseEntity.badRequest().body(null);
		}
		
		if(dd.getLicenceId().equals(doctorId)) {
			System.out.println("OK vec postojis");
			return ResponseEntity.accepted().body(dd.getLicenceId());
		}
		
		
		doctorId = dd.getLicenceId();
		kSession = SampleApp.kieContainer().newKieSession();

		return ResponseEntity.ok().body(dd.getLicenceId());
	}
	
	
	@RequestMapping(value="/applogout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public void logout(){
		
		doctorId = null;
		kSession = null;
	}
	
	@RequestMapping(value="/authenticate/{id}", produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET)
	public ResponseEntity<Integer> getLoggedDoctor(@PathVariable("id") Integer id) {
		if(id == null) {
			if(doctorId == null) {
				System.out.println("prc");
				return ResponseEntity.badRequest().body(null);
			}else {
				return ResponseEntity.ok().body(doctorId);
			}
		}
		
		if(!doctorId.equals(id)) {
			return ResponseEntity.badRequest().body(null);
		}else {
			Doctor d = doctorRepository.findById(doctorId).get();
			
			if(d == null) {
				return ResponseEntity.badRequest().body(null);
			}
			
			return ResponseEntity.ok().body(d.getLicenceId());
		}
	}

	@RequestMapping(value="/authenticate", produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET)
	public Doctor getLoggedDoctors(){
		return doctorRepository.findById(doctorId).get();
	}
	
	public static KieSession getKieSessionOf() {
		return kSession;
	}
}
