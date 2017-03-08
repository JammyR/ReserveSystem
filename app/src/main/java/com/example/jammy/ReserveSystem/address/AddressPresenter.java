package com.example.jammy.ReserveSystem.address;

import android.util.Log;

import com.example.jammy.ReserveSystem.base.BasePresenter;
import com.example.jammy.ReserveSystem.bean.Address;
import com.example.jammy.ReserveSystem.bean.Dish;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jammy on 2017/1/19.
 */

public class AddressPresenter extends BasePresenter<AddressActivity, AddressModel> {
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
