package drools.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DoctorDetailsService doctorDetailsService;

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
		.authorizeRequests()
			.antMatchers("/api/password/**").permitAll()
			.antMatchers("/api/query/**").permitAll()
			.antMatchers("/api/reports/**").permitAll()
			.anyRequest().fullyAuthenticated()
		.and()
			.formLogin()
			.loginPage("http://localhost:4200/login")
			.loginProcessingUrl("/login")
			.failureUrl("http://localhost:4200/login")
			.usernameParameter("j_username")
			.passwordParameter("j_password")
			.permitAll()
		.and()
			/*.addFilterBefore(
                authenticationFilter(),
                UsernamePasswordAuthenticationFilter.class)*/
			.logout()
			.logoutUrl("/logout")
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
	
	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
