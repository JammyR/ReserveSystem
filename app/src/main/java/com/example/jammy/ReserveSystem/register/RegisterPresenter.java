package com.example.jammy.ReserveSystem.register;

import android.database.ContentObservable;
import android.util.Log;

import com.example.jammy.ReserveSystem.base.BasePresenter;

import java.util.Map;

import rx.Observer;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.internal.operators.OnSubscribeSingle;
import rx.schedulers.Schedulers;

/**
 * Created by Jammy on 2017/1/13.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View,RegisterContract.Model>{

    @Override
    public void onStart() {
    }

    void register(Map map){
        mModel.register(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                String errorCode = getErrorCode(throwable);
                Log.v("errorCode",errorCode);
                Log.i("error on register:",throwable.getMessage());
                mView.registerFail(errorCode);
            }

            @Override
            public void onNext(Object o) {
                mView.registerSuccess();
                Log.v("注册成功","presenter");
            }
        });
    }
}
