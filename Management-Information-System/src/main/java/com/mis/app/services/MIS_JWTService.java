package com.mis.app.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.mis.app.entities.Role;
import com.mis.app.entities.User;
import com.mis.app.exceptions.MyCustomException;

public interface MIS_JWTService {
	
	public String createToken(User user, Authentication auth);
	
    public UsernamePasswordAuthenticationToken validateUserAndGetAuthentication(String token);
    
    public String getUsername(String token);
    
    public  String parseToken(HttpServletRequest req);
    
    public  boolean validateToken(String token) throws MyCustomException;

}
