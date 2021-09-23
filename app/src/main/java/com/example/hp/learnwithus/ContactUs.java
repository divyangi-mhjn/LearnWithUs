package com.example.hp.learnwithus;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity implements AnimationListener{
    ImageButton btn1,btn2;
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    Animation animationslidedown,animationslideup;
   CardView card1,card2;
    ImageButton bt1,bt2;
    TextView t1,t2,t3,t4,t5,t6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));

            }
        });
        btn1=(ImageButton)findViewById(R.id.basic);
        btn2=(ImageButton)findViewById(R.id.basic1);
        tv1=(TextView)findViewById(R.id.b1);
        tv2=(TextView)findViewById(R.id.b2);
        tv3=(TextView)findViewById(R.id.b3);
        tv4=(TextView)findViewById(R.id.b4);
        tv5=(TextView)findViewById(R.id.b6);
        tv6=(TextView)findViewById(R.id.b7);
        card1=(CardView)findViewById(R.id.card1);
        card2=(CardView)findViewById(R.id.card2);

        t1=(TextView)findViewById(R.id.mail2);
        t2=(TextView)findViewById(R.id.no2);
        t3=(TextView)findViewById(R.id.add2);
        t4=(TextView)findViewById(R.id.mail1);
        t5=(TextView)findViewById(R.id.no1);
        t6=(TextView)findViewById(R.id.add1);
       animationslideup= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
        animationslidedown=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        animationslidedown.setAnimationListener(this);
        animationslideup.setAnimationListener(this);

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                card2.setVisibility(View.VISIBLE);
                card1.setVisibility(View.GONE);

                card2.startAnimation(animationslidedown);

            }
        });




        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card1.setVisibility(View.VISIBLE);
                card1.startAnimation(animationslidedown);
                card2.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation==animationslidedown){

        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
