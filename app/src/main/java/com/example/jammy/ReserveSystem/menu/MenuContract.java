package com.example.jammy.ReserveSystem.menu;

import com.example.jammy.ReserveSystem.bean.Dish;

import java.util.List;

import rx.Observable;

/**
 * Created by Jammy on 2017/1/16.
 */

public class MenuContract {
    interface View{
        void getDishSuccess(List<Dish> list);
        void getDishFail(String errorCode);
    }
    interface Model{
        Observable getDish();
    }
}
