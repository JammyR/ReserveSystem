package com.example.jammy.ReserveSystem.order;

import android.util.Log;

import com.example.jammy.ReserveSystem.base.BasePresenter;
import com.example.jammy.ReserveSystem.bean.Order;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jammy on 2017/3/3.
 */

public class OrderPresenter extends BasePresenter<OrderContract.View,OrderContract.Model>{
    @Override
    public void onStart() {

    }


    public void getOrderList(){
        mModel.getOrderList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Order>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                String errorCode = getErrorCode(throwable);
                Log.v("错误代码是：",errorCode);
                Log.v("获取订单列表失败：",throwable.getMessage());
                mView.getOrderListFail(errorCode);
            }

            @Override
            public void onNext(List<Order> list) {
                mView.getOrderListSuccess(list);
            }
        });
    }


    public void loadMore(){
        mModel.loadMore().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Order>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                String errorCode = getErrorCode(throwable);
                Log.v("错误代码是：",errorCode);
                Log.v("获取订单列表失败：",throwable.getMessage());
                mView.loadMoreFail(errorCode);
                mModel.loadMoreFail();
            }

            @Override
            public void onNext(List<Order> list) {
                mView.loadMoreSuccess(list);
            }
        });
    }
}
