package com.example.jammy.ReserveSystem.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by Jammy on 2017/1/16.
 */

public class Dish extends BmobObject implements Serializable{
    private int status;
    private int price;
    private String name;
    private String detail;
    private String icon;
    private int count;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
