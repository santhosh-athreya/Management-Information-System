package com.mis.app.configurations;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mis.app.services.MIS_JWTService;

/**
 * This Configuration class is main Security Gateway to Authenticate and
 * Authorize our users. <br>
 * <br>
 * {@code configure(AuthenticationManagerBuilder auth)} - Configures
 * AuthenticationManager to validate our user based on UserDetailsService. <br>
 * <br>
 * {@code configure(HttpSecurity http)} - Configures Http endpoints URIs and
 * also authenticates any Request passing through this gateway.
 * 
 * <br>
 * <br>
 * <b>This class is also annotated with
 * EnableGlobalMethodSecuirty(prePostEnabled = true) - this caters the
 * application to have method level authorization(Role based - Authority
 * based).</b> <br>
 * <br>
 * 
 * @author Santhosh Athreya <br>
 *         <br>
 *         {@link https://www.linkedin.com/in/santhosh-athreya-b-3b7393165/}
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MIS_SecurityConfig extends WebSecurityConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(MIS_SecurityConfig.class);

	@Autowired
	private UnauthorizedEntryPoint unauthorizedEntryPoint;

	@Resource(name = "AuthenticateUserService")
	private UserDetailsService userDetailsService;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		logger.info("INSIDE configure method....");

		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/accounts/signup").permitAll()
				.antMatchers("/accounts/signin").permitAll()
				.antMatchers("/auth/**", "/h2-console/**", "/api-docs/**", "/swagger-ui.html", "/swagger-ui/**")
				.permitAll().anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(unauthorizedEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return authenticationManager();
	}

	@Bean
	public JwtTokenFilter authenticationTokenFilterBean() throws Exception {

		return new JwtTokenFilter();
	}

}
