package com.mis.app.controllers;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/login")
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
	
	    @PostMapping(value = "/signUp")
	    public ResponseEntity<User> signUp(HttpServletRequest requestHeader, @RequestBody SignUpRequest request) throws Exception {

	        User user;
	        try {
	            user = service.signUp(request);
	            return new ResponseEntity<>(user, HttpStatus.OK);
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
