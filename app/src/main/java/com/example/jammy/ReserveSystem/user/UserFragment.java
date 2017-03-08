package com.example.jammy.ReserveSystem.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.address.skimAddress.SkimAddressActivity;
import com.example.jammy.ReserveSystem.base.BaseFragment;
import com.example.jammy.ReserveSystem.bean.User;
import com.example.jammy.ReserveSystem.login.LoginActivity;
import com.example.jammy.ReserveSystem.news.NewsActivity;
import com.example.jammy.ReserveSystem.suggest.SuggestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * Created by Jammy on 2017/1/16.
 */

public class UserFragment extends BaseFragment implements UserContract.View {

    User user;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_tel)
    TextView tvTel;

    @Override
    protected int bindContentViewId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {
        user = BmobUser.getCurrentUser(User.class);
        tvName.setText(user.getName());
        tvTel.setText(user.getMobilePhoneNumber());
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("点击更换头像");
            }
        });
    }

    @OnClick({R.id.rl_user, R.id.rl_address, R.id.rl_suggest, R.id.rl_news, R.id.btn_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_user:
                //TODO:  编辑个人信息？   只能修改名字或密码（修改密码有点麻烦）
                break;
            case R.id.rl_address:
                startActivity(new Intent(getContext(), SkimAddressActivity.class));
                break;
            case R.id.rl_suggest:
                startActivity(new Intent(getContext(), SuggestActivity.class));
                break;
            case R.id.rl_news:
                startActivity(new Intent(getContext(), NewsActivity.class));
                break;
            case R.id.btn_exit:
                BmobUser.logOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }
}
