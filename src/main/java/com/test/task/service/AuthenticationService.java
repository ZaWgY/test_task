package com.test.task.service;

import com.test.task.dto.CustomerDTO;
import com.test.task.dto.TokenDTO;
import com.test.task.model.Customer;
import com.test.task.security.JwtTokenUtil;
import com.test.task.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

public interface AuthenticationService {

    /**
     * Метод создает токен
     */
    TokenDTO createAuthenticationToken (CustomerDTO customerDTO);
}
