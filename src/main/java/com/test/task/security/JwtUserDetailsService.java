package com.test.task.security;

import java.util.ArrayList;

import com.test.task.model.Customer;
import com.test.task.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private CustomerRepository customerRepository;

    @Autowired
    public JwtUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer= customerRepository.findCustomerByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
            return new User(customer.getName(),
                    customer.getPassword(),
                    new ArrayList<>());
    }
}
