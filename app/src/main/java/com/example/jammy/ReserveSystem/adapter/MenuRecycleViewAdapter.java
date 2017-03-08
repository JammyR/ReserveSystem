package com.example.jammy.ReserveSystem.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.bean.Dish;
import com.mcxtzhang.lib.AnimShopButton;
import com.mcxtzhang.lib.IOnAddDelListener;

import java.util.List;
import java.util.Map;

/**
 * Created by Jammy on 2017/1/16.
 */

public class MenuRecycleViewAdapter extends BaseQuickAdapter<Dish,BaseViewHolder>{

    Map<String,Integer> map;
    ChangeToCar listener;
    public MenuRecycleViewAdapter(int layoutResId, List<Dish> data) {
        super(layoutResId, data);
    }

    public void setListener(ChangeToCar listener){
        this.listener = listener;
    }

    public void setMap(Map map){
        this.map = map;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Dish dish) {
        baseViewHolder.setText(R.id.tv_name,dish.getName())
                .setText(R.id.tv_price,"￥"+String.valueOf(dish.getPrice()));
        if(map.get(dish.getName())!=null){
            ((AnimShopButton)baseViewHolder.getConvertView().findViewById(R.id.btnEle)).setCount(map.get(dish.getName()));
        }else{
            ((AnimShopButton)baseViewHolder.getConvertView().findViewById(R.id.btnEle)).setCount(0);
        }
        ((AnimShopButton)baseViewHolder.getConvertView().findViewById(R.id.btnEle)).setOnAddDelListener(new IOnAddDelListener() {
            @Override
            public void onAddSuccess(int i) {
                Log.v("现在的数目是：", String.valueOf(i));
                listener.addItem(dish);
                    map.put(dish.getName(),i);
            }

            @Override
            public void onAddFailed(int i, FailType failType) {

            }

            @Override
            public void onDelSuccess(int i) {
                Log.v("现在的数目是：", String.valueOf(i));
                listener.delItem(dish);
                if(i==0){
                    map.remove(dish.getName());
                }
            }

            @Override
            public void onDelFaild(int i, FailType failType) {

            }
        });
    }

    public interface ChangeToCar{
        void addItem(Dish dish);

        void delItem(Dish dish);
    }

}
