package com.example.hp.learnwithus;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResultActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button tv,tv1;
    ImageButton replay,home;
    @Override
    public void onStart() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        super.onStart();
        String name = user.getDisplayName();
        String email = user.getEmail();


        // The user's ID, unique to the Firebase project. Do NOT use this value to
        // authenticate with your backend server, if you have one. Use
        // FirebaseUser.getToken() instead.
        String uid = user.getUid();
        // Check if user is signed in (non-null) and update UI accordingly.
           if(user!=null)
        tv.setText( email);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        tv=(Button)findViewById(R.id.tv);
        tv1=(Button)findViewById(R.id.tv1);
        replay=(ImageButton)findViewById(R.id.replay);
        home=(ImageButton)findViewById(R.id.home);
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser()!= null)
                {
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                }

            }
        };
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ResultActivity.this,QuizMain.class);
                startActivity(i);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(ResultActivity.this,HomeActivity.class);
                startActivity(b);
            }
        });
        tv1.setText("YOUR SCORE IS \n " +score );

    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),QuizMain.class);
        startActivity(intent);

    }



}

