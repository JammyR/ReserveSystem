package com.example.jammy.ReserveSystem.address.skimAddress;

import com.example.jammy.ReserveSystem.bean.Address;

import java.util.List;

import rx.Observable;

/**
 * Created by Jammy on 2017/3/8.
 */

public class Contract {
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
