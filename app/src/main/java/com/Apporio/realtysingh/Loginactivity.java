package com.Apporio.realtysingh;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.Apporio.realtysingh.R;
import com.Apporio.realtysingh.parsing_files.parsingforlogin;

public class Loginactivity extends AppCompatActivity {
    public static View v11,v2,v3,v41;
    public static EditText email,zip;
    public static TextView next,forgot;
    public  static ProgressBar pb;
    public  static Loginactivity loginactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        next = (TextView)findViewById(R.id.login34);
        forgot = (TextView)findViewById(R.id.textView2);
        email = (EditText)findViewById(R.id.username);
        pb=(ProgressBar)findViewById(R.id.pb);
        zip = (EditText)findViewById(R.id.zpc);
        v11 = (View)findViewById(R.id.view11);
        v2 = (View)findViewById(R.id.view12);
        v3 = (View)findViewById(R.id.view21);
        v41 = (View)findViewById(R.id.view22);
        loginactivity=Loginactivity.this;
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==true){
                    v11.setVisibility(View.GONE);
                    v2.setVisibility(View.VISIBLE);
                }
                else{
                    v11.setVisibility(View.VISIBLE);
                    v2.setVisibility(View.GONE);
                }
            }
        });
        zip.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    v3.setVisibility(View.GONE);
                    v41.setVisibility(View.VISIBLE);
                } else {
                    v3.setVisibility(View.VISIBLE);
                    v41.setVisibility(View.GONE);
                }
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(getApplicationContext(), "Signing Up...", Toast.LENGTH_SHORT).show();


                        Intent in = new Intent(Loginactivity.this, ForgotActivity.class);
                        startActivity(in);





            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = Loginactivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)Loginactivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                parsingforlogin.parsing(Loginactivity.this,email.getText().toString().trim(),zip.getText().toString().trim());
//                Toast.makeText(getApplicationContext(), "Login Successfully...", Toast.LENGTH_SHORT).show();
//                Handler handler1 = new Handler();
//
//                handler1.postDelayed(new Runnable() {
//
//                    @Override
//                    public void run()
//
//                    {
//                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//                        SharedPreferences.Editor edit2 = prefs.edit();
//
//                        edit2.putBoolean("pref_previously_started", Boolean.TRUE);
//                        edit2.commit();
//                        Intent in = new Intent(Loginactivity.this, MainActivity.class);
//                        startActivity(in);
//                        Loginactivity.this.finish();
//                        Logregactivity.logct.finish();
//
//                    }
//                }, 1000);

            }
        });
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Loginactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Loginactivity.this.getResources().getColor(R.color.red));
        } else {
            Window window = Loginactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        email.postDelayed(
                new Runnable() {
                    public void run() {
                        email.requestFocus();
                        InputMethodManager inputMethodManager =  (InputMethodManager)Loginactivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.showSoftInput(email,InputMethodManager.SHOW_IMPLICIT);
                    }
                },100);
        super.onResume();
    }
}
