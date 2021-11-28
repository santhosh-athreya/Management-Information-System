package com.mis.app.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mis.app.entities.Company;
import com.mis.app.entities.Role;
import com.mis.app.entities.User;
import com.mis.app.exceptions.MyCustomException;
import com.mis.app.repositories.CompanyRepo;
import com.mis.app.repositories.SetRoleRepo;
import com.mis.app.repositories.SignupLoginRepo;
import com.mis.app.requests.SignUpRequest;
import com.mis.app.responses.LoginResponse;

@Service
public class Login_SignUp_Impl implements Login_SignUp_Service {

	Logger logger = LoggerFactory.getLogger(Login_SignUp_Service.class);

	@Autowired
	private SignupLoginRepo signupLogin;

	@Autowired
	private CompanyRepo companyRepository;

	@Autowired
	private SetRoleRepo assignRole;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MIS_JWTService securityService;

	@Autowired
	private AuthenticationManager AuthManager;

	@Override
	public LoginResponse login(String username, String password) throws MyCustomException {

		try {

			logger.info("INSIDE... Login_SignUp_Service Service..LOGIN Method..");

			// Step 1: Fetch Username and password and Authenticate with AuthManager

			final Authentication authentication = AuthManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			// Step 2: Get the User Object

			User user = signupLogin.findByUserName(username);

			// Step 3: Get SecurityContextHolder and set Authentication

			SecurityContextHolder.getContext().setAuthentication(authentication);

			logger.info("GET USER OBJECT: Login_SignUp_Service class: " + user.toString());

			// Construct Login Response.
			String accessToken = securityService.createToken(user, authentication);

			return new LoginResponse(user.getUserName(), user.getPassword(), accessToken);

		} catch (AuthenticationException ex) {
			throw new MyCustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	@Override
	public User signUp(SignUpRequest request) throws MyCustomException {

		if (signupLogin.existsByUserName(request.getUserName())) {
			throw new MyCustomException("User already exists!", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		// Fetch Role - Default Sign up Role is - ADMIN
		Role roles = assignRole.findByRoleType("ADMIN");

		Company companyDetails = new Company(request.getCompanyName(), request.getIndustryType(),
				request.getCompanySize(), request.getCompanyLogo());

		companyRepository.save(companyDetails);

		User user = new User(request.getUserName(), passwordEncoder.encode(request.getPassword()), "System Admin",
				roles, companyDetails);

		signupLogin.save(user);

		return user;
	}

	@Override
	public List<SimpleGrantedAuthority> getAuthority(User user) {

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));

		return authorities;
	}

}
