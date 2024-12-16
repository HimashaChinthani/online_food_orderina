package com.himasha.service;

import com.himasha.model.USER_ROLE;
import com.himasha.model.User;
import com.himasha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user from database using email
        User user = userRepository.findByEmail(username); // Use the injected instance 'userRepository'

        // If no user is found, throw an exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        // Get role from the user, default to ROLE_CUSTOMER if role is null
        USER_ROLE role = user.getRole();
        if (role == null) {
            role = USER_ROLE.ROLE_CUSTOMER;  // Default to ROLE_CUSTOMER if no role is assigned
        }

        // Create authorities based on role (ensure "ROLE_" prefix is added)
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));  // Prefix the role with "ROLE_"

        // Return UserDetails with email, password, and authorities
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}

