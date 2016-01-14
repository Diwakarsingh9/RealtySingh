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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.Apporio.realtysingh.networkchecker.NetworkChecker;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.Apporio.realtysingh.parsing_files.parsingforpages;
import com.Apporio.realtysingh.R;
import com.Apporio.realtysingh.parsing_files.parsingforstatenames;

import java.util.ArrayList;

import views.ProgressWheel;

public class SplashActivity extends Activity {
    LinearLayout background;
    boolean previouslyStarted;
    LinearLayout rel;
    ImageView img,img2;
    public static int y2=0;
    public static int y=0;
    public  static String s1="";
    public static SplashActivity spl;

    public static ArrayList<String> module_id = new ArrayList<String>();
    public static ArrayList<String> moduletilte = new ArrayList<String>();
    public static ArrayList<String> moduledate = new ArrayList<String>();
    public static ArrayList<String> moduledescp = new ArrayList<String>();
    public static ArrayList<String> moduleimg = new ArrayList<String>();
    public static ArrayList<String> moduleinnertitle = new ArrayList<String>();

    TextView nointernet  ,loadingtext;
    StringBuilder mSB;
    String result;
    Button tryagain ;
    public static ProgressWheel pb ;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        spl= SplashActivity.this;
        nointernet = (TextView) findViewById(R.id.nointernet);
        tryagain = (Button) findViewById(R.id.tryagain);
        pb = (ProgressWheel) findViewById(R.id.progressBarinsplash);
        loadingtext = (TextView) findViewById(R.id.loadintextinsplash);
        nointernet.setVisibility(View.GONE);
        tryagain.setVisibility(View.GONE);
        pb.setVisibility(View.GONE);
        loadingtext.setVisibility(View.GONE);

        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startThread();
            }
        });
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
                startThread();
                pb.setVisibility(View.VISIBLE);

            }
        }, 4200);
    }
    private void startThread() {
        pb.setVisibility(View.VISIBLE);
        loadingtext.setVisibility(View.VISIBLE);
        nointernet.setVisibility(View.GONE);
        tryagain.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getTheGPSBROO();
            }
        }, SPLASH_TIME_OUT);
    }


    private void getTheGPSBROO() {
        if(new NetworkChecker().isNetworkConnected(SplashActivity.this)){
            nointernet.setVisibility(View.GONE);
            tryagain.setVisibility(View.GONE);
            parsingforstatenames.parsing(SplashActivity.this);


        }else {
            pb.setVisibility(View.GONE);
            loadingtext.setVisibility(View.GONE);
            nointernet.setVisibility(View.VISIBLE);
            tryagain.setVisibility(View.VISIBLE);

        }
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
