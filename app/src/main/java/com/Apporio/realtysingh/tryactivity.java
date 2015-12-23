package com.Apporio.realtysingh;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.Apporio.realtysingh.parsing_files.parsingforpages;
import com.Apporio.realtysingh.R;


public class tryactivity extends FragmentActivity {



    ViewPager viewPager;
    // public static final String[] TITLES = {"Home","Realty Trends","Banking","Blog","Video","Events"};
    PagerSlidingTabStrip tabs;
    public  static String s1="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tryactivity);


        viewPager =(ViewPager)findViewById(R.id.view22);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));


// lv=(ListView)findViewById(R.id.listView22);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        tabs.setViewPager(viewPager);
        tabs.setIndicatorHeight(8);
        tabs.setTextColor(Color.parseColor("#000000"));
        tabs.setUnderlineColor(Color.parseColor("#c0392b"));
        tabs.setIndicatorColor(Color.parseColor("#c0392b"));
        tabs.setDividerColor(Color.parseColor("#ffffff"));



    }

    //////////////////////////  BS EVENT ////////////////////////////////

    @Override
    protected void onResume() {
        super.onResume();
        //AppEventsLogger.activateApp(this);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // AppEventsLogger.deactivateApp(this);
    }





    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        String [] tittles ;
        String [] categoryid;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.tittles =tittles ;

            this.categoryid =categoryid;
        }

        public Fragment getItem(int num) {



            return Newsfragment.newInstance(tryactivity.this, parsingforpages.pname.get(num));
        }

        @Override
        public int getCount() {
            return parsingforpages.pname.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return parsingforpages.pname.get(position);
        }
    }



}