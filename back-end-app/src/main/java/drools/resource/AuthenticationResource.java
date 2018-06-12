package drools.resource;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Doctor;
import drools.repository.DoctorRepository;

//@RestController
public class AuthenticationResource {

	//@Autowired
	//DoctorRepository doctorRepository;
	
	//@RequestMapping(value="api/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	/*public void login(@Context HttpServletRequest request, @Context HttpServletResponse response, @RequestBody Doctor doctor) throws IOException {
		if(doctor.getUsername() == null) {
			response.sendRedirect("/login");
		}
		
		/*Doctor d = doctorRepository.findByUsername(doctor.getUsername());
		if(d == null) {
			response.sendRedirect("/login");
		}
		
		if(d.getPassword().equals(doctor.getPassword())) {
			request.getSession().setAttribute("doctor", doctor);
		}else {
			response.sendRedirect("/login");
		}
	}*/
	
	//@RequestMapping(value="api/logout", method = RequestMethod.GET)
	/*public void logout(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		request.getSession().invalidate();
	}*/
}
