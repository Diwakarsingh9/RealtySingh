package com.Apporio.realtysingh;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.widget.Toast;

import com.Apporio.realtysingh.R;
import com.Apporio.realtysingh.imageloading.ImageLoader;
import com.Apporio.realtysingh.parsing_files.parsingforrecent;
import com.Apporio.realtysingh.parsing_files.parsingforrecentvideos;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Videoactivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {
    public static TextView headl, date11, title, descp, src, plc, relatedsearch;
    public static ImageView back;
    public static ImageLoader il;
    public static View v111;
    private static final int PORTRAIT_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
    public static YouTubePlayer player;


    private boolean isFullscreen;

    public static LinearLayout llforlist, llforrecnt;
    public static ProgressBar pb;
    public static Videoactivity lis;
    String idvideo;
    String DEVELOPER_KEY = "AIzaSyCvTcjzBZggmzTEvtujaYELU9GytcEcz0s";
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private YouTubePlayerView youTubeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoactivity);
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        lis = Videoactivity.this;

        title = (TextView) findViewById(R.id.titlehead);
        back = (ImageView) findViewById(R.id.back);
        headl = (TextView) findViewById(R.id.titleddd);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        date11 = (TextView) findViewById(R.id.date);
        v111 = (View) findViewById(R.id.vew);
        relatedsearch = (TextView) findViewById(R.id.related);
        llforlist = (LinearLayout) findViewById(R.id.listforrelated);
        llforrecnt = (LinearLayout) findViewById(R.id.listforrelated);
        src = (TextView) findViewById(R.id.src11);
        plc = (TextView) findViewById(R.id.plc);
        descp = (TextView) findViewById(R.id.descp);
        //dp= (ImageView) findViewById(R.id.logo);
        llforlist.removeAllViews();
        Videoactivity.relatedsearch.setVisibility(View.GONE);
        Videoactivity.v111.setVisibility(View.GONE);
        il = new ImageLoader(Videoactivity.this);
        Bundle bundle = getIntent().getExtras();
        String s = bundle.getString("title", null);
        String head = bundle.getString("head", null);
        String date1 = bundle.getString("date", null);
        String dsecp = bundle.getString("descp", null);
        String img = bundle.getString("img", null);
        String id1 = bundle.getString("id", null);
        String src11 = bundle.getString("src", null);
        String plc11 = bundle.getString("plc", null);
        idvideo = bundle.getString("key", null);
        relatedsearch.setText("Related " + s);

        parsingforrecentvideos.parsing(Videoactivity.this, id1, s);

        // il.DisplayImage("http://keshavgoyal.com/realtysingh/" + img, dp);
        title.setText("" + s.toUpperCase());
        src.setText("" + src11);
        plc.setText("" + plc11);
        headl.setText("" + Html.fromHtml("" + head).toString());
        String des = Html.fromHtml("" + dsecp).toString();
        descp.setText(des);
        date11.setText("" + date1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this);


        // Initializing video player with developer key


    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {


        player.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);

        //This flag tells the player to automatically enter fullscreen when in landscape. Since we don't have
        //landscape layout for this activity, this is a good way to allow the user rotate the video player.
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);

        //This flag controls the system UI such as the status and navigation bar, hiding and showing them
        //alongside the player UI
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);
        if (!wasRestored) {



            player.loadVideo(idvideo);

            // Hiding player controls
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(DeveloperKey.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = Videoactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Videoactivity.this.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Window window = Videoactivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }







}
