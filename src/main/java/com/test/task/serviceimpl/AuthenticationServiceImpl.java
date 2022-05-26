package com.test.task.serviceimpl;

import com.test.task.dto.CustomerDTO;
import com.test.task.dto.TokenDTO;
import com.test.task.security.JwtTokenUtil;
import com.test.task.security.JwtUserDetailsService;
import com.test.task.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Override
    public TokenDTO createAuthenticationToken(CustomerDTO customerDTO) {
        authenticate(customerDTO.getName(), customerDTO.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(customerDTO.getName());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new TokenDTO(token);
    }

    private void authenticate(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            log.error("DisabledException while authenticate user " + username);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            log.error("BadCredentialsException while authenticate user " + username);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
