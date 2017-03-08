package com.example.jammy.ReserveSystem.login;

import com.example.jammy.ReserveSystem.bean.User;

import java.util.Map;

import rx.Observable;
import rx.Observer;

/**
 * Created by Jammy on 2017/1/14.
 */

public class LoginContract {

    interface View{
        /**
         * 登录成功
         */
        public void loginSuccess(User user);

        /**
         * 登录失败
         */
        public void loginFail(String errorCode);
    }

    interface Model{
        public Observable login(Map map);
    }

}
