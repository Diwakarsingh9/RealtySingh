package com.Apporio.realtysingh;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.Apporio.realtysingh.parsing_files.parsingforpages;
import com.Apporio.realtysingh.R;
import com.Apporio.realtysingh.parsing_files.parsingforstatenames;

import java.util.ArrayList;

public class SplashActivity extends Activity {
    LinearLayout background;
    boolean previouslyStarted;
    LinearLayout rel;
    ImageView img,img2;
    public static int y2=0;
    public static int y=0;
    public  static String s1="";
    public static SplashActivity spl;
    public static ProgressBar pb;
    public static ArrayList<String> module_id = new ArrayList<String>();
    public static ArrayList<String> moduletilte = new ArrayList<String>();
    public static ArrayList<String> moduledate = new ArrayList<String>();
    public static ArrayList<String> moduledescp = new ArrayList<String>();
    public static ArrayList<String> moduleimg = new ArrayList<String>();
    public static ArrayList<String> moduleinnertitle = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        spl= SplashActivity.this;
        pb =(ProgressBar)findViewById(R.id.pb);
        parsingforstatenames.parsing(SplashActivity.this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        previouslyStarted = prefs.getBoolean("pref_previously_started", false);
        ScreenResolution screenRes = deviceDimensions();

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {

            @Override
            public void run()

            {
                YoYo.with(Techniques.SlideInLeft)
                        .duration(3000)
                        .playOn(findViewById(R.id.title22));

                YoYo.with(Techniques.SlideInLeft)
                        .duration(3000)
                        .playOn(findViewById(R.id.title2233));
                YoYo.with(Techniques.SlideInRight)
                        .duration(3000)
                        .playOn(findViewById(R.id.subtitle));





            }
        }, 50);




        Handler handler2 = new Handler();

        handler2.postDelayed(new Runnable() {

            @Override
            public void run()

            {
                pb.setVisibility(View.VISIBLE);

            }
        }, 4200);
    }

    private class ScreenResolution {
        int width, height;
        public ScreenResolution(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    ScreenResolution deviceDimensions() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // getsize() is available from API 13
        if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            return new ScreenResolution(size.x, size.y);
        } else {
            Display display = getWindowManager().getDefaultDisplay();
            // getWidth() & getHeight() are depricated
            return new ScreenResolution(display.getWidth(), display.getHeight());
        }
    }

}
