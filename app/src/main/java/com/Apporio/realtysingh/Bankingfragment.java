package com.Apporio.realtysingh;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.Apporio.realtysingh.R;
import com.Apporio.realtysingh.adapter.Bankingadapter;
import com.Apporio.realtysingh.settergetter.Innerpagefraglist;
import com.Apporio.realtysingh.settergetter.Specificfraglistsettergetter;
import com.Apporio.realtysingh.singleton.VolleySingleton;
import com.Apporio.realtysingh.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import views.ProgressWheel;


public class Bankingfragment extends Fragment {
    public static String s1="";
    public static String s44="";
    public  static  int i=0;
    public  static  int c=0;

    public  static  int y=0;
    public  static String d[]={"1","2","3","4","5"};
    public  static Bankingadapter adp;
    public  static ListView lv;
    public static String pos;
    public static ProgressWheel pb;
    public static RequestQueue queue;
    public static StringRequest sr1,sr2;
    public static List<Innerpagefraglist> data_list1;
    public static ArrayList<String> module_id = new ArrayList<String>();
    public static ArrayList<String> moduletilte = new ArrayList<String>();
    public static ArrayList<String> moduledate = new ArrayList<String>();
    public static ArrayList<String> moduledescp = new ArrayList<String>();
    public static ArrayList<String> moduleimg = new ArrayList<String>();
    public static ArrayList<String> moduleinnertitle = new ArrayList<String>();
    public static ArrayList<String> moduleet = new ArrayList<String>();
    public static ArrayList<String> modulesrc = new ArrayList<String>();
    public static ArrayList<String> modulesrc_link = new ArrayList<String>();
    public static Fragment frag = null;
    public static SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_banking, null);
        lv=(ListView)root.findViewById(R.id.listView22);
        pb=(ProgressWheel)root.findViewById(R.id.pb);
        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.activity_main_swipe_refresh_layout);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        pos=getArguments().getString("msg22");
        parsing(getActivity(), s1, pos);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.green, R.color.orange);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        parsing(getActivity(), s1, pos);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        //Toast.makeText(getActivity(), "create", Toast.LENGTH_SHORT).show();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }




    public static Bankingfragment newInstance(FragmentActivity activity,String position ) {
        Bankingfragment f = new Bankingfragment();
        Bundle b = new Bundle();
        b.putString("msg22", position);
        //Log.e("possss",""+position);

        f.setArguments(b);

        return f;
    }










    public static void parsing(final Context activity,String s1, String s2) {

        s44=s2;

        String locationurl2 = Api_s.pagefraglist.concat(SplashActivity.s1).concat(Api_s.pagefraglist2).concat("Banking");
        locationurl2 = locationurl2.replace(" ", "%20");
        Log.e("url", "" + locationurl2);

        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//
                module_id.clear();
                moduletilte.clear();
                moduledate.clear();
                moduledescp.clear();
                moduleimg.clear();
                moduleinnertitle.clear();
                moduleet.clear();
                modulesrc.clear();
                modulesrc_link.clear();
                Newsfragment.pb.setVisibility(View.GONE);
                Newsfragment.lv.setVisibility(View.VISIBLE);

                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Specificfraglistsettergetter received2 = new Specificfraglistsettergetter();
                    received2 = gson.fromJson(response, Specificfraglistsettergetter.class);


                    data_list1 = received2.innerpagefraglist;
                    for (int i = 0; i < data_list1.size(); i++) {
                        module_id.add(data_list1.get(i).module_id);
                        moduletilte.add(data_list1.get(i).module_name);
                        moduledate.add(data_list1.get(i).module_date);
                        moduledescp.add(data_list1.get(i).module_desc_new);
                        moduleimg.add(data_list1.get(i).module_img);
                        moduleet.add(data_list1.get(i).module_state);
                        modulesrc.add(data_list1.get(i).module_source);
                        moduleinnertitle.add(data_list1.get(i).module_excerpt);
                        modulesrc_link.add(data_list1.get(i).source_link);
                    }

                    Log.e("title", SplashActivity.moduletilte + "");
                    //Toast.makeText(activity, ""+Mainfragment.viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();



                    lv.setAdapter(new Bankingadapter(activity,moduleet,modulesrc, moduletilte, moduledate,
                            moduledescp, moduleimg,modulesrc_link));
                    //adp.notifyDataSetChanged();


//                        Newsfragment.lv.setFocusable(false);


                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    Log.e("exception", "" + e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        sr2.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr2);
        Newsfragment.pb.setVisibility(View.VISIBLE);
        Newsfragment.lv.setVisibility(View.GONE);

    }

    @Override
    public void onResume() {
        super.onResume();

        //Toast.makeText(getActivity(), "chla resume", Toast.LENGTH_SHORT).show();
        parsing(getActivity(), s1, pos);

    }






}



