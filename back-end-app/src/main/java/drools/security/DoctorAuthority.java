package drools.security;

import org.springframework.security.core.GrantedAuthority;

public class DoctorAuthority implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public DoctorAuthority(String name) {
		this.name = name;
	}
	
	public String getAuthority() {
		return name;
	}

}
