package drools.security;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class DoctorControllerAdvice {

	@ModelAttribute("doctor")
	public DoctorDetails getDoctorDetails(Authentication auth) {
		return (auth == null) ? null : (DoctorDetails) auth.getPrincipal();
	}
}
