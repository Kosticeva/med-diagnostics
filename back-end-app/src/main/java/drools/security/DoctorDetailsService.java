package drools.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import drools.model.Doctor;
import drools.model.enums.DoctorType;
import drools.repository.DoctorRepository;

//@Service
public class DoctorDetailsService implements UserDetailsService {

	//@Autowired
	private DoctorRepository doctorRepository;

	//private final PasswordEncoder passwordEncoder;
	
    public DoctorDetailsService(/*PasswordEncoder passwordEncoder*/) {
       // this.passwordEncoder = passwordEncoder;
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Autentifikacija sa korisnickim imenom: "+username);
		Doctor user = doctorRepository.findByUsername(username).get(0);
	
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
		
		return new User(user.getUsername(), /*passwordEncoder.encode("*/user.getPassword()/*")*/, auths);
	}

}
