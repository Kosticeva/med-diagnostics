package drools.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	/*@Autowired
	DoctorDetailsService doctorDetailsService;*/

	/*@Bean
    public DoctorUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
		 DoctorUsernamePasswordAuthenticationFilter authenticationFilter
            = new DoctorUsernamePasswordAuthenticationFilter();
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http
			/*.authorizeRequests()
	        .anyRequest()//.authenticated()
		 .and()
            .formLogin()
            .loginPage("http://localhost:4200/login")
            .loginProcessingUrl("/login") //the URL on which the clients should post the login information
            .usernameParameter("j_username") //the username parameter in the queryString, default is 'username'
            .passwordParameter("j_password") //the password parameter in the queryString, default is 'password'
            .successHandler(this::loginSuccessHandler)
            .failureHandler(this::loginFailureHandler)
            .permitAll()
        .and()
            .logout()
            .logoutUrl("/logout") //the URL on which the clients should post if they want to logout
            .logoutSuccessHandler(this::logoutSuccessHandler)
            .invalidateHttpSession(true)
        .and()*/
			.csrf()
			.disable()
		 	/*.cors()
		 	.disable()*/;
	}
	
	/*@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(doctorDetailsService);
	}
	
	private void loginSuccessHandler(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) throws IOException {
 
		System.out.println("Uspesno logInovanje!");
        response.setStatus(HttpStatus.OK.value());
    }
	 
    private void loginFailureHandler(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException e) throws IOException {
 
    	System.out.println("Neuspesno logInovanje!");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
	 
    private void logoutSuccessHandler(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) throws IOException {
 
    	System.out.println("Uspesno logOutovanje!");
        response.setStatus(HttpStatus.OK.value());
    }*/
}
