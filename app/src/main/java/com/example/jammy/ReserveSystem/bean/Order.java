package com.example.jammy.ReserveSystem.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobObject;

/**
 * Created by Jammy on 2017/3/3.
 */

public class Order extends BmobObject {

    private User buyer;
    private List<Dish> list;
    private Map<String, Integer> map;
    private String date;
    private String time;
    private String address;
    private String name;
    private String phone;

    public List<Dish> getList() {
        return list;
    }

    public void setList(List<Dish> list) {
        this.list = list;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
