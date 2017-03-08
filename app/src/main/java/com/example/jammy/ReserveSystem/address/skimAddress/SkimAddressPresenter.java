package com.example.jammy.ReserveSystem.address.skimAddress;

import android.util.Log;

import com.example.jammy.ReserveSystem.base.BasePresenter;
import com.example.jammy.ReserveSystem.bean.Address;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jammy on 2017/3/8.
 */

public class SkimAddressPresenter extends BasePresenter<Contract.View,Contract.Model>{
    private List<Address> address;

    @Override
    public void onStart() {

    }

    public void getAddress() {
        mModel.getAddress().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Address>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                String errorCode = getErrorCode(throwable);
                Log.v("错误代码是：", errorCode);
                Log.v("添加地址失败：", throwable.getMessage());
                mView.getAddressFail(errorCode);
            }

            @Override
            public void onNext(List<Address> list) {
                mView.getAddressSuccess(list);
            }
        });
    }

    public void delAddress(String objectId){
        mModel.delAddress(objectId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                String errorCode = getErrorCode(throwable);
                Log.v("错误代码是：", errorCode);
                Log.v("删除地址失败：", throwable.getMessage());
                mView.delAddressFail(errorCode);
            }

            @Override
            public void onNext(Object o) {
                mView.delAddressSuccess();
            }
        });
    }
}
