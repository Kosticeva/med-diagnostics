package drools.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DoctorDetailsService doctorDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http
		.authorizeRequests()
			.antMatchers("/api/**").permitAll()
			.anyRequest().fullyAuthenticated()
		.and()
			.formLogin()
			.loginPage("http://localhost:4200/login")
			.loginProcessingUrl("/api/login")
			.failureUrl("http://localhost:4200/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.permitAll()
		.and()
			.logout()
			.logoutUrl("/api/logout")
			.logoutSuccessUrl("http://localhost:4200/login")
			.permitAll()
		.and()
			.csrf()
			.disable();
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(doctorDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
	
	/*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("http://domain2.com")
            .allowedMethods("PUT", "DELETE")
            .allowedHeaders("header1", "header2", "header3")
            .exposedHeaders("header1", "header2")
            .allowCredentials(false).maxAge(3600);
    }*/
}
