package com.example.jammy.ReserveSystem.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Jammy on 2017/3/8.
 */

public class Suggest extends BmobObject {
    private User user;
    private String suggest;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }
}
