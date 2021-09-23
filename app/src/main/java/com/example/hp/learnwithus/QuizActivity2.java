package com.example.hp.learnwithus;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class QuizActivity2 extends Activity {

    ArrayList<Question2> question2s;
    int score = 0;

    private int CurrentQuestion2Index=0 ;


    TextView txtQuestion2;
    RadioButton rda, rdb, rdc,grp_selected;
    Button butNext;
    RadioGroup grp_choices;
    DatabaseReference fb;
    @Override
    protected void onStart() {
        super.onStart();

        fb.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                question2s=new ArrayList<Question2>();



                for (DataSnapshot q:dataSnapshot.getChildren()) {
                    Question2 question21 = q.getValue(Question2.class);
                    //adding artist to the list
                    question2s.add(question21);

                }
                CurrentQuestion2Index = 0;
                setQuestion2View(CurrentQuestion2Index);


                butNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        advance();



                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);


        txtQuestion2 = (TextView) findViewById(R.id.textView1);


        rda = (RadioButton) findViewById(R.id.radio0);
        rdb = (RadioButton) findViewById(R.id.radio1);
        rdc = (RadioButton) findViewById(R.id.radio2);
        butNext = (Button) findViewById(R.id.button1);
        grp_choices = (RadioGroup) findViewById(R.id.radioGroup1);
        fb = FirebaseDatabase.getInstance().getReference("questions2");}







    private void setQuestion2View(int i) {
        txtQuestion2.setText(question2s.get(this.CurrentQuestion2Index).getQuestion());

        rda.setText(question2s.get(this.CurrentQuestion2Index).getChoicea());
        rdb.setText(question2s.get(this.CurrentQuestion2Index).getChoiceb());
        rdc.setText(question2s.get(this.CurrentQuestion2Index).getChoicec());


        grp_choices.clearCheck();


    }
    private boolean selectedAnswer() {
        String answer = "x";
        int id = grp_choices.getCheckedRadioButtonId();
        grp_selected = (RadioButton) findViewById(id);
        if (rda.isChecked())
        {
            answer = "a";
        }
        if (rdb.isChecked()) {
            answer = "b";
        }
        if (rdc.isChecked()){
            answer = "c";
        }
        return (isCorrectAnswer(answer));
    }

    private boolean isCorrectAnswer(String answer) {
        if (answer.equals(question2s.get(this.CurrentQuestion2Index).getAnswer())) {
            return true;
        }
        else {
            return false;
        }


    }


    private void advance()
    {     if (selectedAnswer()==true) {
        score++;
        Toast.makeText(getApplicationContext(), "Right" , Toast.LENGTH_SHORT).show();

    }
    else {
        Toast.makeText(getApplicationContext(), "wrong", Toast.LENGTH_SHORT).show();
    }


        CurrentQuestion2Index=(CurrentQuestion2Index+1)%10;
        if(CurrentQuestion2Index==8)
        {
            Intent intent=new Intent(QuizActivity2.this,ResultActivity.class);
            intent.putExtra("score",score);

            startActivity(intent);

        }
        else
        {     setQuestion2View(CurrentQuestion2Index);


        }



    }


}













