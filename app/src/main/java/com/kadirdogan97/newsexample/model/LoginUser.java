package com.kadirdogan97.newsexample.model;

import android.util.Patterns;

public class LoginUser {
    private String emailAddress;
    private String password;

    public LoginUser(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEmailValid(String emailAddress) {
        return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();
    }


    public boolean isPasswordLengthGreaterThan5(String password) {
        return password.length() > 5;
    }
}
