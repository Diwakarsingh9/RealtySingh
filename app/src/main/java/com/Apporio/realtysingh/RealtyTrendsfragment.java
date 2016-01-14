package com.Apporio.realtysingh;


////////////////////////////////////////////////////////////

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
import com.Apporio.realtysingh.adapter.Realtytrendsadapter;
import com.Apporio.realtysingh.settergetter.Innerphoto;
import com.Apporio.realtysingh.settergetter.Speci2;
import com.Apporio.realtysingh.settergetter.innerspeci2;
import com.Apporio.realtysingh.settergetter.photossettergetteroutter;
import com.Apporio.realtysingh.singleton.VolleySingleton;
import com.Apporio.realtysingh.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.List;

import views.ProgressWheel;


public class RealtyTrendsfragment extends Fragment {
    public static String s1="";
    public static String s44="";
    public  static  int i=0;
    public  static  int y=0;
    public  static  int c=0;
    public  static String d[]={"1","2","3","4","5"};
    public  static Realtytrendsadapter adp22;
    public  static ListView lv;
    public static String pos;
    public static ProgressWheel pb;
    public static RequestQueue queue,queue2;
    public static StringRequest sr1,sr2;
    public static List<innerspeci2> data_list1;
    public static List<Innerphoto> data_list11;
    public static ArrayList<String> gallery_id = new ArrayList<String>();
    public static ArrayList<String> category = new ArrayList<String>();
    public static ArrayList<String> title1 = new ArrayList<String>();
    public static ArrayList<String> image1 = new ArrayList<String>();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_blank2, null);
        lv=(ListView)root.findViewById(R.id.listView22);
        pb=(ProgressWheel)root.findViewById(R.id.pb);
        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.activity_main_swipe_refresh_layout);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        queue2 = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        pos=getArguments().getString("msg22");
        parsing(getActivity(), s1, pos);
        parsingforphotos(getActivity(), s44);
        //Toast.makeText(getActivity(), "create", Toast.LENGTH_SHORT).show();
//
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.green, R.color.orange);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        parsing(getActivity(), s1, pos);
                        parsingforphotos(getActivity(), s44);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Toast.makeText(getActivity(), "" + position+module_id.size(), Toast.LENGTH_SHORT).show();
