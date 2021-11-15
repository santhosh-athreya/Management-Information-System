package com.mis.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mis.app.entities.User;
import com.mis.app.repositories.SignupLoginRepo;

@Service(value = "AuthenticateUserService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private SignupLoginRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		final User user = repo.findByUserName(username);
		
		if(user == null)
		{
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}
		
		return org.springframework.security.core.userdetails.User
				.withUsername(username)
				.password(user.getPassword()).authorities(new SimpleGrantedAuthority(user.getRole().getRole()))
				.accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
	}

}
