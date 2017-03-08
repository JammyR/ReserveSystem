package com.example.jammy.ReserveSystem.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.bean.Order;

import java.util.List;

/**
 * Created by Jammy on 2017/3/3.
 */

public class OrderAdapter extends BaseQuickAdapter<Order,BaseViewHolder> {
    Context context;

    public OrderAdapter(int layoutResId, List<Order> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Order order) {

        //TODO:rv的显示问题
        int sum = 0;
        for (int i = 0; i < order.getList().size(); i++) {
            int num = order.getMap().get(order.getList().get(i).getName());
            sum += (order.getList().get(i).getPrice()*num);
        }

        baseViewHolder.setText(R.id.tv_time, "下单时间:" + String.valueOf(order.getCreatedAt())).setText(R.id.tv_sum, "共"+order.getList().size()+"件商品 ￥"+String.valueOf(sum));
        RecyclerView rv = (RecyclerView) baseViewHolder.getConvertView().findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(context));
        SettleAdapter adapter = new SettleAdapter(R.layout.item_dish_count, order.getList(), order.getMap());
        rv.setAdapter(adapter);
    }
}
