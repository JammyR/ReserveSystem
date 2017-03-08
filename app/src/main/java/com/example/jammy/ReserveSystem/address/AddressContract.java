package com.example.jammy.ReserveSystem.address;

import com.example.jammy.ReserveSystem.bean.Address;

import java.util.List;

import rx.Observable;

/**
 * Created by Jammy on 2017/1/19.
 */

public class AddressContract {
    interface View{
        void getAddressSuccess(List<Address> list);
        void getAddressFail(String errorCode);
        void delAddressSuccess();
        void delAddressFail(String errorCode);
    }
    interface Model{
        Observable getAddress();
        Observable delAddress(String objectId);
    }
}
