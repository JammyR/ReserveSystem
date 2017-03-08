package com.example.jammy.ReserveSystem.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.base.BaseActivity;
import com.example.jammy.ReserveSystem.bean.User;
import com.example.jammy.ReserveSystem.main.MainActivity;
import com.example.jammy.ReserveSystem.register.RegisterActivity;
import com.example.jammy.ReserveSystem.utils.SPUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;


/**
 * Created by Jammy on 2017/1/13.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    LoginPresenter presenter;
    @BindView(R.id.tv_pwd)
    EditText tvPwd;
    @BindView(R.id.tv_mobile)
    EditText tvMobile;

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //隐藏键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);

        presenter = new LoginPresenter();
        presenter.setVM(this, new LoginModel());

    }


    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_login:
                if (isInputLegal()) {
                    Map map = new HashMap<>();
                    map.put("mobile", tvMobile.getText().toString());
                    map.put("pwd", tvPwd.getText().toString());
                    presenter.login(map);
                    showLoadingDialog();
                }
                break;

            case R.id.btn_register:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void loginSuccess(User user) {
        closeLoadingDialog();
        showToast("登录成功");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFail(String errorCode) {
        closeLoadingDialog();
        showFail(errorCode);
    }

    public boolean isInputLegal() {
        String mobile = tvMobile.getText().toString();
        String pwd  =  tvPwd.getText().toString();
        if(mobile.length()==0){
            tvMobile.setError("手机号码不能为空");
            return false;
        }else if(mobile.length()<11){
            tvMobile.setError("请输入正确的手机号");
            return false;
        }else if(pwd.length()<6){
            tvPwd.setError("密码长度要大于6位");
            return false;
        }
        return true;
    }
}
