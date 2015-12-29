package com.Apporio.realtysingh.parsing_files;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.Apporio.realtysingh.ApplicationConstants;
import com.Apporio.realtysingh.settergetter.settergetterpush;
import com.Apporio.realtysingh.singleton.VolleySingleton;
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
 * Created by saifi45 on 10/14/2015.
 */
public class parsingfornotification {

    public static RequestQueue queue;
    public static StringRequest sr1,sr2;


    public static void parsing(final Context activity, String s1, String s2){


        String locationurl2 = ApplicationConstants.APP_SERVER_URL+s1+"&d_id="+s2+"&flag=2";
       // String locationurl2 = "http://apporio.in/gcm_demo/send_message.php?regId="+s2+"&message=test";
        locationurl2=locationurl2.replace(" ", "%20");

        Log.e("url", "" + locationurl2);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    settergetterpush received2 = new settergetterpush();
                    received2 = gson.fromJson(response, settergetterpush.class);

                    String result11= received2.response;
                    String result21= received2.message;
                    if(result11.equals("1")){
                        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(activity);
                        SharedPreferences.Editor edit2 = prefs2.edit();
                        edit2.putBoolean("pref_previously_started22", Boolean.TRUE);
                        edit2.commit();
                    }
                  //  Toast.makeText(activity, "" + result21, Toast.LENGTH_SHORT).show();




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
