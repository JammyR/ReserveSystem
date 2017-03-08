package com.example.jammy.ReserveSystem.order;

import com.example.jammy.ReserveSystem.bean.Order;

import java.util.List;

import rx.Observable;

/**
 * Created by Jammy on 2017/3/3.
 */

public class OrderContract {
    interface View{
        void getOrderListSuccess(List<Order> list);
        void getOrderListFail(String errorCode);
        void loadMoreSuccess(List<Order> list);
        void loadMoreFail(String errorCode);
    }

    interface Model{
        Observable getOrderList();
        Observable loadMore();
        void loadMoreFail();
    }
}
