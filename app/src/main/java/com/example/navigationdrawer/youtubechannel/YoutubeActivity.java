package com.example.navigationdrawer.youtubechannel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.home.HomeDrawer;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity {

    Button fullscreen;
    YouTubePlayerView youtube_player;
    OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        //initializing variables
        fullscreen=findViewById(R.id.fullscreen_btn);
        youtube_player=findViewById(R.id.youtube_playerID);


        onInitializedListener=new YouTubePlayer.OnInitializedListener(){
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo("vUn3TnyQx3o");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }

    };
        fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youtube_player.initialize("AIzaSyDWyhf-lP3PFOxi4Qcmb0gW0AK60niyqVA",onInitializedListener);
            }
        });
}
public void toHome(View view){
    Intent intent=new Intent(YoutubeActivity.this, HomeDrawer.class);
    startActivity(intent);
}
}