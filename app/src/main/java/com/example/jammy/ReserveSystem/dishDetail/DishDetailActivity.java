package com.example.jammy.ReserveSystem.dishDetail;

import android.os.Bundle;

import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.base.BaseActivity;

/**
 * Created by Jammy on 2017/3/8.
 */

public class DishDetailActivity extends BaseActivity implements DishDetailContract.View{
    @Override
    protected int bindContentViewId() {
        return R.layout.activity_dish_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
