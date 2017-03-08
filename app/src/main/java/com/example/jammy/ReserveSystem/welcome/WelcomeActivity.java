package com.example.jammy.ReserveSystem.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jammy.ReserveSystem.Constant;
import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.base.BaseActivity;
import com.example.jammy.ReserveSystem.bean.User;
import com.example.jammy.ReserveSystem.login.LoginActivity;
import com.example.jammy.ReserveSystem.main.MainActivity;

import butterknife.BindView;
import cn.bmob.v3.BmobUser;

/**
 * Created by Jammy on 2017/1/13.
 */

public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.iv_welcome)
    ImageView iv_welcome;

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Glide.with(this).load(Constant.WELCOME_PIC).into(iv_welcome);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(BmobUser.getCurrentUser(User.class)!=null){
                    ////登陆过的话自动登录
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                }
                else startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                finish();
            }
        },1000);
    }

}
