package com.Apporio.realtysingh.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import com.Apporio.realtysingh.Bankingfragment;
import com.Apporio.realtysingh.Horizontal_ListView.HorizontalListView;
import com.Apporio.realtysingh.ListInneractivity;
import com.Apporio.realtysingh.R;
import com.squareup.picasso.Picasso;


public class Bankingadapter extends BaseAdapter {
    public  static ArrayList<String> title11;
    public  static ArrayList<String> date;
    public  static ArrayList<String> dscvp;
    public  static ArrayList<String> source2;
    public  static ArrayList<String> city2;
    public  static ArrayList<String> img;
    public  static ArrayList<String> src_link;
    LayoutInflater inflater;
    static Context ctc2;
    String profilename;

    static int[] c=new int []{R.drawable.flip2,R.drawable.bb,R.drawable.dd,
            R.drawable.bgg,R.drawable.logo,R.drawable.flip1,
            R.drawable.bgg,R.drawable.flip1,R.drawable.reall2,R.drawable.flip2,R.drawable.flip3,};




    public Bankingadapter(Context activity, ArrayList<String> stringArrayList, ArrayList<String> strings, ArrayList<String> moduletilte, ArrayList<String> moduledate, ArrayList<String> moduledescp, ArrayList<String> moduleimg, ArrayList<String> modulesrc_link) {

        Log.e("ffff", "adapter method chla");
        this.ctc2=activity;

        title11=moduletilte;
        date=moduledate;
        dscvp=moduledescp;
        img=moduleimg;
        source2=stringArrayList;
        city2=strings;
        this.src_link = modulesrc_link;
        inflater = LayoutInflater.from(this.ctc2);
    }



    @Override
    public int getCount() {
        return title11.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class Holder{

        TextView title11,date,city,source1;
        public     ImageView img,minus;
        public     TextView descp;
        public TextSwitcher mTitle;
        public LinearLayout llforlist;
        public HorizontalListView hlv;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;


        convertView = inflater.inflate(R.layout.recyclerview_item2, null);
        holder = new Holder();
        convertView.setTag(holder);
        holder.title11 = (TextView) convertView.findViewById(R.id.title2234);
        holder.date = (TextView) convertView.findViewById(R.id.date);
        holder.city = (TextView) convertView.findViewById(R.id.city);
        holder.source1 = (TextView) convertView.findViewById(R.id.srce);
        holder.descp = (TextView) convertView.findViewById(R.id.descp);
        holder.img = (ImageView) convertView.findViewById(R.id.img);


        holder.title11.setText("" + Html.fromHtml("" + title11.get(position)).toString());
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
        String inputDateStr=""+date.get(position);
        Date dateaa = null;
        try {
            dateaa = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(dateaa);
        holder. date.setText("" + outputDateStr);
        holder.descp.setText("" + Html.fromHtml("" + dscvp.get(position)).toString());
        holder.city.setText("" + source2.get(position));
        holder.source1.setText("" + city2.get(position));
        //il.DisplayImage("http://keshavgoyal.com/realtysingh/" + img.get(i), viewHolder.img);
        Picasso.with(ctc2)
                .load("http://meetsingh.com/" + img.get(position))
                .placeholder(R.drawable.stub) // optional
                .error(R.drawable.stub)         // optional
                .into(holder.img);
        holder.img.setTag(position);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) holder.img.getTag();
                Intent in = new Intent(ctc2, ListInneractivity.class);

                in.putExtra("title", "BANKING");
                in.putExtra("head", title11.get(pos));
                in.putExtra("date", date.get(pos));
                in.putExtra("descp", dscvp.get(pos));
                in.putExtra("img", img.get(pos));
                in.putExtra("src", source2.get(pos));
                in.putExtra("plc", city2.get(pos));
                in.putExtra("src_link",src_link.get(pos));
                in.putExtra("id", Bankingfragment.module_id.get(pos));
                ctc2.startActivity(in);


            }
        });


        return convertView;
        }




    public static View ordersview(int layout_name, int i) {

        LayoutInflater layoutInflater =
                (LayoutInflater)ctc2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);

        final  ImageView img = (ImageView)addView.findViewById(R.id.imagess);
        img.setImageResource(c[i]);

        return addView ;
    }
}










