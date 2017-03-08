package com.example.jammy.ReserveSystem.address.editAddress;

import com.example.jammy.ReserveSystem.base.BaseModel;
import com.example.jammy.ReserveSystem.bean.Address;
import com.example.jammy.ReserveSystem.bean.User;

import java.util.Map;

import rx.Observable;

/**
 * Created by Jammy on 2017/1/19.
 */

public class EditAddressModel extends BaseModel implements EditAddressContract.Model {

    @Override
    public Observable addAddress(Map map) {
        Address address = new Address();
        address.setAddress((String) map.get("address"));
        address.setName((String) map.get("name"));
        address.setPhone((String) map.get("phone"));
        address.setUser((User) map.get("user"));
        return address.saveObservable();
    }
}