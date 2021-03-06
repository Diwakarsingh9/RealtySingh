package com.Apporio.realtysingh.parsing_files;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.Apporio.realtysingh.settergetter.Innerstate;
import com.Apporio.realtysingh.settergetter.Statesettergetter;
import com.Apporio.realtysingh.singleton.VolleySingleton;
import com.Apporio.realtysingh.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 11/3/2015.
 */
public class parsingforstatenames {
    public static RequestQueue queue;
    public static StringRequest sr1,sr2;
    public static List<Innerstate> data_list1;
    public static ArrayList<String> sid = new ArrayList<String>();
    public static ArrayList<String> sname = new ArrayList<String>();


    public static void parsing(final Context activity){



        String locationurl2 = Api_s.statenames;
        Log.e("url", "" + locationurl2);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                sid.clear();
                sname.clear();



                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Statesettergetter received2 = new Statesettergetter();
                    received2 = gson.fromJson(response, Statesettergetter.class);

                    if(received2.result.equals("1")) {
                        data_list1 = received2.innerstate;
                        for (int i = 0; i < data_list1.size(); i++) {
                            sid.add(data_list1.get(i).s_id);
                            sname.add(data_list1.get(i).s_name);
                        }
                        parsingforpages.parsing(activity);

                    }

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



    }
}
