package com.Apporio.realtysingh;

import android.app.Application;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

/**
 * Created by saifi45 on 12/7/2015.
 */
public class CustomApplication extends Application implements YouTubePlayer.OnInitializedListener {

   public CustomApplication customApplication = CustomApplication.this;
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
