package com.example.hp.learnwithus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Signup extends AppCompatActivity {


    private Button signup, existing1;
    private EditText email, password;
    TextView name;
    private ProgressDialog pd;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth fa;
    private String regexstr="[a-zA-z]";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup = (Button) findViewById(R.id.sign_up_button);
        email = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        existing1 = (Button) findViewById(R.id.sign_in_button);
        pd = new ProgressDialog(this);
        fa = FirebaseAuth.getInstance();
        name=(TextView)findViewById(R.id.you);


        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser()!= null)
                {
                    startActivity(new Intent(getApplicationContext(),ChooseSubject.class));
                }

            }
        };

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username();
            }
        });

        existing1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kj=new Intent(Signup.this,Login.class);
                startActivity(kj);
                finish();
            }
        });



    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }




    protected void username() {
        String str, str1;
        str = email.getText().toString().trim();
        str1 = password.getText().toString().trim();
        if (email.getText().toString().trim().length() == 0) {
            email.setError("Enter E-mail ID");
        } else if (password.getText().toString().trim().length() == 0) {
            password.setError("Enter password");
        }


        else if (password.getText().toString().trim().length() <6) {
            password.setError("password length minimum 6 letters");}

        else if (email.getText().toString().trim().length() != 0 && password.getText().toString().trim().length() != 0) {
            pd.setMessage("Signing up.");
            pd.show();

            fa.createUserWithEmailAndPassword(str, str1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();

                                pd.dismiss();
                                Toast.makeText(getApplicationContext(), "Account created.", Toast.LENGTH_LONG).show();
                                Intent d = new Intent(getApplicationContext(), ChooseSubject.class);
                                startActivity(d);
                            } else {
                                FirebaseUser user = mAuth.getCurrentUser();

                                pd.dismiss();
                                email.setError("E-mail ID not valid");

                            }
                        }

                    });
        }
    }
    private void updateUI(FirebaseUser user) {

        if (user != null) {
            Intent d = new Intent(Signup.this, ChooseSubject.class);
            startActivity(d);

        } else {

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    username();
                }
            });


        }
    }
    @Override
    public void onBackPressed() {
    Intent intent=new Intent(Signup.this,Login.class);
        startActivity(intent);

    }


}