//                Intent in = new Intent(getActivity(), ListInneractivity.class);
//                if (position< module_id.size()) {
//                    in.putExtra("title", s44);
//                    in.putExtra("head", moduleinnertitle.get(position));
//                    in.putExtra("date", moduledate.get(position));
//                    in.putExtra("descp", moduledescp.get(position));
//                    in.putExtra("img", moduleimg.get(position));
//                    in.putExtra("src", modulesrc.get(position));
//                    in.putExtra("plc", moduleet.get(position));
//                    in.putExtra("id", module_id.get(position));
//                    startActivity(in);
//
//                } else {
//                    in.putExtra("title", s44);
//                    in.putExtra("head", moduleinnertitle.get(position-1));
//                    in.putExtra("date", moduledate.get(position-1));
//                    in.putExtra("descp", moduledescp.get(position-1));
//                    in.putExtra("img", moduleimg.get(position-1));
//                    in.putExtra("src", modulesrc.get(position-1));
//                    in.putExtra("plc", moduleet.get(position-1));
//                    in.putExtra("id", module_id.get(position-1));
//                    startActivity(in);
//                }
            }
        });

        return root;
    }
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            try {
//                parsing(getActivity(), s1, parsingforpages.pname.get(pos));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
////            Toast.makeText(getActivity(), "gggggggggg ", Toast.LENGTH_SHORT).show();
//        }
//    }



    public static RealtyTrendsfragment newInstance(FragmentActivity activity,String position ) {
        RealtyTrendsfragment f = new RealtyTrendsfragment();
        Bundle b = new Bundle();
        b.putString("msg22", position);
        //Log.e("possss",""+position);

        f.setArguments(b);

        return f;
    }










    public static void parsing(final Context activity,String s1,String s2) {

        s44=s2;

        String locationurl2 = Api_s.pagefraglist.concat(SplashActivity.s1).concat(Api_s.pagefraglist2).concat("Realty Trends");
        locationurl2 = locationurl2.replace(" ", "%20");
        Log.e("url", "" + locationurl2);

        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                module_id.clear();
                moduletilte.clear();
                moduledate.clear();
                moduledescp.clear();
                moduleimg.clear();
                moduleinnertitle.clear();
                moduleet.clear();
                modulesrc.clear();
                modulesrc_link.clear();
                RealtyTrendsfragment.pb.setVisibility(View.GONE);
                RealtyTrendsfragment.lv.setVisibility(View.VISIBLE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Speci2 received2 = new Speci2();
                    received2 = gson.fromJson(response, Speci2.class);


                    data_list1 = received2.innerspeci2;
                    for (int i = 0; i < data_list1.size(); i++) {
                        module_id.add(data_list1.get(i).module_id);
                        moduletilte.add(data_list1.get(i).module_name);
                        moduledate.add(data_list1.get(i).module_date);
                        moduledescp.add(data_list1.get(i).module_desc_new);
                        moduleimg.add(data_list1.get(i).module_img);
                        moduleinnertitle.add(data_list1.get(i).module_excerpt);
                        moduleet.add(data_list1.get(i).module_state);
                        modulesrc.add(data_list1.get(i).module_source);
                        modulesrc_link.add(data_list1.get(i).source_link);
                    }


                    //Toast.makeText(activity, ""+Mainfragment.viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();



                    lv.setAdapter(new Realtytrendsadapter(activity,moduleet,modulesrc, moduletilte, moduledate,
                            moduledescp, moduleimg,image1,title1,modulesrc_link));
                    //adp22.notifyDataSetChanged();


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
                   RealtyTrendsfragment.pb.setVisibility(View.VISIBLE);
            RealtyTrendsfragment.lv.setVisibility(View.GONE);

    }
    public static void parsingforphotos(final Context activity,String s1) {



        String locationurl2 = Api_s.photosgallery.concat("Realty Trends");
        locationurl2 = locationurl2.replace(" ", "%20");
        Log.e("url", "" + locationurl2);

        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//
                gallery_id.clear();
                category.clear();
                image1.clear();
                title1.clear();



                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    photossettergetteroutter received2 = new photossettergetteroutter();
                    received2 = gson.fromJson(response, photossettergetteroutter.class);


                    data_list11 = received2.innerphoto;
                    for (int i = 0; i < data_list11.size(); i++) {
                        gallery_id.add(data_list11.get(i).gallery_id);
                        category.add(data_list11.get(i).category);
                        image1.add(data_list11.get(i).image);
                        title1.add(data_list11.get(i).title);

                    }

                    Log.e("title", SplashActivity.moduletilte + "");
                    //Toast.makeText(activity, ""+Mainfragment.viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();



                    lv.setAdapter(new Realtytrendsadapter(activity,moduleet,modulesrc, moduletilte, moduledate,
                            moduledescp, moduleimg,image1,title1, modulesrc_link));
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
        queue2.add(sr2);


    }
    @Override
    public void onResume() {
        super.onResume();

        parsing(getActivity(), s1, pos);
        parsingforphotos(getActivity(), s44);

    }
}




//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.example.spinno.realitysingh.adapter.NewsAdapter;
//import com.example.spinno.realitysingh.settergetter.Innerpagefraglist;
//import com.example.spinno.realitysingh.settergetter.Specificfraglistsettergetter;
//import com.example.spinno.realitysingh.singleton.VolleySingleton;
//import com.example.spinno.realitysingh.urlapi.Api_s;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//
///**
// * Created by neokree on 16/12/14.
// */
//public class Newsfragment extends Fragment {
//    public static String s1="";
//    public  static  int i=0;
//    public  static  int y;
//    public  static String d[]={"1","2","3","4","5"};
//    public  static NewsAdapter adp;
//    public  static ListView lv;
//   public static int pos;
//    public static ProgressBar pb;
//
//    public static List<Innerpagefraglist> data_list1;
//    public static ArrayList<String> module_id = new ArrayList<String>();
//    public static ArrayList<String> moduletilte = new ArrayList<String>();
//    public static ArrayList<String> moduledate = new ArrayList<String>();
//    public static ArrayList<String> moduledescp = new ArrayList<String>();
//    public static ArrayList<String> moduleimg = new ArrayList<String>();
//    public static ArrayList<String> moduleinnertitle = new ArrayList<String>();
//    public static Fragment frag = null;
//    tryactivity trya = new tryactivity();
//    StringRequest sr;
//    RequestQueue queue;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View v = inflater.inflate(R.layout.fragment_blank, container, false);
//        lv=(ListView)v.findViewById(R.id.listView22);
//        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
//
//        viewproduct();
//        return v;
//    }
//
//    public static Newsfragment newInstance(Context ctc ,  String text) {
//
//        Newsfragment f = new Newsfragment();
//        Bundle b = new Bundle();
//        b.putString("msg", text);
//        f.setArguments(b);
//
//        return f;
//    }
//
//    public void viewproduct() {
//        String s2 = getArguments().getString("msg");
//
//        String viewproducturl  = Api_s.pagefraglist.concat(s1).concat(Api_s.pagefraglist2).concat(s2);
//        viewproducturl=viewproducturl.replace(" ","%20");
//        sr = new StringRequest(Request.Method.POST, viewproducturl, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e("Sucess", "" + response);
//                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
//
//                GsonBuilder gsonBuilder = new GsonBuilder();
//                final Gson gson = gsonBuilder.create();
//
//                module_id.clear();
//                moduletilte.clear();
//                moduledate.clear();
//                moduledescp.clear();
//                moduleimg.clear();
//                moduleinnertitle.clear();
//
//                Specificfraglistsettergetter received2 = new Specificfraglistsettergetter();
//                received2 = gson.fromJson(response, Specificfraglistsettergetter.class);
//
//
//                data_list1 = received2.innerpagefraglist;
//                for (int i = 0; i < data_list1.size(); i++) {
//                    module_id.add(data_list1.get(i).module_id);
//                    moduletilte.add(data_list1.get(i).module_name);
//                    moduledate.add(data_list1.get(i).module_date);
//                    moduledescp.add(data_list1.get(i).module_desc_new);
//                    moduleimg.add(data_list1.get(i).module_img);
//                    moduleinnertitle.add(data_list1.get(i).module_excerpt);
//
//
//
//
//                }
//
//                lv.setAdapter(new NewsAdapter(getActivity(), moduletilte, moduledate,moduledescp,moduleimg));
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //pDialog.dismiss();
//                Log.e("Sucess", "" + error.toString());
//                Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/x-www-form-urlencoded");
//                return params;
//            }
//        };
//        sr.setRetryPolicy(new DefaultRetryPolicy(
//                30000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        queue.add(sr);
//    }
//}
