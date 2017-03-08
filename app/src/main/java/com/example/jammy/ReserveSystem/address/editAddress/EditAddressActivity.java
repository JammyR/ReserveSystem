package com.example.jammy.ReserveSystem.address.editAddress;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.base.BaseActivity;
import com.example.jammy.ReserveSystem.bean.Address;
import com.example.jammy.ReserveSystem.bean.User;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

/**
 * Created by Jammy on 2017/1/19.
 */

public class EditAddressActivity extends BaseActivity implements EditAddressContract.View {

    public static final int BACK = 0;
    public static final int ADD_ADDRESS_SUCCESS = 1;

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.btn_complete)
    Button btnComplete;
    @BindView(R.id.tv_person)
    EditText tvPerson;
    @BindView(R.id.tv_num)
    EditText tvNum;
    @BindView(R.id.tv_address)
    EditText tvAddress;

    EditAddressPresenter presenter;

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_edit_address;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        presenter = new EditAddressPresenter();
        presenter.setVM(this,new EditAddressModel());
    }


    @OnClick({R.id.btn_back, R.id.btn_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                Intent data=new Intent();
                setResult(BACK,data);
                finish();
                break;
            case R.id.btn_complete:
                showLoadingDialog();
                Map map = new HashMap();
                map.put("name",tvPerson.getText().toString());
                map.put("address",tvAddress.getText().toString());
                map.put("phone",tvNum.getText().toString());
                map.put("user", BmobUser.getCurrentUser(User.class));
                presenter.addAddress(map);
                break;
        }
    }

    @Override
    public void success(String o) {
        showToast("添加地址成功");
        closeLoadingDialog();
        Intent data=new Intent();
        Address address = new Address();
        address.setObjectId(o);
        address.setName(tvPerson.getText().toString());
        address.setPhone(tvNum.getText().toString());
        address.setAddress(tvAddress.getText().toString());
        address.setUser(BmobUser.getCurrentUser(User.class));
        data.putExtra("newAddress",address);
        setResult(ADD_ADDRESS_SUCCESS,data);
        finish();
    }
}
