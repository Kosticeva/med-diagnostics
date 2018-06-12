package drools.security;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import drools.model.Doctor;

public class DoctorDetails extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Doctor doctor;
	
	public DoctorDetails(String username, String password, List<DoctorAuthority> auths, Doctor d) {
		super(username, password, auths);
		
		doctor = d;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	
}
