package com.Apporio.realtysingh.parsing_files;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.Apporio.realtysingh.ForgotActivity;
import com.Apporio.realtysingh.Helpfragment;
import com.Apporio.realtysingh.Signupactivity;
import com.Apporio.realtysingh.settergetter.forgotpwsettergetter;
import com.Apporio.realtysingh.settergetter.settergetterfeedback;
import com.Apporio.realtysingh.singleton.VolleySingleton;
import com.Apporio.realtysingh.urlapi.Api_s;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Created by saifi45 on 12/28/2015.
 */
public class parsingforfeedback {

    public static RequestQueue queue;
    public static StringRequest sr1,sr2;


    public static void parsing(final Context activity,String s1,String s2,String s3,String s4,String s5){



        String locationurl2 = Api_s.feedback.concat(s1).concat(Api_s.feedback0).concat(s5).concat(Api_s.feedback1).concat(s2).concat(Api_s.feedback2).concat(s3).concat(Api_s.feedback3).concat(s4);
        locationurl2=locationurl2.replace(" ","%20");

        Log.e("url", "" + locationurl2);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Helpfragment.pb.setVisibility(View.GONE);



                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    settergetterfeedback received2 = new settergetterfeedback();
                    received2 = gson.fromJson(response, settergetterfeedback.class);

                    String result1= received2.result;
                    Log.e("resss", "" + result1);

                    String msg1= received2.msg;


                        Toast.makeText(activity, "" + msg1, Toast.LENGTH_SHORT).show();




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
        Helpfragment.pb.setVisibility(View.VISIBLE);


    }
}
