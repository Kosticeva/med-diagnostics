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

import drools.model.Doctor;
import drools.service.DoctorService;

@RestController
@Path("/api/doctors")
public class DoctorResource {

	@Autowired
	DoctorService doctorService;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Doctor getDoctor(@PathParam("id") int id) {
		return doctorService.findById(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Doctor> getDoctors() {
		return doctorService.findAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Doctor newDoctor(Doctor doctor) {
		if(doctor.getLicenceId() != null) {
			return null;
		}
		
		return doctorService.createNewDoctor(doctor);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Doctor editDoctor(Doctor doctor, @PathParam("id") int id) {
		if(doctor.getLicenceId() == null || doctor.getLicenceId() != id) {
			return null;
		}
		
		return doctorService.updateDoctor(doctor);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteDoctor(@PathParam("id") int id) {
		doctorService.deleteDoctor(id);
	}
	
}
