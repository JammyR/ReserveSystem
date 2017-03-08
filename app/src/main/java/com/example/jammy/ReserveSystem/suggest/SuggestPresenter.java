package com.example.jammy.ReserveSystem.suggest;

import android.util.Log;

import com.example.jammy.ReserveSystem.base.BasePresenter;
import com.example.jammy.ReserveSystem.bean.Suggest;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jammy on 2017/3/8.
 */

public class SuggestPresenter extends BasePresenter<SuggestContract.View, SuggestContract.Model> {
    @Override
    public void onStart() {

    }

    public void submitSuggest(Suggest suggest) {
        mModel.submitSuggest(suggest).observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                String errorCode = getErrorCode(throwable);
                Log.v("错误代码是：", errorCode);
                Log.v("删除地址失败：", throwable.getMessage());
                mView.submitFail(errorCode);
            }

            @Override
            public void onNext(Object o) {
                mView.submitSuccess();
            }
        });
    }
}
