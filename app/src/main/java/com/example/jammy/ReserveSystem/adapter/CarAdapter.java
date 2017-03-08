package com.example.jammy.ReserveSystem.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.bean.Dish;
import com.mcxtzhang.lib.AnimShopButton;
import com.mcxtzhang.lib.IOnAddDelListener;

import java.util.List;
import java.util.Map;

/**
 * Created by Jammy on 2017/1/17.
 */

public class CarAdapter extends BaseQuickAdapter<Dish,BaseViewHolder> {

    Map<String, Integer> map;
    ChangeFromCar listener;

    public CarAdapter(int layoutResId, List<Dish> data) {
        super(layoutResId, data);
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setListener(ChangeFromCar listener){
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Dish dish) {
        baseViewHolder.setText(R.id.tv_name, dish.getName())
                .setText(R.id.tv_price, "￥" + String.valueOf(dish.getPrice()));
        AnimShopButton btn = ((AnimShopButton) baseViewHolder.getConvertView().findViewById(R.id.btnEle));
        btn.setCount(map.get(dish.getName()));
        btn.setOnAddDelListener(new IOnAddDelListener() {
            @Override
            public void onAddSuccess(int i) {
                map.put(dish.getName(), map.get(dish.getName()) + 1);
                CarAdapter.this.notifyDataSetChanged();
                listener.addDish(dish);
            }

            @Override
            public void onAddFailed(int i, FailType failType) {

            }

            @Override
            public void onDelSuccess(int i) {
                if (i == 0) {
                    map.remove(dish.getName());
                    getData().remove(dish);
                    CarAdapter.this.notifyDataSetChanged();
                    listener.removeDish(dish);
                }else{
                    map.put(dish.getName(),i);
                    listener.removeDish(dish);
                }
            }

            @Override
            public void onDelFaild(int i, FailType failType) {

            }
        });
    }

    public interface ChangeFromCar{

        void addDish(Dish dish);

        void removeDish(Dish dish);

    }

}
