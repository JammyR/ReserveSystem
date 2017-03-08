package com.example.jammy.ReserveSystem.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jammy on 2017/1/16.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragmentlist ;

    public MainFragmentAdapter(FragmentManager fm,List<Fragment> fragmentlist) {
        super(fm);
        this.fragmentlist = fragmentlist;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return fragmentlist.size();
    }
}
