package com.example.jammy.ReserveSystem.settle;

import com.example.jammy.ReserveSystem.bean.Order;

import rx.Observable;

/**
 * Created by Jammy on 2017/3/1.
 */

public class SettleContract {

    interface View{
        void commitFail(String errorCode);
        void commitSuccess();
    }

    interface Model{
        Observable commit(Order order);
    }
}
