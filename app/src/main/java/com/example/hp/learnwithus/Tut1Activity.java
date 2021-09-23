package com.example.hp.learnwithus;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

import static com.example.hp.learnwithus.R.id.textView;

public class Tut1Activity extends AppCompatActivity


{
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10;
    ImageButton im1,im2,im3,im4,im5,im6,im7,im8,im9,im10;
    String basic,loops,array,object,inher,abs,exc,multi,io,ntw;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut1);
        basic = "basic";
        loops = "loops";
        array = "array";
        object = "object";
        inher = "inher";
        abs = "abs";
        exc = "exc";
        multi = "multi";
        io = "io";
        ntw = "ntw";


        tv1 = (TextView) findViewById(R.id.basic1);
        tv2 = (TextView) findViewById(R.id.loop1);
        tv3 = (TextView) findViewById(R.id.array1);
        tv4 = (TextView) findViewById(R.id.object1);
        tv5 = (TextView) findViewById(R.id.inher1);
        tv6 = (TextView) findViewById(R.id.abs1);
        tv7 = (TextView) findViewById(R.id.exc1);
        tv8 = (TextView) findViewById(R.id.multi1);
        tv9 = (TextView) findViewById(R.id.io1);
        tv10 = (TextView) findViewById(R.id.ntw1);


        im1 = (ImageButton) findViewById(R.id.basic);
        im2 = (ImageButton) findViewById(R.id.loop);
        im3 = (ImageButton) findViewById(R.id.array);
        im4 = (ImageButton) findViewById(R.id.object);
        im5 = (ImageButton) findViewById(R.id.inher);
        im6 = (ImageButton) findViewById(R.id.abs);
        im7 = (ImageButton) findViewById(R.id.exc);
        im8 = (ImageButton) findViewById(R.id.multi);
        im9 = (ImageButton) findViewById(R.id.io);
        im10 = (ImageButton) findViewById(R.id.ntw);
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

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tut1Activity.this, Tut2Activity.class);
                intent.putExtra("value", basic);

                startActivity(intent);
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tut1Activity.this, Tut2Activity.class);
                intent.putExtra("value", loops);

                startActivity(intent);
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tut1Activity.this, Tut2Activity.class);
                intent.putExtra("value", array);

                startActivity(intent);
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tut1Activity.this, Tut2Activity.class);
                intent.putExtra("value", object);

                startActivity(intent);
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tut1Activity.this, Tut2Activity.class);
                intent.putExtra("value", inher);

                startActivity(intent);
            }
        });
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tut1Activity.this, Tut2Activity.class);
                intent.putExtra("value", abs);

                startActivity(intent);
            }
        });
        im7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tut1Activity.this, Tut2Activity.class);
                intent.putExtra("value", exc);

                startActivity(intent);
            }
        });
        im8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tut1Activity.this, Tut2Activity.class);
                intent.putExtra("value", multi);


                startActivity(intent);
            }
        });
        im9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tut1Activity.this, Tut2Activity.class);
                intent.putExtra("value", io);

                startActivity(intent);
            }
        });
        im10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tut1Activity.this, Tut2Activity.class);
                intent.putExtra("value", ntw);

                startActivity(intent);
            }
        });


    }
    @Override
    public void onBackPressed() {
      finish();

    }

}
