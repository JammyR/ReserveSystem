package com.example.jammy.ReserveSystem.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.adapter.MainFragmentAdapter;
import com.example.jammy.ReserveSystem.base.BaseActivity;
import com.example.jammy.ReserveSystem.menu.MenuFragment;
import com.example.jammy.ReserveSystem.order.OrderFragment;
import com.example.jammy.ReserveSystem.user.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Jammy on 2017/1/14.
 */

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener{

    @BindView(R.id.btn_order)
    ImageView btnOrder;
    @BindView(R.id.btn_main)
    ImageView btnMain;
    @BindView(R.id.btn_user)
    ImageView btnUser;
    @BindView(R.id.viewpager)
    ViewPager viewpager;



    private List<Fragment> fragmentlist = new ArrayList<>();
    private MainFragmentAdapter adapter;

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        btnMain.setSelected(true);
        fragmentlist.add(new MenuFragment());
        fragmentlist.add(new OrderFragment());
        fragmentlist.add(new UserFragment());
        adapter = new MainFragmentAdapter(getSupportFragmentManager(), fragmentlist);
        viewpager.setAdapter(adapter);
        viewpager.setOnPageChangeListener(this);
    }


    @OnClick({R.id.btn_order, R.id.btn_main, R.id.btn_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_order:
                viewpager.setCurrentItem(1);
                btnOrder.setSelected(true);
                btnMain.setSelected(false);
                btnUser.setSelected(false);
                break;
            case R.id.btn_main:
                viewpager.setCurrentItem(0);
                btnMain.setSelected(true);
                btnOrder.setSelected(false);
                btnUser.setSelected(false);
                break;
            case R.id.btn_user:
                viewpager.setCurrentItem(2);
                btnMain.setSelected(false);
                btnOrder.setSelected(false);
                btnUser.setSelected(true);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch(position){
            case 0:
                btnMain.setSelected(true);
                btnOrder.setSelected(false);
                btnUser.setSelected(false);
                break;

            case 1:
                btnMain.setSelected(false);
                btnOrder.setSelected(true);
                btnUser.setSelected(false);
                break;

            case 2:
                btnMain.setSelected(false);
                btnOrder.setSelected(false);
                btnUser.setSelected(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
