package it.uniroma3.siw.demospring;

import java.security.Principal;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/images/logo.png", "/Gallery", "/album", "/album/*", "/photos","/RequestForm","/RequestForm**","/submitRequestForm", "/album/*/addPhoto/*")
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.oauth2Login()
		.and().logout().logoutSuccessUrl("/").permitAll()
		.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
}