package com.example.jammy.ReserveSystem.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by Jammy on 2017/1/13.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog mDialog = null;

    /**
     * TODO 设置layout文件
     *
     * @return 目标资源layoutId
     */
    protected abstract int bindContentViewId();

    /**
     * 初始化数据 以及 各种控件的事件
     */
    protected abstract void initData(Bundle savedInstanceState);


    /**
     * 显示toast信息
     *
     * @param text
     */
    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindContentViewId());

        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        //注解绑定
        ButterKnife.bind(this);
        initData(savedInstanceState);
        Log.i("Activity is create", "activity is " + getClass());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Activity is destroy", "activity is " + getClass());
    }

    public void showLoadingDialog() {
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
            ////这里设置是否可以中断dialog的显示，若中断需要做什么操作
            mDialog.setCancelable(true);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setMessage("请稍等");
        }
        mDialog.show();
    }

    public void closeLoadingDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    //点击空白处隐藏键盘
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

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
        closeLoadingDialog();
    }

}
