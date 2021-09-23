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


public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder>  {

    private Context mContext;
    private List<Subject> subjectList;
    TextView suu;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title1, count;
        public ImageView thumbnail1, overflow;




        public MyViewHolder(View view) {
            super(view);
            title1 = (TextView) view.findViewById(R.id.title1);


            thumbnail1 = (ImageView) view.findViewById(R.id.thumbnail1);

        }


    }



    public SubjectAdapter(Context mContext, List<Subject> subjectList) {
        this.mContext = mContext;
        this.subjectList = subjectList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Subject subject =subjectList.get(position);
        holder.title1.setText(subject.getName1());
        Glide.with(mContext).load(subject.getThumbnail1()).into(holder.thumbnail1);
        holder.title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                subject.getName1();

                switch (subject.getName1()){
                    case "Android":
                    { AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
                        alertDialogBuilder.setCancelable(true);
                        alertDialogBuilder.setTitle("Coming Soon");
                        alertDialogBuilder.setMessage("From LearnWithUs Team");
                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();

                        break;
                    }

                    case "Java":
                    {
                        Intent intent=new Intent(mContext,HomeActivity.class);
                        mContext.startActivity(intent);

                        break;
                    }
                    case "Asp.net":
                    {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
                        alertDialogBuilder.setCancelable(true);
                        alertDialogBuilder.setTitle("Coming Soon");
                        alertDialogBuilder.setMessage("From LearnWithUs Team");
                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();

                        break;
                    }

                    case "C" :
                    {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
                        alertDialogBuilder.setCancelable(true);
                        alertDialogBuilder.setTitle("Coming Soon");
                        alertDialogBuilder.setMessage("From LearnWithUs Team");
                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();

                        break;
                    }
                    case "C++" :
                    {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
                        alertDialogBuilder.setCancelable(true);
                        alertDialogBuilder.setTitle("Coming Soon");
                        alertDialogBuilder.setMessage("From LearnWithUs Team");
                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();

                        break;
                    }
                    case "Python" :
                    {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
                        alertDialogBuilder.setCancelable(true);
                        alertDialogBuilder.setTitle("Coming Soon");
                        alertDialogBuilder.setMessage("From LearnWithUs Team");
                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();

                        break;
                    }
                    case "Oracle" :
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



        // loading subject cover using Glide library



    }


    /**
     * Showing popup menu when tapping on 3 dots
     */



    @Override
    public int getItemCount() {
        return subjectList.size();
    }
}