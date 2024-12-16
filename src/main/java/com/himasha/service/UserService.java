package com.himasha.service;

import com.himasha.model.User;
import lombok.Data;


public interface UserService {
    public  User findUserByJwtToken(String jwt) throws Exception;




    public User findUserByEmail(String email) throws Exception;


    public User findByJwtToken(String jwt) throws Exception;
}
