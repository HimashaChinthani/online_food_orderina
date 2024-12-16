package com.himasha.service;

import com.himasha.config.JwtProvider;
import com.himasha.model.User;
import com.himasha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        User user=findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws  Exception{
        User user = userRepository.findByEmail(email); // Use the injected instance 'userRepository'

        if(user==null){
            throw new Exception("Üser not found");
        }

        return user;
    }

    @Override
    public User findByJwtToken(String jwt) throws Exception {
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        User user=findUserByEmail(email);
        return user;
    }

}
