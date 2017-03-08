package com.example.jammy.ReserveSystem.login;

import com.example.jammy.ReserveSystem.bean.User;

import java.util.Map;

import rx.Observable;

/**
 * Created by Jammy on 2017/1/13.
 */

public class LoginModel implements LoginContract.Model{
    @Override
    public Observable login(Map map) {
        User user = new User();
        user.setUsername((String) map.get("mobile"));
        user.setPassword((String) map.get("pwd"));
        return user.loginObservable(User.class);
    }
}
