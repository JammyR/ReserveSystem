package com.example.jammy.ReserveSystem.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.bean.Dish;

import java.util.List;
import java.util.Map;

/**
 * Created by Jammy on 2017/3/3.
 */

public class SettleAdapter extends BaseQuickAdapter<Dish,BaseViewHolder> {

    Map<String, Integer> map;

    public SettleAdapter(int layoutResId, List<Dish> data, Map<String, Integer> map) {
        super(layoutResId, data);
        this.map = map;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Dish dish) {
        baseViewHolder.setText(R.id.tv_dish_name, dish.getName())
                .setText(R.id.tv_dish_num, "X" + map.get(dish.getName())).setText(R.id.tv_dish_price, "￥" + String.valueOf(dish.getPrice()));
    }
}
