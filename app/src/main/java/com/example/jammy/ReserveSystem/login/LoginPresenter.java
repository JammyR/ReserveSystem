package com.example.jammy.ReserveSystem.login;

import android.util.Log;

import com.example.jammy.ReserveSystem.base.BasePresenter;
import com.example.jammy.ReserveSystem.bean.User;
import com.google.gson.Gson;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jammy on 2017/1/13.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View,LoginContract.Model>{


    @Override
    public void onStart() {

    }

    public void login(Map map){
        mModel.login(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<User>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                String errorCode = getErrorCode(throwable);
                Log.v("错误代码是：",errorCode);
                Log.v("登录失败：",throwable.getMessage());
                mView.loginFail(errorCode);
            }

            @Override
            public void onNext(User o) {
                mView.loginSuccess(o);
            }
        });
    }
}
