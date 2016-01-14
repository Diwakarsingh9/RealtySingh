package com.Apporio.realtysingh.parsing_files;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.Apporio.realtysingh.ForgotActivity;
import com.Apporio.realtysingh.Logregactivity;
import com.Apporio.realtysingh.MainActivity;
import com.Apporio.realtysingh.SplashActivity;
import com.Apporio.realtysingh.settergetter.forgotpwsettergetter;
import com.Apporio.realtysingh.settergetter.settergetterinvitefriends;
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
public class parsingforinvitefriends {

    public static RequestQueue queue;
    public static StringRequest sr1,sr2;
    public static String msg1;
    public static boolean previouslyStarted;
    public static void parsing(final Context activity){
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        previouslyStarted = prefs.getBoolean("pref_previously_started", false);

        String locationurl2 = Api_s.invitefriends;
        locationurl2=locationurl2.replace(" ","%20");

        Log.e("url", "" + locationurl2);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {




                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    settergetterinvitefriends received2 = new settergetterinvitefriends();
                    received2 = gson.fromJson(response, settergetterinvitefriends.class);

                    String result1= received2.result;
                    Log.e("resss", "" + result1);

                     msg1= received2.url;

                    //Toast.makeText(activity, "" + msg1, Toast.LENGTH_SHORT).show();
                    if(result1.equals("1")) {
                        SplashActivity.pb.setVisibility(View.GONE);
                        if (!previouslyStarted) {
                            Intent in = new Intent(activity, Logregactivity.class);
                            activity.startActivity(in);
                            SplashActivity.spl.finish();

                        } else {
                            Intent in = new Intent(activity, MainActivity.class);
                            activity.startActivity(in);
                            SplashActivity.spl.finish();
                        }
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
