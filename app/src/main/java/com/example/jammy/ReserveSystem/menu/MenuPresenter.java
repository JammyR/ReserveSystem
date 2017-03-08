package com.example.jammy.ReserveSystem.menu;

import android.util.Log;

import com.example.jammy.ReserveSystem.base.BasePresenter;
import com.example.jammy.ReserveSystem.bean.Dish;
import com.example.jammy.ReserveSystem.bean.User;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jammy on 2017/1/16.
 */

public class MenuPresenter extends BasePresenter<MenuContract.View,MenuContract.Model>{
    @Override
    public void onStart() {

    }

    public void getDish(){
        mModel.getDish().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Dish>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                String errorCode = getErrorCode(throwable);
                Log.v("错误代码是：", errorCode);
                Log.v("删除地址失败：", throwable.getMessage());
                mView.getDishFail(errorCode);
            }

            @Override
            public void onNext(List<Dish> list) {
                mView.getDishSuccess(list);
            }
        });
    }
}
