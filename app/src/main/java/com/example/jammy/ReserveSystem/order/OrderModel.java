package com.example.jammy.ReserveSystem.order;

import com.example.jammy.ReserveSystem.bean.Order;
import com.example.jammy.ReserveSystem.bean.User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import rx.Observable;

import static cn.bmob.v3.BmobQuery.CachePolicy.CACHE_ONLY;
import static cn.bmob.v3.BmobQuery.CachePolicy.CACHE_THEN_NETWORK;
import static cn.bmob.v3.BmobQuery.CachePolicy.NETWORK_ONLY;

/**
 * Created by Jammy on 2017/3/3.
 */

public class OrderModel implements OrderContract.Model {
    public static int PAGE_NUM = 10;
    private int page = 1;

    @Override
    public Observable getOrderList() {
        page = 1;
        BmobQuery<Order> query = new BmobQuery<Order>();
        query.include("address,buyer");
        query.addWhereEqualTo("buyer", BmobUser.getCurrentUser(User.class));
        query.setLimit(PAGE_NUM);
        boolean isCache = query.hasCachedResult(Order.class);
        if(isCache){
            query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);    // 如果有缓存的话，则设置策略为CACHE_ELSE_NETWORK
        }else{
            query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);    // 如果没有缓存的话，则设置策略为NETWORK_ELSE_CACHE
        }
        return query.findObjectsObservable(Order.class);
    }

    @Override
    public Observable loadMore() {
        BmobQuery<Order> query = new BmobQuery<Order>();
        query.setCachePolicy(CACHE_THEN_NETWORK);
        query.addWhereEqualTo("buyer", BmobUser.getCurrentUser(User.class));
        query.include("address,buyer");
        query.setLimit(PAGE_NUM);
        query.setSkip(PAGE_NUM * page++);
        return query.findObjectsObservable(Order.class);
    }

    @Override
    public void loadMoreFail() {
        page--;
    }
}
