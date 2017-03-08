package com.example.jammy.ReserveSystem.address.editAddress;

import com.example.jammy.ReserveSystem.bean.Address;

import java.util.Map;

import rx.Observable;

/**
 * Created by Jammy on 2017/1/19.
 */

public class EditAddressContract {
    interface View{
        void success(String o);
    }

    interface Model{
        Observable addAddress(Map map);
    }

}
