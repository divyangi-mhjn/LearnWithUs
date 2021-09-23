package com.example.hp.learnwithus;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class Login extends AppCompatActivity {
    private Button login,reset,signup;
    private EditText password;
    private AutoCompleteTextView email;

    private ProgressDialog pd;



    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth fa;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signup = (Button) findViewById(R.id.btn_signup);
        email = (AutoCompleteTextView) findViewById(R.id.email);



        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btn_login);
        reset = (Button) findViewById(R.id.btn_reset_password);
        pd = new ProgressDialog(this);
        ArrayList<String> list = new ArrayList<String>(); // define this is as globally

        list.add(email.getText().toString());
        setSuggestions();


        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kj=new Intent(Login.this,Signup.class);
                startActivity(kj);
                finish();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kj=new Intent(Login.this,ResetPassword.class);
                startActivity(kj);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String str, str1;
                str = email.getText().toString().trim();
                str1 = password.getText().toString().trim();

                if (email.getText().toString().trim().length() == 0) {
                    email.setError("Enter E-mail ID");
                } else if (password.getText().toString().trim().length() == 0) {
                    password.setError("Enter password");
                } else if (email.getText().toString().trim().length() != 0 && password.getText().toString().trim().length() != 0) {
                    pd.setMessage("Signing in");
                    pd.show();


                    //authenticate user
                    mAuth.signInWithEmailAndPassword(str, str1)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.

                                    if (!task.isSuccessful()) {
                                        pd.dismiss();
                                        Toast.makeText(getApplicationContext(), "Wrong E-mail or password.", Toast.LENGTH_LONG).show();

                                    } else {

                                        pd.dismiss();
                                        Toast.makeText(getApplicationContext(), "Sign in successful.", Toast.LENGTH_LONG).show();
                                        Intent f = new Intent(Login.this, ChooseSubject.class);

                                        startActivity(f);
                                    }
                                }
                            });
                }




        }
        }); }
    void setSuggestions() {

        // Getting the string array from strings.xml


        // New Arrays list for storing items
        ArrayList<String> list = new ArrayList<String>();


            // Adding items to arary list
            list.add(email.getText().toString());


        // Adapter for holding the data view
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                Login.this, android.R.layout.simple_list_item_1, list);

        // Setting adapter to both textviews
        email.setAdapter(adapter);


        // Specify the minimum type of characters before drop-down list is shown
        email.setThreshold(1);


        // comma to separate the different items


        // When the user clicks an item of the drop-down list an toast will
        // shown





    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");

        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                Toast.makeText(Login.this, "Done",
                        Toast.LENGTH_LONG).show();

                AppExit();
            }
        });

        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);

        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.parseColor("#00bcd4"));
        nbutton.setTextColor(Color.parseColor("#00bcd4"));
        nbutton.setHintTextColor(Color.BLUE);
    }
    public void AppExit()
    {

        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }


}





