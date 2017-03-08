package com.example.jammy.ReserveSystem.menu;

import com.example.jammy.ReserveSystem.bean.Dish;
import com.example.jammy.ReserveSystem.bean.Order;

import cn.bmob.v3.BmobQuery;
import rx.Observable;

/**
 * Created by Jammy on 2017/1/16.
 */

public class MenuModel implements MenuContract.Model{
    @Override
    public Observable getDish() {
        BmobQuery<Dish> query = new BmobQuery<>();

        boolean isCache = query.hasCachedResult(Dish.class);
        if(isCache){
            query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);    // 如果有缓存的话，则设置策略为CACHE_ELSE_NETWORK
        }else{
            query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);    // 如果没有缓存的话，则设置策略为NETWORK_ELSE_CACHE
        }

        return query.findObjectsObservable(Dish.class);
    }
}
