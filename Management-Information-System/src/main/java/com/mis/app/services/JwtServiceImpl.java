package com.mis.app.services;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mis.app.entities.User;
import com.mis.app.exceptions.MyCustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements MIS_JWTService {

	Logger logger = LoggerFactory.getLogger(MIS_JWTService.class);
	
	
	 @Autowired
	 private MyUserDetailsService repo;
	 
	 private String secretKey = "MY_SECRET_KEY";

	 private long validityInMilliseconds = 3600000; // 1h
	
	
	 @PostConstruct
	 protected void init()
	 {
		 logger.info("INSIDE MIS_JWTService... init() method...");
		 secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes()); 
	 }
	 
	@Override
	public String createToken(User user, Authentication auth) {
		
		logger.info("INSIDE CREATE TOKEN METHOD..");
		
		/*
		 * String authorities = auth.getAuthorities().stream()
		 * .map(GrantedAuthority::getAuthority) .collect(Collectors.joining(","));
		 */
		
		Claims claims = Jwts.claims().setSubject(user.getUserName());
	        claims.put("auth", user.getRole().getRole());
            claims.put("UserId", user.getID()); 
	        
	        Date now = new Date();
	        Date validity = new Date(now.getTime() + validityInMilliseconds);

	        return Jwts.builder()
	                .setClaims(claims)
	                .setIssuedAt(now)
	                .setExpiration(validity)
	                .signWith(SignatureAlgorithm.HS256, secretKey)
	                .compact();
	}

	@Override
	public UsernamePasswordAuthenticationToken validateUserAndGetAuthentication(String token) {
		
		logger.info("INSIDE..validateUserAndGetAuthentication");
		
		UserDetails user = repo.loadUserByUsername(getUsername(token));
		
		/*
		 * final Jws<Claims> claimsJws =
		 * Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		 * 
		 * final Claims claims = claimsJws.getBody();
		 * 
		 * final Collection<? extends GrantedAuthority> authorities =
		 * Arrays.stream(claims.get("auth").toString().split(","))
		 * .map(SimpleGrantedAuthority::new) .collect(Collectors.toList());
		 */
		
		logger.info("USERDETAILS.." + user.getAuthorities().toString());
		
		return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
	}

	@Override
	public String getUsername(String token) {
		
		String username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		
		logger.info("INSIDE..getUsername:- "+ username);
		
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	@Override
	public String parseToken(HttpServletRequest req) {
		
		   logger.info("INSIDE parse Token.. ");
		   String bearerToken = req.getHeader("Authorization");
		   
		 
		   
		   logger.info("bearerToken..:- " + bearerToken);
		   
	        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	            return bearerToken.substring(7);
	        }
	        return null;
	    }

	@Override
	public boolean validateToken(String token) throws MyCustomException {
	     try {
	    	 
	    	 logger.info("INSIDE validateToken..");
	    	 
	            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
	            return true;
	        } catch (JwtException | IllegalArgumentException e) {
	            throw new MyCustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}
