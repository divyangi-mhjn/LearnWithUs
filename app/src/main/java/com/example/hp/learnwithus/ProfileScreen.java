package com.example.hp.learnwithus;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileScreen extends AppCompatActivity {

    private Button  btnSendResetEmail, btnRemoveUser,
             sendEmail, remove;

    private EditText oldEmail;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private  FirebaseUser user;
    TextView emaill,welcome;
     String email;
    TextView pr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
         user = FirebaseAuth.getInstance().getCurrentUser();
         email = user.getEmail();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab4);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(ProfileScreen.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(ProfileScreen.this, Login.class));
                    finish();
                }
            }
        };

        pr=(TextView)findViewById(R.id.pr);



        btnSendResetEmail = (Button) findViewById(R.id.sending_pass_reset_button);
        btnRemoveUser = (Button) findViewById(R.id.remove_user_button);

        sendEmail = (Button) findViewById(R.id.send);
        emaill=(TextView)findViewById(R.id.emaill);
        welcome=(TextView)findViewById(R.id.welcome);
        remove = (Button) findViewById(R.id.remove);


        oldEmail = (EditText) findViewById(R.id.old_email);



        oldEmail.setVisibility(View.GONE);
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });




        sendEmail.setVisibility(View.GONE);
        remove.setVisibility(View.GONE);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        emaill.setText(email);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }


        btnSendResetEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldEmail.setVisibility(View.VISIBLE);




                sendEmail.setVisibility(View.VISIBLE);
                remove.setVisibility(View.GONE);
            }
        });

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (!oldEmail.getText().toString().trim().equals("")) {
                    auth.sendPasswordResetEmail(oldEmail.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ProfileScreen.this, "Reset password email is sent!", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(ProfileScreen.this, "Failed to send reset email!Please Login Again", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                } else {
                    oldEmail.setError("Enter email");
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        btnRemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendEmail.setVisibility(View.GONE);

                oldEmail.setVisibility(View.GONE);
                remove.setVisibility(View.VISIBLE);

            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {



                        progressBar.setVisibility(View.VISIBLE);
                        user.delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(ProfileScreen.this, "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(ProfileScreen.this, Signup.class));
                                            finish();
                                            progressBar.setVisibility(View.GONE);
                                        } else {
                                            Toast.makeText(ProfileScreen.this, "Failed to delete your account!Please Login Again", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                    }
                }

        });




    }
    private void updateUI(FirebaseUser user) {

        if (user == null) {
            Intent d = new Intent(ProfileScreen.this, Login.class);
            Toast.makeText(this, "Please login again", Toast.LENGTH_SHORT).show();
            startActivity(d);

        }

        }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);

    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
    @Override
    public void onBackPressed() {
        finish();

    }

}