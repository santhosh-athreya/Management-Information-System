package com.mis.app.services;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.mis.app.entities.User;
import com.mis.app.exceptions.MyCustomException;
import com.mis.app.requests.SignUpRequest;
import com.mis.app.responses.LoginResponse;

public interface Login_SignUp_Service {

	LoginResponse login(String username, String password) throws MyCustomException;

    User signUp(SignUpRequest request) throws MyCustomException;
    
    List<SimpleGrantedAuthority> getAuthority(User user);

}
