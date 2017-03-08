package com.example.jammy.ReserveSystem.register;

import com.example.jammy.ReserveSystem.base.BaseContract;

import java.util.Map;

import rx.Observable;

/**
 * Created by Jammy on 2017/1/13.
 */

public class RegisterContract {
    interface  View{
        /**
         * 注册成功
         */
        public void registerSuccess();

        /**
         * 注册失败
         */
        public void registerFail(String errorCode);
    }

    interface Model{
        /**
         * 注册接口
         */
        public Observable register(Map map);

    }
}
