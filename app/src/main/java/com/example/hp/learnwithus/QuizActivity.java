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

public class QuizActivity extends Activity {

    ArrayList<Question> questions;
    int score = 0;

    private int CurrentQuestionIndex=0 ;


    TextView txtQuestion;
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
                questions=new ArrayList<Question>();



                for (DataSnapshot q:dataSnapshot.getChildren()) {
                    Question question1 = q.getValue(Question.class);
                    //adding artist to the list
                    questions.add(question1);

                }
                CurrentQuestionIndex = 0;
                setQuestionView(CurrentQuestionIndex);


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
        setContentView(R.layout.activity_quiz);


        txtQuestion = (TextView) findViewById(R.id.textView1);


        rda = (RadioButton) findViewById(R.id.radio0);
        rdb = (RadioButton) findViewById(R.id.radio1);
        rdc = (RadioButton) findViewById(R.id.radio2);
        butNext = (Button) findViewById(R.id.button1);
        grp_choices = (RadioGroup) findViewById(R.id.radioGroup1);
        fb = FirebaseDatabase.getInstance().getReference("questions");}







    private void setQuestionView(int i) {
        txtQuestion.setText(questions.get(this.CurrentQuestionIndex).getQuestion());
        rda.setText(questions.get(this.CurrentQuestionIndex).getChoicea());
        rdb.setText(questions.get(this.CurrentQuestionIndex).getChoiceb());
        rdc.setText(questions.get(this.CurrentQuestionIndex).getChoicec());


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
        if (answer.equals(questions.get(this.CurrentQuestionIndex).getAnswer())) {
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


        CurrentQuestionIndex=(CurrentQuestionIndex+1)%12;
        if(CurrentQuestionIndex==10)
        {
            Intent intent=new Intent(QuizActivity.this,ResultActivity.class);
           intent.putExtra("score",score);

            startActivity(intent);

        }
        else
        {     setQuestionView(CurrentQuestionIndex);


        }



        }



    }













