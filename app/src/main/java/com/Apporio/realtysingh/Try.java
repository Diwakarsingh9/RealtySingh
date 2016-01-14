package com.Apporio.realtysingh;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import com.Apporio.realtysingh.adapter.Videoadapter;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

import views.ProgressWheel;


public class Try extends Activity {

    ProgressWheel pb;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_try);
            Videoactivity.lis.finish();
        pb=(ProgressWheel)findViewById(R.id.progressBar2);
        txt=(TextView)findViewById(R.id.textvvvvvvvv);
        Bundle bundle = getIntent().getExtras();
        final String s = bundle.getString("title", null);
        final String head = bundle.getString("head", null);
        final String date1 = bundle.getString("date", null);
        final  String dsecp = bundle.getString("descp", null);
        final String img = bundle.getString("img", null);
        final String id1 = bundle.getString("id", null);
        final  String src11 = bundle.getString("src", null);
        final String plc11 = bundle.getString("plc", null);
        final String idvideo = bundle.getString("key", null);

        Handler handler2 = new Handler();

        handler2.postDelayed(new Runnable() {

            @Override
            public void run()

            {
                Intent in = new Intent(Videoadapter.ctc2,Videoactivity.class);
                in.putExtra("title", s);
                in.putExtra("head", head);
                in.putExtra("date", date1);
                in.putExtra("descp", dsecp);
                in.putExtra("img", img);
                in.putExtra("src", src11);
                in.putExtra("plc", plc11);
                in.putExtra("id", id1);
                in.putExtra("key", idvideo);
                startActivity(in);
                pb.setVisibility(View.GONE);
                txt.setText("Success");
                finish();

            }
        }, 4000);

    }


}
