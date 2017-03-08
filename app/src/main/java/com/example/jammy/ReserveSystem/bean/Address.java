package com.example.jammy.ReserveSystem.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Jammy on 2017/1/19.
 */

public class Address extends BmobObject {

    private String name;
    private String phone;
    private String address;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
