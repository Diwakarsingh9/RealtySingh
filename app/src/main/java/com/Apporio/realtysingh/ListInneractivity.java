package com.Apporio.realtysingh;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.Apporio.realtysingh.imageloading.ImageLoader;
import com.Apporio.realtysingh.parsing_files.parsingforrecent;
import com.Apporio.realtysingh.R;

public class ListInneractivity  extends Activity  {
   public static TextView headl,date11,title,descp,src,plc,relatedsearch;
    public static ImageView back,dp;
    public static ImageLoader il;
    public static View v111;
    public static LinearLayout llforlist,llforrecnt;
    public static ProgressBar pb;
    public static ListInneractivity lis;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_inneractivity);
        lis = ListInneractivity.this;

        title= (TextView) findViewById(R.id.titlehead);
        back= (ImageView) findViewById(R.id.back);
        headl= (TextView) findViewById(R.id.titleddd);
        pb= (ProgressBar)findViewById(R.id.progressBar);
        date11= (TextView) findViewById(R.id.date);
        v111= (View) findViewById(R.id.vew);
        relatedsearch= (TextView) findViewById(R.id.related);
        llforlist= (LinearLayout) findViewById(R.id.listforrelated);
        llforrecnt= (LinearLayout) findViewById(R.id.listforrelated);
        src= (TextView) findViewById(R.id.src11);
        plc= (TextView) findViewById(R.id.plc);
        descp= (TextView) findViewById(R.id.descp);
        dp= (ImageView) findViewById(R.id.logo);
        llforlist.removeAllViews();
        ListInneractivity.relatedsearch.setVisibility(View.GONE);
        ListInneractivity.v111.setVisibility(View.GONE);
        il=new ImageLoader(ListInneractivity.this);
        Bundle bundle = getIntent().getExtras();
        String s= bundle.getString("title", null);
        String head= bundle.getString("head", null);
        String date1= bundle.getString("date", null);
        String dsecp= bundle.getString("descp", null);
        String img= bundle.getString("img", null);
        String id1= bundle.getString("id", null);
         url= bundle.getString("src_link", null);
        relatedsearch.setText("Related " + s);

        parsingforrecent.parsing(ListInneractivity.this, id1, s);

        il.DisplayImage("http://keshavgoyal.com/realtysingh/" + img, dp);
        title.setText("" + s.toUpperCase());
        src.setText("" + getIntent().getExtras().getString("src"));
        plc.setText("" +  getIntent().getExtras().getString("plc"));
        headl.setText("" + Html.fromHtml(""+head).toString());
      String des =  Html.fromHtml(""+dsecp).toString();
        descp.setText(des);
        date11.setText("" + date1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        plc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(""+url));
                    startActivity(browserIntent);
                }
            }
        });
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = ListInneractivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ListInneractivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = ListInneractivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


}
