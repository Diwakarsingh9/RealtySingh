package com.Apporio.realtysingh.parsing_files;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.Apporio.realtysingh.Loginactivity;
import com.Apporio.realtysingh.settergetter.Loginsettergetter;
import com.Apporio.realtysingh.settergetter.logginsettergetter2;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.Apporio.realtysingh.Logregactivity;
import com.Apporio.realtysingh.MainActivity;
import com.Apporio.realtysingh.singleton.VolleySingleton;
import com.Apporio.realtysingh.Signupactivity;
import com.Apporio.realtysingh.settergetter.Signupsettergetter;
import com.Apporio.realtysingh.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Created by saifi45 on 11/3/2015.
 */
public class parsingforsignup {

    public static RequestQueue queue;
    public static StringRequest sr1,sr2;


    public static void parsing(final Context activity, final String s1, final String s2,  String s3,  String s4,  String s5,  String s6){



        String locationurl2 = Api_s.Signup.concat(s1).concat(Api_s.Signup2).concat(s2).concat(Api_s.Signup3).concat(s3).concat(Api_s.Signup4).concat(s4).
                concat("&business_name=").concat(s5).concat("&occupation=").concat(s6);
        locationurl2=locationurl2.replace(" ","%20");

        Log.e("url", "" + locationurl2);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Signupactivity.pb.setVisibility(View.GONE);



                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Loginsettergetter received2 = new Loginsettergetter();
                    received2 = gson.fromJson(response, Loginsettergetter.class);

                    String result1= received2.result;
                    Log.e("resss", "" + result1);

                    String msg1= received2.msg;
                    Toast.makeText(activity, "" + msg1, Toast.LENGTH_SHORT).show();
                    if(result1.equals("1")){
                        Signupsettergetter received3 = new Signupsettergetter();
                        received3 = gson.fromJson(response, Signupsettergetter.class);
                        String name11 =received3.innerlogin.name;
                        String emailid = received3.innerlogin.email;
                        String userid = received3.innerlogin.user_id;

                        //Toast.makeText(activity, ""+name11, Toast.LENGTH_SHORT).show();
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
                        SharedPreferences.Editor edit2 = prefs.edit();

                        edit2.putBoolean("pref_previously_started", Boolean.TRUE);
                        edit2.putString("Username", "" +name11);
                        edit2.putString("email", "" + emailid);
                        edit2.putString("userid", "" + userid);


                        edit2.commit();
                        Intent in = new Intent(activity, MainActivity.class);
                        activity.startActivity(in);
                        Signupactivity.signupactivity.finish();
                        Logregactivity.logct.finish();



                    }


                    //////////////////////////////



                  //  Toast.makeText(activity, "" + msg1, Toast.LENGTH_SHORT).show();
//                    if(result1.equals("1")){
//
//                                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
//                                SharedPreferences.Editor edit2 = prefs.edit();
//
//                                edit2.putBoolean("pref_previously_started", Boolean.TRUE);
//                        edit2.putString("Username", "" + s1);
//                        edit2.putString("email", "" + s2);
//                       // String userid = received2.innerlogin.user_id;
//
//
//                                edit2.commit();
//                                Intent in = new Intent(activity, MainActivity.class);
//                                activity.startActivity(in);
//                                Signupactivity.signupactivity.finish();
//                                Logregactivity.logct.finish();
//
//
//
//                    }




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
        Signupactivity.pb.setVisibility(View.VISIBLE);


    }
}
