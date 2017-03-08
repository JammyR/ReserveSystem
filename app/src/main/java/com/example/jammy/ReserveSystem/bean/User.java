package com.example.jammy.ReserveSystem.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by Jammy on 2017/1/13.
 */

public class User extends BmobUser{
    private String name;
    private String pic ;//头像地址

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
