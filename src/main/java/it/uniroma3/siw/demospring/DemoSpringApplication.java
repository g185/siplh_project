package it.uniroma3.siw.demospring;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso/*OAUTH*/
@RestController/*OAUTH*/
public class DemoSpringApplication extends WebSecurityConfigurerAdapter/*OAUTH*/{

	/*OAUTH*/
	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
	
	/*OAUTH*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.antMatcher("/**")
		.authorizeRequests()/*pagine pubbliche*/
		.antMatchers("/", "/album", "/album/*","/album/*/addPhoto","/album/*/addPhoto/*", "/Gallery", "/Login", "/login**","/callback/", "/webjars/**", "/error**")
		.permitAll()
		.anyRequest()
		.authenticated();
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringApplication.class, args);
	}

}
