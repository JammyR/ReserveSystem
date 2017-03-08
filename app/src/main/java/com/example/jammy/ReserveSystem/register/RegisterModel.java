package com.example.jammy.ReserveSystem.register;


import com.example.jammy.ReserveSystem.bean.User;

import java.util.Map;

import rx.Observable;

/**
 * Created by Jammy on 2017/1/13.
 */

public class RegisterModel implements RegisterContract.Model {

    @Override
    public Observable register(Map map) {
        User user = new User();
        user.setName((String) map.get("username"));
        user.setPassword((String) map.get("pwd"));
        user.setUsername((String) map.get("mobile"));
        user.setMobilePhoneNumber((String) map.get("mobile"));
        return user.signUpObservable(User.class);
    }
}
