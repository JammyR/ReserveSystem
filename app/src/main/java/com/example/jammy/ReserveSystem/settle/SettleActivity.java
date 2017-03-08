package com.example.jammy.ReserveSystem.settle;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.adapter.SettleAdapter;
import com.example.jammy.ReserveSystem.address.AddressActivity;
import com.example.jammy.ReserveSystem.base.BaseActivity;
import com.example.jammy.ReserveSystem.bean.Address;
import com.example.jammy.ReserveSystem.bean.Dish;
import com.example.jammy.ReserveSystem.bean.Order;
import com.example.jammy.ReserveSystem.bean.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Jammy on 2017/1/18.
 */

public class SettleActivity extends BaseActivity implements SettleContract.View{
    private static final int SEL_ADDRESS = 100;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_name)
    TextView tvName;
    AlertDialog dateDialog;
    DatePicker datePicker;
    @BindView(R.id.tv_date)
    TextView tvDate;
    AlertDialog timeDialog;
    TimePicker timePicker;
    @BindView(R.id.tv_time)
    TextView tvTime;
    List<Dish> list;
    Map<String, Integer> map;
    @BindView(R.id.rv)
    RecyclerView rv;
    SettleAdapter adapter;

    int sum = 0;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    Order order;
    SettlePresenter presenter;

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_settle;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        presenter = new SettlePresenter();
        presenter.setVM(this,new SettleModel());
        String listJson = (String) getIntent().getExtras().get("selList");
        String mapJson = (String) getIntent().getExtras().get("mapJson");
        Gson gson = new Gson();
        list = gson.fromJson(listJson, new TypeToken<List<Dish>>() {
        }.getType());
        map = gson.fromJson(mapJson, new TypeToken<Map<String, Integer>>() {
        }.getType());
        adapter = new SettleAdapter(R.layout.item_dish_count, list, map);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        for (int i = 0; i < list.size(); i++) {
            int count = map.get(list.get(i).getName());
            int price = list.get(i).getPrice();
            sum = sum + count * price;
        }
        tvSum.setText("总价为：￥"+sum);
        order = new Order();
        order.setList(list);
        order.setMap(map);
        order.setBuyer(BmobUser.getCurrentUser(User.class));
    }

    @OnClick({R.id.btn_address, R.id.btn_date, R.id.btn_time, R.id.btn_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_address:
                startActivityForResult(new Intent(SettleActivity.this, AddressActivity.class), SEL_ADDRESS);
                break;
            case R.id.btn_date:
                if (dateDialog == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    LayoutInflater inflater = this.getLayoutInflater();
                    final View date = inflater.inflate(R.layout.dialog_date_pick, null);
                    datePicker = (DatePicker) date.findViewById(R.id.date_picker);
                    Calendar calendar = Calendar.getInstance();

                    datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);
                    builder.setView(date).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int year = datePicker.getYear();
                            int month = datePicker.getMonth() + 1;
                            int day = datePicker.getDayOfMonth();
                            tvDate.setText(year + "年" + month + "月" + day + "日");
                            tvDate.setVisibility(View.VISIBLE);
                            order.setDate(year+"-"+month+"-"+day);
                        }
                    }).setNegativeButton("取消", null);
                    dateDialog = builder.create();
                }
                dateDialog.show();
                break;
            case R.id.btn_time:
                if (timeDialog == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    LayoutInflater inflater = this.getLayoutInflater();
                    View date = inflater.inflate(R.layout.dialog_time_pick, null);
                    timePicker = (TimePicker) date.findViewById(R.id.time_picker);
                    timePicker.setIs24HourView(true);
                    builder.setView(date).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int hour = timePicker.getHour();
                            int minute = timePicker.getMinute();
                            tvTime.setText(hour + ":" + minute);
                            tvTime.setVisibility(View.VISIBLE);
                            order.setTime(hour+":"+minute);
                        }
                    }).setNegativeButton("取消", null);
                    timeDialog = builder.create();
                }
                timeDialog.show();
                break;
            case R.id.btn_commit:
                //TODO:提交时候做的操作
                if(order.getAddress()==null||order.getDate()==null||order.getTime()==null){
                    showToast("请填写完整的订单信息");
                }else{
                    showLoadingDialog();
                    presenter.commitOrder(order);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case AddressActivity.SEL_ADDRESS_SUS:
                Address address = (Address) data.getExtras().get("address");
                tvAddress.setText(address.getAddress());
                tvName.setText(address.getName());
                tvNum.setText(address.getPhone());
                tvAddress.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                tvNum.setVisibility(View.VISIBLE);
                order.setAddress(address.getAddress());
                order.setName(address.getName());
                order.setPhone(address.getPhone());
                break;
        }
    }

    @Override
    public void commitFail(String errorCode) {
        showFail(errorCode);
        closeLoadingDialog();
    }

    @Override
    public void commitSuccess() {
        closeLoadingDialog();
        showToast("订单提交成功");
        finish();
    }
}
