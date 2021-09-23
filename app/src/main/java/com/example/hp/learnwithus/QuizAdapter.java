package com.example.hp.learnwithus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyViewHolder>  {

    private Context mContext;
    private List<Quiz> quizList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title2, count;
        public ImageView thumbnail2, overflow;




        public MyViewHolder(View view) {
            super(view);
            title2 = (TextView) view.findViewById(R.id.title2);


            thumbnail2 = (ImageView) view.findViewById(R.id.thumbnail2);

        }


    }



    public QuizAdapter(Context mContext, List<Quiz> quizList) {
        this.mContext = mContext;
        this.quizList = quizList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card2, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Quiz quiz =quizList.get(position);
        holder.title2.setText(quiz.getName2());
        Glide.with(mContext).load(quiz.getThumbnail2()).into(holder.thumbnail2);
        holder.title2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quiz.getName2();

                switch (quiz.getName2()){
                    case "Beginner":
                    {
                        Intent intent=new Intent(mContext,QuizActivity1.class);
                        mContext.startActivity(intent);
                        break;
                    }


                    case "Intermediate":
                    {
                        Intent intent=new Intent(mContext,QuizActivity2.class);
                        mContext.startActivity(intent);
                        break;
                    }
                    case "Master":
                    {
                        Intent intent=new Intent(mContext,QuizActivity.class);
                        mContext.startActivity(intent);
                        break;
                    }

                    case "Expert" :
                    {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
                        alertDialogBuilder.setCancelable(true);
                        alertDialogBuilder.setTitle("Coming Soon");
                        alertDialogBuilder.setMessage("From LearnWithUs Team");
                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();

                        break;
                    }




                }


            }
        });



        // loading quiz cover using Glide library



    }


    /**
     * Showing popup menu when tapping on 3 dots
     */



    @Override
    public int getItemCount() {
        return quizList.size();
    }
}