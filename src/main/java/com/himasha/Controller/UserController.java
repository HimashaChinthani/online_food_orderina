package com.himasha.Controller;

import com.himasha.model.User;
import com.himasha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        // Use the injected userService instance to call the non-static method
        User user = userService.findUserByJwtToken(jwt); // Fixed: Use the instance 'userService'
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

