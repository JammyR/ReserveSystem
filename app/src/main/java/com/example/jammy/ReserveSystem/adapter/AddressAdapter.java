package com.example.jammy.ReserveSystem.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.bean.Address;

import java.util.List;

/**
 * Created by Jammy on 2017/1/19.
 */

public class AddressAdapter extends BaseQuickAdapter<Address,BaseViewHolder>{
    public AddressAdapter(int layoutResId, List<Address> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Address address) {
        baseViewHolder.setText(R.id.tv_name,address.getName()).setText(R.id.tv_phone,address.getPhone()).setText(R.id.tv_address,address.getAddress()).addOnClickListener(R.id.tv_del);

    }
}
