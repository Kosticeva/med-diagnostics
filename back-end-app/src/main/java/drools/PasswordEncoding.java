package drools;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordEncoding {

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@RequestMapping(value="/api/password/{pass}", method=RequestMethod.POST)
	public void getPassword(@PathVariable("pass") String password) {
		System.out.println(password);
		String kkk = passwordEncoder().encode(password);
		System.out.println(kkk);
		System.out.println(passwordEncoder().encode(kkk));
	}
	
}
