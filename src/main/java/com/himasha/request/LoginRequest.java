package com.himasha.request;


import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;



   /* public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }*/
}
