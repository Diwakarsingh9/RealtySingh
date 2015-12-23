package com.Apporio.realtysingh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.Apporio.realtysingh.adapter.NewsAdapter;
import com.Apporio.realtysingh.parsing_files.parsingforpages;
import com.Apporio.realtysingh.settergetter.Innerpagefraglist;
import com.Apporio.realtysingh.settergetter.Specificfraglistsettergetter;
import com.Apporio.realtysingh.singleton.VolleySingleton;
import com.Apporio.realtysingh.urlapi.Api_s;
import com.Apporio.realtysingh.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by neokree on 16/12/14.
 */
public class basefragex extends Fragment {

    public static String s1="";
    public  static  int i=0;
    public  static  int y;
    public  static String d[]={"1","2","3","4","5"};
    public  static NewsAdapter adp;
    public  static ListView lv;
    int pos;
    public static ProgressBar pb;
    public static List<Innerpagefraglist> data_list1;
    public static ArrayList<String> module_id = new ArrayList<String>();
    public static ArrayList<String> moduletilte = new ArrayList<String>();
    public static ArrayList<String> moduledate = new ArrayList<String>();
    public static ArrayList<String> moduledescp = new ArrayList<String>();
    public static ArrayList<String> moduleimg = new ArrayList<String>();
    public static ArrayList<String> moduleinnertitle = new ArrayList<String>();



    StringRequest sr;
    RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_blank, null);
        lv=(ListView)root.findViewById(R.id.listView22);
        pb=(ProgressBar)root.findViewById(R.id.pb);
        pos= getArguments().getInt("msg22");
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();





        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(getActivity(), ""+pos, Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getActivity(), ListInneractivity.class);
                in.putExtra("title", parsingforpages.pname.get(pos));
                in.putExtra("head", moduleinnertitle.get(position));
                in.putExtra("date", moduledate.get(position));
                in.putExtra("descp", moduledescp.get(position));
                in.putExtra("img", moduleimg.get(position));
                startActivity(in);
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewproduct();

    }

    //    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            try {
//                viewproduct();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
////            Toast.makeText(getActivity(), "gggggggggg ", Toast.LENGTH_SHORT).show();
//        }
//    }
    public static basefragex newInstance(int pos,Context ctc) {

        basefragex f = new basefragex();
        Bundle b = new Bundle();
        b.putInt("msg22", pos);
        Toast.makeText(ctc, ""+pos, Toast.LENGTH_SHORT).show();
        f.setArguments(b);

        return f;
    }

    public void viewproduct() {


        String locationurl2 = Api_s.pagefraglist.concat(s1).concat(Api_s.pagefraglist2).concat(parsingforpages.pname.get(getArguments().getInt("msg22")));
        locationurl2=locationurl2.replace(" ","%20");
        Log.e("url", "" + locationurl2);
        sr = new StringRequest(Request.Method.POST, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                module_id.clear();
                moduletilte.clear();
                moduledate.clear();
                moduledescp.clear();
                moduleimg.clear();
                moduleinnertitle.clear();


                GsonBuilder gsonBuilder = new GsonBuilder();
                final Gson gson = gsonBuilder.create();
                Specificfraglistsettergetter received2 = new Specificfraglistsettergetter();
                received2 = gson.fromJson(response, Specificfraglistsettergetter.class);


                data_list1=received2.innerpagefraglist;
                for(int i=0;i<data_list1.size();i++){
                    module_id.add(data_list1.get(i).module_id);
                    moduletilte.add(data_list1.get(i).module_name);
                    moduledate.add(data_list1.get(i).module_date);
                    moduledescp.add(data_list1.get(i).module_desc_new);
                    moduleimg.add(data_list1.get(i).module_img);
                    moduleinnertitle.add(data_list1.get(i).module_excerpt);
                }

                Log.e("title",moduletilte+"");



               // adp = new NewsAdapter(getActivity(), moduletilte, moduledate, moduletilte,moduledate,moduledescp,moduleimg, title1, image1);
          //      adp.notifyDataSetChanged();
                lv.setAdapter(adp);


//                        Newsfragment.lv.setFocusable(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
                Log.e("Sucess", "" + error.toString());
                Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();

            }
        }) {

        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(sr);
    }
}
