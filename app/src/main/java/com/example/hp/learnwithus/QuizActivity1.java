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

public class QuizActivity1 extends Activity {

    ArrayList<Question1> question1s;
    int score = 0;

    private int CurrentQuestion1Index=0 ;


    TextView txtQuestion1;
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
                question1s=new ArrayList<Question1>();



                for (DataSnapshot q:dataSnapshot.getChildren()) {
                    Question1 question11 = q.getValue(Question1.class);
                    //adding artist to the list
                    question1s.add(question11);

                }
                CurrentQuestion1Index = 0;
                setQuestion1View(CurrentQuestion1Index);


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


        txtQuestion1 = (TextView) findViewById(R.id.textView1);


        rda = (RadioButton) findViewById(R.id.radio0);
        rdb = (RadioButton) findViewById(R.id.radio1);
        rdc = (RadioButton) findViewById(R.id.radio2);
        butNext = (Button) findViewById(R.id.button1);
        grp_choices = (RadioGroup) findViewById(R.id.radioGroup1);
        fb = FirebaseDatabase.getInstance().getReference("questions1");}







    private void setQuestion1View(int i) {
        txtQuestion1.setText(question1s.get(this.CurrentQuestion1Index).getQuestion());

        rda.setText(question1s.get(this.CurrentQuestion1Index).getChoicea());
        rdb.setText(question1s.get(this.CurrentQuestion1Index).getChoiceb());
        rdc.setText(question1s.get(this.CurrentQuestion1Index).getChoicec());


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
        if (answer.equals(question1s.get(this.CurrentQuestion1Index).getAnswer())) {
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


        CurrentQuestion1Index=(CurrentQuestion1Index+1)%7;
        if(CurrentQuestion1Index==5)
        {
            Intent intent=new Intent(QuizActivity1.this,ResultActivity.class);
            intent.putExtra("score",score);

            startActivity(intent);

        }
        else
        {     setQuestion1View(CurrentQuestion1Index);


        }



    }


}













