package com.example.jammy.ReserveSystem.suggest;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.base.BaseActivity;
import com.example.jammy.ReserveSystem.bean.Suggest;
import com.example.jammy.ReserveSystem.bean.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

/**
 * Created by Jammy on 2017/3/8.
 */

public class SuggestActivity extends BaseActivity implements SuggestContract.View {

    SuggestPresenter presenter;

    @BindView(R.id.et)
    EditText et;

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_suggest;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        presenter = new SuggestPresenter();
        presenter.setVM(this,new SuggestModel());
    }

    @OnClick({R.id.btn_back, R.id.btn_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_complete:
                showLoadingDialog();
                Suggest suggest = new Suggest();
                suggest.setSuggest(et.getText().toString());
                suggest.setUser(BmobUser.getCurrentUser(User.class));
                presenter.submitSuggest(suggest);
                break;
        }
    }

    @Override
    public void submitSuccess() {
        closeLoadingDialog();
        showToast("意见提交成功");
        finish();
    }

    @Override
    public void submitFail(String errorCode) {
        closeLoadingDialog();
        showFail(errorCode);
    }
}
