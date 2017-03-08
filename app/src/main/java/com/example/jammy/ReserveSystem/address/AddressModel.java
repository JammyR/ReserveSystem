package com.example.jammy.ReserveSystem.address;

import com.example.jammy.ReserveSystem.base.BaseModel;
import com.example.jammy.ReserveSystem.bean.Address;
import com.example.jammy.ReserveSystem.bean.User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import rx.Observable;

/**
 * Created by Jammy on 2017/1/19.
 */

public class AddressModel extends BaseModel implements AddressContract.Model{
    @Override
    public Observable getAddress() {
        BmobQuery<Address> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("user", BmobUser.getCurrentUser(User.class).getObjectId());
        return bmobQuery.findObjectsObservable(Address.class);
    }

    @Override
    public Observable delAddress(String objectId) {
        Address address = new Address();
        return address.deleteObservable(objectId);
    }
}
