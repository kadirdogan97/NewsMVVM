package com.kadirdogan97.newsexample.viewmodel;

import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.kadirdogan97.newsexample.model.LoginUser;

public class VMLoginActivity extends ViewModel {
    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<LoginUser> userMutableLiveData;
    public MutableLiveData<LoginUser> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }
    public void onClick(View view) {
        LoginUser loginUser = new LoginUser(emailAddress.getValue(), password.getValue());
        userMutableLiveData.setValue(loginUser);

    }
}
