package com.Apporio.realtysingh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class MyAdapter1 extends FragmentPagerAdapter {
    ArrayList<String> a;
    ArrayList<String> f;
        int b;


    public MyAdapter1(FragmentManager fm, int position1, ArrayList<String> b, ArrayList<String> arrayB) {
        super(fm);
        this.b =position1;
        f=arrayB;
        a=b;

    }



    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;

        frag= Basefragmentforphotoopen.newInstance(a.get(position),f.get(position));

        return frag;
    }

    @Override
    public int getCount() {
        return a.size();
    }
}