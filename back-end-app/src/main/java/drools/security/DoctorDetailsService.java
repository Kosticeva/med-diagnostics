package drools.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import drools.model.Doctor;
import drools.model.enums.DoctorType;
import drools.repository.DoctorRepository;

@Service
public class DoctorDetailsService implements UserDetailsService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Autentifikacija sa korisnickim imenom: "+username);
		Doctor user = doctorRepository.findByUsername(username);
	
		if(user == null) {
			throw new UsernameNotFoundException("Ne postoji doktor sa unesenim imenom "+username);
		}
		
		List<DoctorAuthority> auths = new ArrayList<DoctorAuthority>();
		if(user.getType() == DoctorType.ADMINISTRATOR) {
			auths.add(new DoctorAuthority("ADMINISTRATOR"));
			auths.add(new DoctorAuthority("REGULAR"));
		}else if(user.getType() == DoctorType.REGULAR) {
			auths.add(new DoctorAuthority("REGULAR"));
		}
		
		return new DoctorDetails(user.getUsername(), passwordEncoder().encode(user.getPassword()), auths, user);
	}

}
