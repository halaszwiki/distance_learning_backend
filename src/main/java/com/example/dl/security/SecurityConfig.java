package com.example.dl.security;

import com.example.dl.filters.JwtRequestFilter;
import com.example.dl.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.baeldung.springsecuredsockets")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().and()
				.headers()
				.frameOptions().sameOrigin().and()
				//tells spring security to not create session automatically
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/auth/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers( "/topic/**", "/app/**" ).permitAll()
				.antMatchers(
						"/secured/**/**",
						"/secured/success",
						"/secured/socket",
						"/secured/success").authenticated()
				.anyRequest().authenticated();


		//call this filter before the auth method, does the session (if the token is valid)
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}


}
