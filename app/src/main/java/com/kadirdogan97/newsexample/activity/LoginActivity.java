package com.kadirdogan97.newsexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.kadirdogan97.newsexample.R;
import com.kadirdogan97.newsexample.databinding.ActivityLoginBinding;
import com.kadirdogan97.newsexample.util.LoginValidator;
import com.kadirdogan97.newsexample.viewmodel.VMLoginActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private VMLoginActivity vmLoginActivity;
    private ActivityLoginBinding loginBinding;
    private LoginValidator loginValidator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        vmLoginActivity = ViewModelProviders.of(this).get(VMLoginActivity.class);
        loginBinding.setLifecycleOwner(this);
        loginBinding.setViewModel(vmLoginActivity);
        loginValidator = new LoginValidator();
        vmLoginActivity.getUser().observe(this, loginUser -> {
            if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getEmailAddress())) {
                loginBinding.tvEmail.setError("Enter an E-Mail Address");
                loginBinding.tvPassword.requestFocus();
            }
            else if (!loginValidator.isEmailValid(loginUser.getEmailAddress())) {
                loginBinding.tvEmail.setError("Enter a Valid E-mail Address");
                loginBinding.tvEmail.requestFocus();
            }
            else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getPassword())) {
                loginBinding.tvPassword.setError("Enter a Password");
                loginBinding.tvPassword.requestFocus();
            }
            else if (!loginValidator.isPasswordTrue(loginUser.getPassword())) {
                loginBinding.tvPassword.setError("Enter at least 6 Digit password");
                loginBinding.tvPassword.requestFocus();
            }
            else {
                Intent intent = new Intent(this, SourcesActivity.class);
                startActivity(intent);

            }
        });
    }
}
