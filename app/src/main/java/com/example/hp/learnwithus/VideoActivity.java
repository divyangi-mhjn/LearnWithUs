package com.example.hp.learnwithus;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    ImageButton clk;
    VideoView videov;
    MediaController mediaC;
    GestureDetector mGestureDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        clk=(ImageButton)findViewById(R.id.button);
        TextView tv=(TextView) findViewById(R.id.tv);
        videov=(VideoView)findViewById(R.id.videoView);
        mediaC= new MediaController(this);
        String videopath="android.resource://com.example.hp.learnwithus/"+R.raw.install;
        Uri uri=Uri.parse(videopath);
        videov.setVideoURI(uri);
        videov.seekTo(100);
        videov.setMediaController(mediaC);
        mediaC.setAnchorView(videov);
        mGestureDetector = new GestureDetector(this, mGestureListener);



        videov.setOnTouchListener(mTouchListener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),VideoMain.class));
            }
        });





    }
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mGestureDetector.onTouchEvent(event);

            return true;
        }
    };
    private GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if(videov.isPlaying())
            {videov.pause();
            }
            else{
                videov.start(); }
            return true;
        };

    };


    public void videoplay(View v){
        String videopath="android.resource://com.example.hp.learnwithus/"+R.raw.install;
        Uri uri=Uri.parse(videopath);
        videov.setVideoURI(uri);
        videov.seekTo(100);
        videov.setMediaController(mediaC);
        mediaC.setAnchorView(videov);



    }

}
