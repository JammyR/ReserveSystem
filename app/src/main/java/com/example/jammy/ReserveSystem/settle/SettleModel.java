package com.example.jammy.ReserveSystem.settle;

import com.example.jammy.ReserveSystem.base.BaseModel;
import com.example.jammy.ReserveSystem.bean.Order;

import rx.Observable;

/**
 * Created by Jammy on 2017/3/1.
 */

public class SettleModel extends BaseModel implements SettleContract.Model{

    @Override
    public Observable commit(Order order) {
        return order.saveObservable();
    }
}
