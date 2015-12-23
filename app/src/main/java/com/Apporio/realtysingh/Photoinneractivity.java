package com.Apporio.realtysingh;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.Apporio.realtysingh.adapter.MyAdapter1;
import com.Apporio.realtysingh.R;

import java.util.ArrayList;

public class Photoinneractivity extends FragmentActivity {
    ImageView img,back,delete;
    public static Photoinneractivity php2;
    int position2;
    String  caption,data;
    int seconds;
    ViewPager viewPager;
    ArrayList<String> arrayB;
    ArrayList<String> arrayc;
    String  []arrayd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoinneractivity);
        php2=this;
        back= (ImageView)findViewById(R.id.close);

        Bundle bundle2= getIntent().getExtras();
        int position1= bundle2.getInt("position", 0);
        arrayB = bundle2.getStringArrayList("imagesdata");
        arrayc = bundle2.getStringArrayList("imagescap");
       // arrayd = bundle2.getStringArray("imagesid");
        viewPager=(ViewPager)findViewById(R.id.view);
        viewPager.setAdapter(new MyAdapter1(getSupportFragmentManager(), position1, arrayB, arrayc));
        viewPager.setCurrentItem(position1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Photoinneractivity.this.finish();
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Photoinneractivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Photoinneractivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = Photoinneractivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
