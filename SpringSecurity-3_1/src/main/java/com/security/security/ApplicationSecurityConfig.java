package com.security.security;

import static com.security.security.ApplicationUserPermission.COURSE_WRITE;
import static com.security.security.ApplicationUserRole.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	 public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder=passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() //
		.authorizeRequests()//we want to autorize our request
		.antMatchers("/").permitAll()
		.antMatchers("/api/**").hasRole(STUDENT.name())
		.antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(COURSE_WRITE.name())
		.antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(COURSE_WRITE.name())
		.antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(COURSE_WRITE.name())
		.antMatchers("/management/api/**").hasAnyRole(ADMIN.name(),ADMINTRANIE.name())
		.anyRequest()  //any request
		.authenticated() // client must specify username and password
		.and()
//		.build();
		.httpBasic();
	}
	
	
	@Bean
	protected UserDetailsService userDetailsService() {
		//UserDetailsService:- used how we can retrive our user from database
		 UserDetails skyfall=User
								.builder() //pick user from spring framework
								.username("Skyfall")
								.password(passwordEncoder.encode("123"))
								.roles(STUDENT.name())//internally ROLE_STUDENT
								.build();
		 
		UserDetails lindaUser= User.builder()
								 .username("linda")
								 .password(passwordEncoder.encode("123"))
								 .roles(ADMIN.name())
								 .build();
		
		UserDetails tomUser=User.builder()
								.username("tom")
								.password(passwordEncoder.encode("123"))
								.roles(ADMINTRANIE.name())
								.build();

		 return new InMemoryUserDetailsManager(skyfall,lindaUser,tomUser);
	}
	
}
