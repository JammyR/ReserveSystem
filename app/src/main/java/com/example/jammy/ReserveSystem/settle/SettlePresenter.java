package com.example.jammy.ReserveSystem.settle;

import android.util.Log;

import com.example.jammy.ReserveSystem.base.BasePresenter;
import com.example.jammy.ReserveSystem.bean.Order;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jammy on 2017/3/1.
 */

public class SettlePresenter extends BasePresenter<SettleContract.View, SettleContract.Model> {
    @Override
    public void onStart() {

    }

    public void commitOrder(Order order) {
        mModel.commit(order).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                String errorCode = getErrorCode(throwable);
                Log.v("错误代码是：", errorCode);
                Log.v("删除地址失败：", throwable.getMessage());
                mView.commitFail(errorCode);
            }

            @Override
            public void onNext(Object o) {
                mView.commitSuccess();
            }
        });
    }
}
