package com.example.jammy.ReserveSystem.register;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jammy on 2017/1/13.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View {
    RegisterPresenter presenter;
    @BindView(R.id.tv_user)
    EditText tvUser;
    @BindView(R.id.tv_pwd)
    EditText tvPwd;
    @BindView(R.id.tv_phone)
    EditText tvPhone;

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        presenter = new RegisterPresenter();
        presenter.setVM(this, new RegisterModel());
    }

    @Override
    public void registerSuccess() {
        closeLoadingDialog();
        showToast("注册成功");
        finish();
    }

    @Override
    public void registerFail(String errorCode) {
        closeLoadingDialog();
        showFail(errorCode);
    }


    @OnClick({R.id.btn_register, R.id.btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                if (isInputLegal()) {
                    Map map = new HashMap<>();
                    map.put("pwd", tvPwd.getText().toString());
                    map.put("mobile", tvPhone.getText().toString());
                    map.put("username", tvUser.getText().toString());
                    presenter.register(map);
                    showLoadingDialog();
                } else {

                }
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }

    public boolean isInputLegal() {
        String userName = tvUser.getText().toString();
        String pwd = tvPwd.getText().toString();
        String mobile = tvPhone.getText().toString();
        if(userName.length()==0){
            tvUser.setError("请输入用户名");
            return false;
        }
        else if(pwd.length()<6){
            tvPwd.setError("密码长度需大于6位");
            return false;
        }else if(mobile.length()<11){
            tvPhone.setError("请输入正确的手机号");
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
