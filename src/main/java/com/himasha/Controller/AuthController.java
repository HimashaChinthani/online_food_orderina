package com.himasha.Controller;

import com.himasha.config.JwtProvider;
import com.himasha.model.Cart;
import com.himasha.model.USER_ROLE;
import com.himasha.model.User;
import com.himasha.repository.CartRepository;
import com.himasha.repository.UserRepository;
import com.himasha.request.LoginRequest;
import com.himasha.response.AuthResponse;
import com.himasha.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        // Check if the email already exists
        User isEmailExist = userRepository.findByEmail(user.getEmail()); // Corrected repository call
        if (isEmailExist != null) {
            throw new Exception("Email is already used with another account");
        }

        // Create and save the new user
        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(createdUser);

        // Create a cart for the new user
        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        // Authenticate the user
        Authentication authentication = authenticate(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT
        String jwt = jwtProvider.generateToken(authentication);

        // Prepare response
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register success");
        authResponse.setRole(savedUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signing(@RequestBody LoginRequest req) {
        String username = req.getEmail();
        String password = req.getPassword();
        Authentication authentication = authenticate(username, password);

        // Extract the authorities (roles) from the authenticated user
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        // Safely extract the role name, defaulting to 'USER' if it's null or invalid
        String roleName = (role != null && role.startsWith("ROLE_")) ? role.replace("ROLE_", "") : "USER";

        // Generate JWT
        String jwt = jwtProvider.generateToken(authentication);

        // Prepare response
        AuthResponse authResponse = new AuthResponse();
        try {
            // Safely assign the role to the response by checking if it exists in the enum
            USER_ROLE userRole = null;
            try {
                userRole = USER_ROLE.valueOf(roleName.toUpperCase());
            } catch (IllegalArgumentException e) {
                // If the role is not found in the enum, set a default role
                userRole = USER_ROLE.ROLE_CUSTOMER; // Default role
            }
            authResponse.setRole(userRole);
        } catch (Exception e) {
            // Handle any unexpected issues
            throw new BadCredentialsException("Invalid role configuration.", e);
        }

        authResponse.setJwt(jwt);
        authResponse.setMessage("Login success");

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }



    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username....");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password.....");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
