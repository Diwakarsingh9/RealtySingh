package com.Apporio.realtysingh.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Apporio.realtysingh.R;
import com.squareup.picasso.Picasso;


/**
 * Created by saifi45 on 8/9/2015.
 */

    public class Basefragmentforphotoopen extends Fragment {
        static String ds2;
        public  static  int y;
    TextView title, copy;

public  static ImageView img;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layoutforphotoopen, null);

           img = (ImageView)root.findViewById(R.id.imageView);
            title= (TextView)root.findViewById(R.id.caption);
            String d= getArguments().getString("msg");
            String de= getArguments().getString("msg2");

            Log.e("endpic", "" + d);
            title.setText(de);
            Picasso.with(getActivity())
                    .load("http://meetsingh.com/" + d)
                    .placeholder(R.drawable.reall2) // optional
                    .error(R.drawable.reaal2222l)         // optional
                    .into(img);


            return root;
        }


        public static Basefragmentforphotoopen newInstance(String dev1, String s) {
            Basefragmentforphotoopen f = new Basefragmentforphotoopen();
            Bundle b = new Bundle();
            b.putString("msg", (dev1));
            b.putString("msg2", String.valueOf(s));
            // y=ber;
            f.setArguments(b);

            return f;
        }
    }

