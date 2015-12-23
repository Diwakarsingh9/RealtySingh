package com.Apporio.realtysingh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Apporio.realtysingh.R;

import java.util.List;



public class Myadaptericon extends BaseAdapter {

    Context ctc;
    LayoutInflater inflater;
    List<String> ad;
    String[] ad2;
    String[] ad3;
    int c[]=new int []{R.drawable.flip1,R.drawable.flip2,R.drawable.flip3,
            R.drawable.bgg,R.drawable.logo,R.drawable.flip1,
            R.drawable.bgg,R.drawable.flip1,R.drawable.reall2,R.drawable.flip2,R.drawable.flip3,};


    public Myadaptericon(Context context) {
        ctc=context;
       // ad=strings;

        inflater = LayoutInflater.from(this.ctc);
         // il=new ImageLoader(ctc.getApplicationContext());
    }

    @Override
    public int getCount() {
        return 18;
    }

    @Override
    public Object getItem(int position) {
        //Log.e("position", "Dish" + position);
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class Holder{

        TextView tv1,tv2;
        ImageView f1;
        View v1;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.item_coverflow, null);
            holder = new Holder();
            convertView.setTag(holder);
        }
        else {
            holder = (Holder)convertView.getTag();
        }

        Toast.makeText(ctc, "adap", Toast.LENGTH_SHORT).show();

        holder.f1 = (ImageView)convertView.findViewById(R.id.imagess);
        holder.tv1 = (TextView)convertView.findViewById(R.id.text33);
       holder.f1.setImageResource(c[position]);
        holder.tv1.setText("News "+ position);
        return convertView;
    }
}