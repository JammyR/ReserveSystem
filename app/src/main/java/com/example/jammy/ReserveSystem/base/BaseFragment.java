package com.example.jammy.ReserveSystem.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;


/**
 * Created by Jammy on 2017/1/16.
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment {


    /**
     * TODO 设置layout文件
     *
     * @return 目标资源layoutId
     */
    protected abstract int bindContentViewId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(bindContentViewId(), container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    protected abstract void initView();

    public void showFail(String errorCode) {
        switch (errorCode) {
            case "101":
                showToast("账号或密码不正确");
                break;
            case "9016":
                showToast("无网络连接，请检查您的手机网络");
                break;
            case "9010":
                showToast("网络连接超时，请重试");
                break;
            case "9018":
                showToast("账号或密码不能为空");
                break;
            case "209":
                showToast("手机号码已存在");
                break;
            case "301":
                showToast("请输入正确的手机号码");
                break;
            case "304":
                showToast("用户名或密码为空");
                break;
            default:
                showToast("网络连接失败，请重试");
                break;
        }
    }

    protected void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }


}
