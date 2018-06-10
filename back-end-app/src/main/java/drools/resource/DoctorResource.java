package drools.resource;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Doctor;
import drools.service.DoctorService;

@RestController
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DoctorResource {

	@Autowired
	DoctorService doctorService;
	
	@RequestMapping(value = "/api/doctors/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Doctor getDoctor(@PathParam("id") int id) {
		return doctorService.findById(id);
	}
	
	@RequestMapping(value = "/api/doctors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Doctor> getDoctors() {
		return doctorService.findAll();
	}
	
	@RequestMapping(value = "/api/doctors", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Doctor newDoctor(@RequestBody Doctor doctor) {
		if(doctor.getLicenceId() != null) {
			return null;
		}
		
		return doctorService.createNewDoctor(doctor);
	}
	
	@RequestMapping(value = "/api/doctors/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Doctor editDoctor(@RequestBody Doctor doctor, @PathParam("id") int id) {
		if(doctor.getLicenceId() == null || doctor.getLicenceId() != id) {
			return null;
		}
		
		return doctorService.updateDoctor(doctor);
	}
	
	@RequestMapping(value = "/api/doctors/{id}", method = RequestMethod.DELETE)
	public void deleteDoctor(@PathParam("id") int id) {
		doctorService.deleteDoctor(id);
	}
	
}
