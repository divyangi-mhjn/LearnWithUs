package com.example.hp.learnwithus;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;

import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TutorialFinal extends AppCompatActivity  {
    TextView value1,value2,value3,value4,ert;
    DatabaseReference ref1,   databaseArtists;
    ArrayList<Favorite> favorite;
    String s1,s2,s3,s4;
    ArrayList<TutFinal> tutfinal;

    ImageButton butto;
    String value;
    FirebaseUser user;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_tutorial_final);
        Bundle b = getIntent().getExtras();
        value= b.getString("value");
        value1=(TextView)findViewById(R.id.one);
        value2=(TextView)findViewById(R.id.two);
        value3=(TextView)findViewById(R.id.three);
        value4=(TextView)findViewById(R.id.four);
        ert=(TextView)findViewById(R.id.ert);
        ref1= FirebaseDatabase.getInstance().getReference("tutfinal");
        databaseArtists= FirebaseDatabase.getInstance().getReference("favorite");
           butto=(ImageButton)findViewById(R.id.butt);

        user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();




        tutfinal = new ArrayList<>();
        favorite=new ArrayList<>();




    }
    private void addArtist(String st,String st2,String st3,String st4,String email) {
        //getting the values to save
        final String name = st.toString();
        final String name2 = st2.toString();
        final String name3 = st3.toString();
        final String name4 = st4.toString();
        final String el = email.toString();
        final int[] flag = {1};


        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Editor

            databaseArtists.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(final DataSnapshot dataSnapshot) {

                    favorite.clear();

                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist

                        if(postSnapshot.getValue(Favorite.class).getName().equals(name)) {
                            flag[0] =0;


                        }
                    }
                        switch(flag[0]){

                            case 1:
                            {
                                String id = databaseArtists.push().getKey();

                                Favorite favoritel = new Favorite(name, name2, name3, name4, id, el);
                                //Saving the Editor

                                databaseArtists.child(id).setValue(favoritel);
                                butto.setPressed(false);


                                //setting edittext to blank again


                                //displaying a success toast
                                Toast.makeText(TutorialFinal.this, "Added to favorites", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            case 0:{
                                Toast.makeText(TutorialFinal.this, "This exists in favorites", Toast.LENGTH_SHORT).show();



                                break;
                            }

                            default:
                                Toast.makeText(TutorialFinal.this, "server error!please try after sometime", Toast.LENGTH_SHORT).show();





                        }

                        }










                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            //creating an Editor Object


        }
    }





    @Override
    protected void onStart() {
        super.onStart();

        //attaching value event listener

        if (value.equals("history")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                               tutfinal.clear();
                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        TutFinal tut21 = postSnapshot.child("history").getValue(TutFinal.class);


                        // first textview
                        String str1 =tut21.getValue1();
                        String kept1 = str1.substring( 0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":")+1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 =tut21.getValue2();
                        String kept2 = str2.substring( 0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":")+1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);



                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 =tut21.getValue3();
                        String kept3 = str3.substring( 0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":")+1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                        
                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 =tut21.getValue4();
                        String kept4 = str4.substring( 0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":")+1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);


                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                addArtist(s1,s2,s3,s4,email);

                            }
                        });





                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("features")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    tutfinal.clear();
                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        TutFinal tut21 = postSnapshot.child("features").getValue(TutFinal.class);

                        // first textview
                        String str1 =tut21.getValue1();
                        String kept1 = str1.substring( 0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":")+1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 =tut21.getValue2();
                        String kept2 = str2.substring( 0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":")+1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 =tut21.getValue3();
                        String kept3 = str3.substring( 0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":")+1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 =tut21.getValue4();
                        String kept4 = str4.substring( 0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":")+1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);

                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });

        } else if (value.equals("path")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        Tut2 tut21 = postSnapshot.child("path").getValue(Tut2.class);
                        // first textview
                        String str1 =tut21.getValue1();
                        String kept1 = str1.substring( 0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":")+1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 =tut21.getValue2();
                        String kept2 = str2.substring( 0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":")+1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 =tut21.getValue3();
                        String kept3 = str3.substring( 0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":")+1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 =tut21.getValue4();
                        String kept4 = str4.substring( 0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":")+1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("jvm")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("jvm").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("operator")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("operator").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("variable")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("variable").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }


                    //creating adapter


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("ifElse")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("ifElse").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                    //creating adapter


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("switchh")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("switchh").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("forr")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("forr").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("whilee")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("whilee").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("breakk")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("breakk").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("comments")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("comments").getValue(Tut2.class);
                        // first textview
                        String str1 =tut21.getValue1();
                        String kept1 = str1.substring( 0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":")+1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 =tut21.getValue2();
                        String kept2 = str2.substring( 0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":")+1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 =tut21.getValue3();
                        String kept3 = str3.substring( 0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":")+1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 =tut21.getValue4();
                        String kept4 = str4.substring( 0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":")+1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }


       else if (value.equals("array1")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    tutfinal.clear();
                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        TutFinal tut21 = postSnapshot.child("array1").getValue(TutFinal.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("typess")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    tutfinal.clear();
                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                        TutFinal tut21 = postSnapshot.child("typess").getValue(TutFinal.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });

        } else if (value.equals("declar")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        Tut2 tut21 = postSnapshot.child("declar").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("names")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("names").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("matrices")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("matrices").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("examples")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("examples").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }


                    //creating adapter


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("oop")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("oop").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }


                    //creating adapter


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("naming")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("naming").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("constructor")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("constructor").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("staticc")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("staticc").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("thiss")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("thiss").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("objectt")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("objectt").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str2.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }

       else if (value.equals("inh")) {



            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        Tut2 tut21 = postSnapshot.child("inh").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("over")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    tutfinal.clear();
                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                        TutFinal tut21 = postSnapshot.child("over").getValue(TutFinal.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });

        } else if (value.equals("rid")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        Tut2 tut21 = postSnapshot.child("rid").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("sup")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("sup").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("run")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("run").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("ins")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("ins").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }


                    //creating adapter


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("abst")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("abst").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                    //creating adapter


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("inte")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("inte").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("ver")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("ver").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("pack")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("pack").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("access")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("access").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("enca")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("enca").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }


       else if (value.equals("introd")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    tutfinal.clear();
                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        TutFinal tut21 = postSnapshot.child("introd").getValue(TutFinal.class);
                        // first textview
                        String str1 =tut21.getValue1();
                        String kept1 = str1.substring( 0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":")+1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 =tut21.getValue2();
                        String kept2 = str2.substring( 0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":")+1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 =tut21.getValue3();
                        String kept3 = str3.substring( 0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":")+1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 =tut21.getValue4();
                        String kept4 = str4.substring( 0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":")+1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("tr")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    tutfinal.clear();
                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                        TutFinal tut21 = postSnapshot.child("tr").getValue(TutFinal.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });

        } else if (value.equals("thr")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        Tut2 tut21 = postSnapshot.child("thr").getValue(Tut2.class);
                        // first textview
                        String str1 =tut21.getValue1();
                        String kept1 = str1.substring( 0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":")+1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 =tut21.getValue2();
                        String kept2 = str2.substring( 0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":")+1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 =tut21.getValue3();
                        String kept3 = str3.substring( 0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":")+1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 =tut21.getValue4();
                        String kept4 = str4.substring( 0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":")+1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("fina")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("fina").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("thr")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("thr").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("handle")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("handle").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }


                    //creating adapter


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("introduce")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("introduce").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                    //creating adapter


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("lifecycle")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("lifecycle").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }else if (value.equals("create")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("create").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("thread")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("thread").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("name")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("name").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("daemon")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("daemon").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("inp")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    tutfinal.clear();
                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        TutFinal tut21 = postSnapshot.child("inp").getValue(TutFinal.class);
                        // first textview
                        String str1 =tut21.getValue1();
                        String kept1 = str1.substring( 0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":")+1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 =tut21.getValue2();
                        String kept2 = str2.substring( 0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":")+1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 =tut21.getValue3();
                        String kept3 = str3.substring( 0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":")+1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 =tut21.getValue4();
                        String kept4 = str4.substring( 0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":")+1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("inps")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    tutfinal.clear();
                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                        TutFinal tut21 = postSnapshot.child("inps").getValue(TutFinal.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });

        } else if (value.equals("bos")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        Tut2 tut21 = postSnapshot.child("bos").getValue(Tut2.class);
                        // first textview
                        String str1 =tut21.getValue1();
                        String kept1 = str1.substring( 0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":")+1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 =tut21.getValue2();
                        String kept2 = str2.substring( 0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":")+1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 =tut21.getValue3();
                        String kept3 = str3.substring( 0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":")+1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 =tut21.getValue4();
                        String kept4 = str4.substring( 0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":")+1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("fp")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("fp").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("wr")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("wr").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("writ")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("writ").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }


                    //creating adapter


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("ntww")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("ntww").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }


                    //creating adapter


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("sock")) {



            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("sock").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }else if (value.equals("url")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("url").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        } else if (value.equals("hrl")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("hrl").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("ht")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("ht").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
        else if (value.equals("datag")) {


            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        //getting artist
                        Tut2 tut21 = postSnapshot.child("datag").getValue(Tut2.class);
                        // first textview
                        String str1 = tut21.getValue1();
                        String kept1 = str1.substring(0, str1.indexOf(":"));
                        String remainder1 = str1.substring(str1.indexOf(":") + 1, str1.length());
                        Spannable word1 = new SpannableString(kept1);

                        word1.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word1.setSpan(new RelativeSizeSpan(2f), 0, word1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value1.setText(word1);
                        Spannable wordTwo1 = new SpannableString(remainder1);

                        wordTwo1.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value1.append(wordTwo1);


                        // second textview
                        String str2 = tut21.getValue2();
                        String kept2 = str2.substring(0, str2.indexOf(":"));
                        String remainder2 = str2.substring(str2.indexOf(":") + 1, str2.length());
                        Spannable word2 = new SpannableString(kept2);

                        word2.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word2.setSpan(new RelativeSizeSpan(2f), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value2.setText(word2);
                        Spannable wordTwo2 = new SpannableString(remainder2);

                        wordTwo2.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value2.append(wordTwo2);

                        // third textview
                        String str3 = tut21.getValue3();
                        String kept3 = str3.substring(0, str3.indexOf(":"));
                        String remainder3 = str3.substring(str3.indexOf(":") + 1, str3.length());
                        Spannable word3 = new SpannableString(kept3);

                        word3.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word3.setSpan(new RelativeSizeSpan(2f), 0, word3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value3.setText(word3);
                        Spannable wordTwo3 = new SpannableString(remainder3);

                        wordTwo3.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value3.append(wordTwo3);


                        // fourth textview
                        String str4 = tut21.getValue4();
                        String kept4 = str4.substring(0, str4.indexOf(":"));
                        String remainder4 = str4.substring(str4.indexOf(":") + 1, str4.length());
                        Spannable word4 = new SpannableString(kept4);

                        word4.setSpan(new ForegroundColorSpan(Color.parseColor("#00bcd4")), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        word4.setSpan(new RelativeSizeSpan(2f), 0, word4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                        value4.setText(word4);
                        Spannable wordTwo4 = new SpannableString(remainder4);

                        wordTwo4.setSpan(new ForegroundColorSpan(Color.parseColor("#787878")), 0, wordTwo4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        value4.append(wordTwo4);
                        s1=str1.toString();
                        s2=str2.toString();
                        s3=str3.toString();
                        s4=str4.toString();
                        butto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addArtist(s1,s2,s3,s4,email);

                            }
                        });
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }


    }







}
