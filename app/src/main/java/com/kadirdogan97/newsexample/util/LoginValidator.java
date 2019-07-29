package com.kadirdogan97.newsexample.util;

import androidx.core.util.PatternsCompat;

public class LoginValidator {

    public boolean isEmailValid(String emailAddress) {
        if(emailAddress!=null)
            return PatternsCompat.EMAIL_ADDRESS.matcher(emailAddress).matches();
        else
            return false;
    }


    public boolean isPasswordTrue(String password) {
        return password.length() > 5;
    }
}
