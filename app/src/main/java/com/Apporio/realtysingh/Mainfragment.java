package com.Apporio.realtysingh;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.astuetz.PagerSlidingTabStrip;
import com.Apporio.realtysingh.settergetter.Innerpagefraglist;
import com.Apporio.realtysingh.R;

import java.util.ArrayList;
import java.util.List;


public class Mainfragment extends Fragment {
    public static ViewPager viewPager;
    // public static final String[] TITLES = {"Home","Realty Trends","Banking","Blog","Video","Events"};
    PagerSlidingTabStrip tabs;
    FrameLayout news, realtytrends, banking, blog, videos,events;
    LinearLayout newscolor,newsgrey, realtytrendscolor, realtytrendsgrey, bankingcolor, bankinggrey, blogcolor, bloggrey, videocolor, videogrey,eventscolor,eventsgrey;


    public static RequestQueue queue;
    public static StringRequest sr1, sr3;
    public static List<Innerpagefraglist> data_list1;
    public static ArrayList<String> module_id = new ArrayList<String>();
    public static ArrayList<String> moduletilte = new ArrayList<String>();
    public static ArrayList<String> moduledate = new ArrayList<String>();
    public static ArrayList<String> moduledescp = new ArrayList<String>();
    public static ArrayList<String> moduleimg = new ArrayList<String>();
    public static ArrayList<String> moduleinnertitle = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mainfragment, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.view22);
        viewPager.requestDisallowInterceptTouchEvent(true);
        tabs = (PagerSlidingTabStrip) v.findViewById(R.id.tabs);
        news = (FrameLayout) v.findViewById(R.id.gallerylayout);
        realtytrends = (FrameLayout) v.findViewById(R.id.newslayout);
        banking = (FrameLayout)v. findViewById(R.id.calendarlayout);
        blog = (FrameLayout) v.findViewById(R.id.surveylayout);
        videos = (FrameLayout) v.findViewById(R.id.morelayout);
        events = (FrameLayout) v.findViewById(R.id.Events);
        newscolor = (LinearLayout) v.findViewById(R.id.gallery);
        newsgrey = (LinearLayout) v.findViewById(R.id.gallery2);
        realtytrendscolor = (LinearLayout) v.findViewById(R.id.news);
        realtytrendsgrey = (LinearLayout) v.findViewById(R.id.news2);
        bankingcolor = (LinearLayout)v. findViewById(R.id.calendar);
        bankinggrey = (LinearLayout) v.findViewById(R.id.calendar2);
        blogcolor = (LinearLayout)v. findViewById(R.id.survey);
        bloggrey = (LinearLayout) v.findViewById(R.id.survey2);
        videocolor = (LinearLayout)v. findViewById(R.id.more);
        videogrey = (LinearLayout)v. findViewById(R.id.more2);
        eventscolor = (LinearLayout)v. findViewById(R.id.events);
        eventsgrey = (LinearLayout)v. findViewById(R.id.events2);
        //queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
//        parsing(getActivity(), s1, parsingforpages.pname.get(0));
        mainfragmentwork();
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                forvisiblecolor(newscolor);
                forinvisiblecolor(newsgrey);
            }
        });
        realtytrends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                forvisiblecolor(realtytrendscolor);
                forinvisiblecolor(realtytrendsgrey);
            }
        });
        banking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
                forvisiblecolor(bankingcolor);
                forinvisiblecolor(bankinggrey);
            }
        });
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3);
                forvisiblecolor(blogcolor);
                forinvisiblecolor(bloggrey);
            }
        });
        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(4);
                forvisiblecolor(videocolor);
                forinvisiblecolor(videogrey);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(5);
                forvisiblecolor(eventscolor);
                forinvisiblecolor(eventsgrey);
            }
        });

        return v;
    }
    private void forvisiblecolor(LinearLayout layoutcolor) {
        newscolor.setVisibility(View.INVISIBLE);
        newsgrey.setVisibility(View.INVISIBLE);
        realtytrendscolor.setVisibility(View.INVISIBLE);
        realtytrendsgrey.setVisibility(View.INVISIBLE);
        bankingcolor.setVisibility(View.INVISIBLE);
        bankinggrey.setVisibility(View.INVISIBLE);
        blogcolor.setVisibility(View.INVISIBLE);
        bloggrey.setVisibility(View.INVISIBLE);
        videocolor.setVisibility(View.INVISIBLE);
        videogrey.setVisibility(View.INVISIBLE);
        eventsgrey.setVisibility(View.INVISIBLE);
        eventscolor.setVisibility(View.INVISIBLE);
        layoutcolor.setVisibility(View.VISIBLE);
    }
    private void forinvisiblecolor(LinearLayout layoutgrey) {
        newscolor.setVisibility(View.VISIBLE);
        newsgrey.setVisibility(View.VISIBLE);
        realtytrendscolor.setVisibility(View.VISIBLE);
        realtytrendsgrey.setVisibility(View.VISIBLE);
        bankingcolor.setVisibility(View.VISIBLE);
        bankinggrey.setVisibility(View.VISIBLE);
        blogcolor.setVisibility(View.VISIBLE);
        bloggrey.setVisibility(View.VISIBLE);
        videocolor.setVisibility(View.VISIBLE);
        videogrey.setVisibility(View.VISIBLE);
        eventsgrey.setVisibility(View.VISIBLE);
        eventscolor.setVisibility(View.VISIBLE);
        layoutgrey.setVisibility(View.INVISIBLE);
    }
    private void mainfragmentwork() {
       // viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        tabs.setViewPager(viewPager);
        tabs.setShouldExpand(true);
        tabs.setClickable(false);
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    forvisiblecolor(newscolor);
                    forinvisiblecolor(newsgrey);

                } else if (position == 1) {
                    forvisiblecolor(realtytrendscolor);
                    forinvisiblecolor(realtytrendsgrey);
                } else if (position == 2) {
                    forvisiblecolor(bankingcolor);
                    forinvisiblecolor(bankinggrey);
                } else if (position == 3) {
                    forvisiblecolor(blogcolor);
                    forinvisiblecolor(bloggrey);
                }
                else if (position == 4) {
                    forvisiblecolor(videocolor);
                    forinvisiblecolor(videogrey);
                } else {
                    forvisiblecolor(eventscolor);
                    forinvisiblecolor(eventsgrey);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tabs.setIndicatorHeight(8);
        tabs.setTextColor(Color.parseColor("#000000"));
        tabs.setUnderlineColor(Color.parseColor("#c0392b"));
        tabs.setIndicatorColor(Color.parseColor("#c0392b"));
        tabs.setDividerColor(Color.parseColor("#ffffff"));

    }

    @Override
    public void onResume() {
        super.onResume();
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
//        Toast.makeText(getActivity(), "chlafragres", Toast.LENGTH_SHORT).show();
        // mainfragmentwork();
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        String messages;
        private final String[] TITLES = {"","","","","","" };
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            //Fragment frag = null;
            switch (position) {

                case 0:

                    return Newsfragment.newInstance(getActivity(),"news");
                case 1:

                    return RealtyTrendsfragment.newInstance(getActivity(),"Realty Trends");
                case 2:

                    return Bankingfragment.newInstance(getActivity(),"Banking");
                case 3:

                    return Blogfragment.newInstance(getActivity(),"Blog");
                case 4:

                    return Videofragment.newInstance(getActivity(),"Video");
                case 5:

                    return Eventsfragment.newInstance(getActivity(),"Events");


                default:
                    return null;

            }
        }



    }


}


