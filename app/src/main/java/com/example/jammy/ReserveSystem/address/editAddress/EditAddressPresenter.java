package com.example.jammy.ReserveSystem.address.editAddress;

import android.util.Log;

import com.example.jammy.ReserveSystem.base.BasePresenter;
import com.example.jammy.ReserveSystem.bean.Address;

import java.util.Map;
import java.util.Objects;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jammy on 2017/1/19.
 */

public class EditAddressPresenter extends BasePresenter<EditAddressActivity, EditAddressModel> {
    @Override
    public void onStart() {

    }

    public void addAddress(Map map) {
        mModel.addAddress(map).observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                String errorCode = getErrorCode(throwable);
                Log.v("错误代码是：", errorCode);
                Log.v("添加地址失败：", throwable.getMessage());
                mView.showFail(errorCode);
            }

            @Override
            public void onNext(String objectId) {
                mView.success(objectId);
            }
        });
    }

}