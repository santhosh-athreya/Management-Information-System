package com.mis.app.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mis.app.entities.User;
import com.mis.app.repositories.SignupLoginRepo;

@Service(value = "AuthenticateUserService")
public class MyUserDetailsService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	private SignupLoginRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.info("_____________INSIDE loadUserByUsername___________");

		final User user = repo.findByUserName(username);

		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

		return org.springframework.security.core.userdetails.User.withUsername(username).password(user.getPassword())
				.authorities(getAuthority(user)).accountLocked(false).credentialsExpired(false).disabled(false).build();
	}

	private Collection<? extends GrantedAuthority> getAuthority(User user) {

		logger.info("___________INSIDE getAuthority____________");

		String role = "ROLE_" + user.getRole().getRole();
		return Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

}
