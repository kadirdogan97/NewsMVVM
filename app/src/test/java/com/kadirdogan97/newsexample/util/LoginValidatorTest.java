package com.kadirdogan97.newsexample.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginValidatorTest {

    private LoginValidator loginValidator;
    public LoginValidatorTest() {
        loginValidator = new LoginValidator();
    }

    @Test
    public void isEmailValid() {
        assertTrue(loginValidator.isEmailValid("abc@aa.cc"));
    }
    @Test
    public void emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(loginValidator.isEmailValid(""));
    }
    @Test
    public void emailValidator_NullEmail_ReturnsFalse() {
        assertFalse(loginValidator.isEmailValid(null));
    }
    @Test
    public void isPasswordLengthGreaterThan5() {
        assertTrue(loginValidator.isPasswordTrue("123456"));
    }
}