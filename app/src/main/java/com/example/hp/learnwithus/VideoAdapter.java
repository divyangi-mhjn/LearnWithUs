package com.example.hp.learnwithus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder>  {

private Context mContext;
private List<Video> videoList;
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



    public VideoAdapter(Context mContext, List<Video> videoList) {
        this.mContext = mContext;
        this.videoList = videoList;
    }

    @Override
    public VideoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_cardd, parent, false);

        return new VideoAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoAdapter.MyViewHolder holder, int position) {
        final Video video =videoList.get(position);
        holder.title1.setText(video.getName1());
        Glide.with(mContext).load(video.getThumbnail1()).into(holder.thumbnail1);
        holder.title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                video.getName1();

                switch (video.getName1()){
                    case "Installing the jdk":
                    { Intent intent=new Intent(mContext,VideoActivity.class);
                        mContext.startActivity(intent);;

                        break;
                    }

                    case "Running a Java Program":
                    {
                        Intent intent=new Intent(mContext,VideoActivity1.class);
                        mContext.startActivity(intent);

                        break;
                    }
                    case "Building a Basic Calculator":
                    {
                        Intent intent=new Intent(mContext,VideoActivity2.class);
                        mContext.startActivity(intent);
                        break;
                    }

                    case "Using Multiple Classes" :
                    {
                        Intent intent=new Intent(mContext,VideoActivity3.class);
                        mContext.startActivity(intent);

                        break;
                    }
                    case "Array Elements As Counters" :
                    {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
                        alertDialogBuilder.setCancelable(true);
                        alertDialogBuilder.setTitle("Coming Soon");
                        alertDialogBuilder.setMessage("From LearnWithUs Team");
                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();

                        break;
                    }
                    case "Multidimensional Arrays" :
                    {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
                        alertDialogBuilder.setCancelable(true);
                        alertDialogBuilder.setTitle("Coming Soon");
                        alertDialogBuilder.setMessage("From LearnWithUs Team");
                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();

                        break;
                    }
                    case "Public,Private and this" :
                    {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
                        alertDialogBuilder.setCancelable(true);
                        alertDialogBuilder.setTitle("Coming Soon");
                        alertDialogBuilder.setMessage("From LearnWithUs Team");
                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();

                        break;
                    }
                    case "Set And Get Methods" :
                    {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
                        alertDialogBuilder.setCancelable(true);
                        alertDialogBuilder.setTitle("Coming Soon");
                        alertDialogBuilder.setMessage("From LearnWithUs Team");
                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();

                        break;
                    }
                    case "Enumeration" :
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



        // loading video cover using Glide library



    }


    /**
     * Showing popup menu when tapping on 3 dots
     */



    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
