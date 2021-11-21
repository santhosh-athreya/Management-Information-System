package com.mis.app.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mis.app.entities.User;
import com.mis.app.exceptions.MyCustomException;
import com.mis.app.repositories.SignupLoginRepo;
import com.mis.app.requests.LoginRequest;
import com.mis.app.requests.SignUpRequest;
import com.mis.app.responses.LoginResponse;
import com.mis.app.services.Login_SignUp_Service;

@RestController
public class Signup_login_controller {

	Logger logger = LoggerFactory.getLogger(Signup_login_controller.class);
	
	@Autowired
	private Login_SignUp_Service service;
	
	@Autowired
	private SignupLoginRepo repo;
	
	@PostMapping("/accounts/signin")
	public ResponseEntity<LoginResponse> login(HttpServletRequest requestHeader, @RequestBody LoginRequest request) throws MyCustomException{
		
		logger.info("INSIDE LOGIN CONTROLLER....PASSWORD: "+ request.getPassword() + "USERNAME: " + request.getUserName());
		
		
		LoginResponse response = service.login(request.getUserName(), request.getPassword());
		
		if(response == null)
		{
			throw new MyCustomException("Login failed. Possible cause : incorrect username/password", HttpStatus.BAD_REQUEST);
		}else {
			
			return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
		}
		
	}
	
	    @PostMapping(value = "/accounts/signup")
	    public ResponseEntity<EntityModel<User>> signUp(HttpServletRequest requestHeader, @RequestBody SignUpRequest request) throws Exception {

	        User user;
	        try {
	            user = service.signUp(request);
	            
	            //Return Created Status code with Created User URI - User/{id} - best Practice to inform the end user. 
	            
	            //ServletURiComponentBuilder - Builds the URI from currentRequest.
	            
				/*
				 * URI location = ServletUriComponentsBuilder .fromCurrentRequest()
				 * .path("/{id}") .buildAndExpand(user.getID()).toUri();
				 */
	            
	            
	            //HEATOS - Provide Sign-in link to the user after Sign-up! 
	            
	            // construct model with user response
	            EntityModel<User> model = EntityModel.of(user);
	            
	            //Plain Request
	            LoginRequest req = null;
	            
	            //Build Link - attach the method 
	            WebMvcLinkBuilder links = linkTo(methodOn(this.getClass()).login(requestHeader, req));
	            
	            //add link
	            model.add(links.withRel("Sign-in"));
	            
	           
	            
	            return new  ResponseEntity<EntityModel<User>>(model,HttpStatus.CREATED);
	        } catch (Exception e) {
	            throw e;
	        }
	    }
	 
	 @PreAuthorize("hasRole('ADMIN')")
	 @GetMapping("/dashboard-admin")
	 public LoginResponse dashboard(@RequestBody LoginRequest request)
	 {
		 logger.info("Inside /dashboard api..");
		 
		 return new LoginResponse(request.getUserName(),"santhoshAthreya975@gmail.com","AccessToken-1222220dgebs");
	 }
}
