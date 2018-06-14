package drools.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class DoctorUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
		 
    public  DoctorUsernamePasswordAuthenticationFilter() {
    }
 
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        
        System.out.println("Username: "+username+", Password: "+password);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        
	    setDetails(request, token);
	
	    return this.getAuthenticationManager().authenticate(token);
    }
    
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter("j_password");
    }
    
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter("j_username");
    }
}
