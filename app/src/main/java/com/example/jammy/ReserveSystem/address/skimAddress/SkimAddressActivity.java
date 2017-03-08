package com.example.jammy.ReserveSystem.address.skimAddress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.adapter.AddressAdapter;
import com.example.jammy.ReserveSystem.address.editAddress.EditAddressActivity;
import com.example.jammy.ReserveSystem.base.BaseActivity;
import com.example.jammy.ReserveSystem.bean.Address;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jammy on 2017/1/19.
 */

public class SkimAddressActivity extends BaseActivity implements Contract.View {

    private static final int START_ADD_ADDRESS = 200;
    public static final int SEL_ADDRESS_SUS = 100;


    int delItem;
    AddressAdapter adapter;
    SkimAddressPresenter presenter;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.tv_retry)
    TextView tvRetry;
    @BindView(R.id.tv_null)
    TextView tvNull;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_address;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingDialog();
                initAddress();
            }
        });
        showLoadingDialog();
        presenter = new SkimAddressPresenter();
        presenter.setVM(this, new SkimAddressModel());
        adapter = new AddressAdapter(R.layout.item_address, new ArrayList<Address>());
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.setAdapter(adapter);
        initAddress();

        rvContent.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                delItem = position;
                showLoadingDialog();
                presenter.delAddress(((Address) (adapter.getItem(position))).getObjectId());
            }
        });
        tvTitle.setText("地址管理");
    }

    public void initAddress() {
        presenter.getAddress();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case EditAddressActivity.ADD_ADDRESS_SUCCESS:
                adapter.getData().add((Address) data.getExtras().get("newAddress"));
                adapter.notifyDataSetChanged();
                tvNull.setVisibility(View.GONE);
                break;

            case EditAddressActivity.BACK:
                break;
        }
    }

    @OnClick({R.id.btn_add, R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                startActivityForResult(new Intent(this, EditAddressActivity.class), START_ADD_ADDRESS);
                break;

            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    public void getAddressSuccess(List<Address> list) {
        adapter.setNewData(list);
        closeLoadingDialog();
        tvRetry.setVisibility(View.GONE);
        if (list.size() == 0) {
            tvNull.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getAddressFail(String errorCode) {
        showFail(errorCode);
        tvRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void delAddressSuccess() {
        adapter.remove(delItem);
        closeLoadingDialog();
        if (adapter.getItemCount() == 0) {
            tvNull.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void delAddressFail(String errorCode) {
        showFail(errorCode);
        closeLoadingDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
