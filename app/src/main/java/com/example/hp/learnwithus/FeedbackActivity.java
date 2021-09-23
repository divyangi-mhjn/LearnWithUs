package com.example.hp.learnwithus;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Dimension;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity  {

    TextView tv1,tv2,tv3;
    EditText edit;

    private RatingBar ratingBar;
    ImageView img;
    TextView tv,hw1;

    Button btnSubmit1;
    String email;
    DatabaseReference databaseRef;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
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
        databaseRef = FirebaseDatabase.getInstance().getReference("feedback");
        img=(ImageView)findViewById(R.id.img);
        tv=(TextView)findViewById(R.id.tv);
        hw1=(TextView)findViewById(R.id.hw1);
        edit=(EditText)findViewById(R.id.edit);
        btnSubmit1=(Button)findViewById(R.id.btnSubmit);


        tv1=(TextView)findViewById(R.id.head);
        tv2=(TextView)findViewById(R.id.hw);
        user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();

        //getting the reference of editors node
        addListenerOnRatingBar();


        btnSubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();

            }
        });




        }
    private void addArtist() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        String smiley=Float.toString(ratingBar.getRating());
        //getting the values to save
        String feed = edit.getText().toString().trim();
        String el=email.toString() ;


        //checking if the value is provided
        if (!TextUtils.isEmpty(feed)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Editor
            String id = databaseRef.push().getKey();

            //creating an Editor Object
            Feedback feedback = new Feedback(id, feed,el,smiley);

            //Saving the Editor
            databaseRef.child(id).setValue(feedback);

            //setting edittext to blank again
            edit.setText("");

            //displaying a success toast
            Toast.makeText(this, "Thank You For Your Feedback", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please give suggestions", Toast.LENGTH_LONG).show();
        }
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);


        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                int rt=(int)rating;


                switch(rt){
                    case 1:{
                        img.setBackgroundResource(R.drawable.terrible);
                        tv.setText("Okay");
                        break;


                    }
                    case 2:{
                        img.setBackgroundResource(R.drawable.expresionless);
                        tv.setText("Average");
                        break;


                    }
                    case 3:{
                        img.setBackgroundResource(R.drawable.smile);
                        tv.setText("Satisfactory");
                        break;


                    }
                    case 4:{
                        img.setBackgroundResource(R.drawable.good);
                        tv.setText("Good");
                        break;


                    }

                    case 5:{
                        img.setBackgroundResource(R.drawable.excellent);
                        tv.setText("Excellent");
                        break;


                    }


                }
            }
        });
    }



    }




