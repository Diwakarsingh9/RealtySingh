package com.Apporio.realtysingh.parsing_files;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.Apporio.realtysingh.Videoactivity;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.Apporio.realtysingh.ListInneractivity;
import com.Apporio.realtysingh.R;
import com.Apporio.realtysingh.settergetter.innerrecent;
import com.Apporio.realtysingh.settergetter.recentsettergetter;
import com.Apporio.realtysingh.settergetter.recentsettergetter2;
import com.Apporio.realtysingh.singleton.VolleySingleton;
import com.Apporio.realtysingh.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 11/18/2015.
 */
public class parsingforrecent {

    public static RequestQueue queue;
    public static StringRequest sr1,sr2;
    public static List<innerrecent> data_list1;
    public static ArrayList<String> module_id = new ArrayList<String>();
    public static ArrayList<String> moduletilte = new ArrayList<String>();
    public static ArrayList<String> moduledate = new ArrayList<String>();
    public static ArrayList<String> moduledescp = new ArrayList<String>();
    public static ArrayList<String> moduleimg = new ArrayList<String>();
    public static ArrayList<String> moduleinnertitle = new ArrayList<String>();
    public static ArrayList<String> moduleet = new ArrayList<String>();
    public static ArrayList<String> modulesrc = new ArrayList<String>();
    public static Context ctc;
    public static String s22;



    public static void parsing(final Context activity,String s1, String s2) {

            ctc = activity;
        s22=s2;
        queue = VolleySingleton.getInstance(activity).getRequestQueue();


            String locationurl2 = Api_s.recentnews.concat(s1).concat(Api_s.recentnews2).concat(s2);
            locationurl2 = locationurl2.replace(" ", "%20");
            locationurl2 = locationurl2.replace("VIDEOS","VIDEO");
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
                    ListInneractivity.pb.setVisibility(View.GONE);
                    ListInneractivity.llforlist.setVisibility(View.VISIBLE);

                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        recentsettergetter2 received2 = new recentsettergetter2();
                        received2 = gson.fromJson(response, recentsettergetter2.class);

                        String result = received2.result;
                        if(result.equals("0")){
                        ListInneractivity.relatedsearch.setVisibility(View.GONE);
                            ListInneractivity.v111.setVisibility(View.GONE);
                        }
                        else {

                            recentsettergetter received = new recentsettergetter();
                            received = gson.fromJson(response, recentsettergetter.class);
                            data_list1 = received.innerrecents;
                            for (int i = 0; i < data_list1.size(); i++) {
                                module_id.add(data_list1.get(i).module_id);
                                moduletilte.add(data_list1.get(i).module_name);
                                moduledate.add(data_list1.get(i).module_date);
                                moduledescp.add(data_list1.get(i).module_desc_new);
                                moduleimg.add(data_list1.get(i).module_img);
                                moduleet.add(data_list1.get(i).module_state);
                                modulesrc.add(data_list1.get(i).module_source);
                                moduleinnertitle.add(data_list1.get(i).module_excerpt);
                            }

                            //Log.e("title", SplashActivity.moduletilte + "");
                            //Toast.makeText(activity, ""+Mainfragment.viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < 3; i++) {
                                if (data_list1.size() > 3) {
                                    ListInneractivity.relatedsearch.setVisibility(View.VISIBLE);
                                    ListInneractivity.v111.setVisibility(View.VISIBLE);
                                    ListInneractivity.llforrecnt.setVisibility(View.VISIBLE);
                                    ListInneractivity.llforlist.addView(ordersview(R.layout.itemforrecent, i));
                                }
                                else {
                                    ListInneractivity.llforrecnt.setVisibility(View.GONE);
                                    ListInneractivity.relatedsearch.setVisibility(View.GONE);
                                    ListInneractivity.v111.setVisibility(View.GONE);
                                }
                            }
                        }
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
            ListInneractivity.pb.setVisibility(View.VISIBLE);
            ListInneractivity.llforlist.setVisibility(View.GONE);

        }

    public static View ordersview(int layout_name, int i) {

        LayoutInflater layoutInflater =
                (LayoutInflater)ctc.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);
        final TextView title11 = (TextView)addView.findViewById(R.id.title2234);
        final  TextView date = (TextView)addView.findViewById(R.id.date);
        final  TextView city = (TextView)addView.findViewById(R.id.city);
        final   TextView source1 = (TextView)addView.findViewById(R.id.srce);
        final  TextView descp = (TextView)addView.findViewById(R.id.descp);
        final  ImageView img = (ImageView)addView.findViewById(R.id.img);
        addView.setTag(i);
    addView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         int position = (int) addView.getTag();
            Intent in = new Intent(ctc,ListInneractivity.class);
            in.putExtra("title", s22);
            in.putExtra("head", moduleinnertitle.get(position));
            in.putExtra("date", moduledate.get(position));
            in.putExtra("descp", moduledescp.get(position));
            in.putExtra("img", moduleimg.get(position));
            in.putExtra("src", modulesrc.get(position));
            in.putExtra("plc", moduleet.get(position));
            in.putExtra("id", module_id.get(position));
           // Toast.makeText(ctc, ""+s22+module_id.get(position), Toast.LENGTH_SHORT).show();
            ctc.startActivity(in);
            ListInneractivity.lis.finish();

        }
    });
        title11.setText(""+ Html.fromHtml("" + moduletilte.get(i)).toString());
        date.setText(""+moduledate.get(i));
       descp.setText(""+Html.fromHtml("" + moduledescp.get(i)).toString());
       city.setText(""+moduleet.get(i));
        source1.setText(""+modulesrc.get(i));
        //il.DisplayImage("http://keshavgoyal.com/realtysingh/" + img.get(i), viewHolder.img);
        Picasso.with(ctc)
                .load("http://keshavgoyal.com/realtysingh/" + moduleimg.get(i))
                .placeholder(R.drawable.stub) // optional
                .error(R.drawable.stub)         // optional
                .into(img);

        return addView ;
    }
    }


