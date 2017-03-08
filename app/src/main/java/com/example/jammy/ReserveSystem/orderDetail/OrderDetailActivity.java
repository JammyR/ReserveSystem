package com.example.jammy.ReserveSystem.orderDetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.adapter.SettleAdapter;
import com.example.jammy.ReserveSystem.base.BaseActivity;
import com.example.jammy.ReserveSystem.bean.Order;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jammy on 2017/3/6.
 */

public class OrderDetailActivity extends BaseActivity implements OrderDetailContract.View {

    Order order;
    SettleAdapter adapter;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.tv_deliver_time)
    TextView tvDeliverTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.tv_sum)
    TextView tvSum;

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        order = (Order) getIntent().getExtras().get("order");
        tvOrder.setText(tvOrder.getText().toString() + order.getObjectId());
        tvDeliverTime.setText(tvDeliverTime.getText().toString() + order.getDate() + " " + order.getTime());
        tvAddress.setText(tvAddress.getText().toString() + order.getAddress());
        tvTel.setText(tvTel.getText().toString() + order.getPhone());
        tvName.setText(tvName.getText().toString() + order.getName());
        int sum = 0;
        for (int i = 0; i < order.getList().size(); i++) {
            int num = order.getMap().get(order.getList().get(i).getName());
            sum += (order.getList().get(i).getPrice() * num);
        }
        tvSum.setText("总计：￥" + sum);
        adapter = new SettleAdapter(R.layout.item_dish_count, order.getList(), order.getMap());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }


    @OnClick(R.id.btn_back)
    public void onClick() {
        finish();
    }
}
