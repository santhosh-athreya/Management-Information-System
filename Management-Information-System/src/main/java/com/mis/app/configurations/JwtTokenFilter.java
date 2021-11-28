package com.mis.app.configurations;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mis.app.exceptions.MyCustomException;
import com.mis.app.services.JwtServiceImpl;
import com.mis.app.services.MIS_JWTService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * This Filter Class
 * 
 * @author Athreya
 *
 */
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private MIS_JWTService jwtTokenProviderService;

	public JwtTokenFilter() {
		// TODO Auto-generated constructor stub
	}

	public JwtTokenFilter(MIS_JWTService jwtTokenProviderService) {

		this.jwtTokenProviderService = new JwtServiceImpl();

	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {

		// Step 1: Get Username.

		String username = null;

		String authToken = null;

		try {

			authToken = jwtTokenProviderService.parseToken(httpServletRequest);
			username = jwtTokenProviderService.getUsername(authToken);
		}

		catch (IllegalArgumentException e) {

			logger.error("An error occurred while fetching Username from Token", e);

		} catch (ExpiredJwtException e) {
			logger.warn("The token has expired", e);
		} catch (SignatureException e) {
			logger.error("Authentication Failed. Username or Password not valid.");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			try {
				if (jwtTokenProviderService.validateToken(authToken)) {
					UsernamePasswordAuthenticationToken authentication = jwtTokenProviderService
							.validateUserAndGetAuthentication(authToken);
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
					logger.info("authenticated user " + username + ", setting security context");
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch (MyCustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
