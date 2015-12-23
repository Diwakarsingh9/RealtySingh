package com.Apporio.realtysingh;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.Apporio.realtysingh.R;

public class Logregactivity extends Activity {
    public static TextView logintry,login,signup,register,passalert;
    public static ImageView back;
    public static Logregactivity logct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logregactivity);
        logct=Logregactivity.this;
        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper1);
        logintry = (TextView) findViewById(R.id.login);
        signup = (TextView) findViewById(R.id.signup);
        flipper.startFlipping();
        logintry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler11 = new Handler();
                handler11.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {
                        // Toast.makeText(Logregactivity.this, "login", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(Logregactivity.this,Loginactivity.class);
                        startActivity(in);
                        //finish();

                    }
                }, 1000);


            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler12 = new Handler();
                handler12.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {
                        //Toast.makeText(Logregactivity.this, "signup", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(Logregactivity.this,Signupactivity.class);
                        startActivity(in);

                    }
                }, 1000);

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Logregactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Logregactivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = Logregactivity.this.getWindow();
            //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
