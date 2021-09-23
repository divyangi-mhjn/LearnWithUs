package com.example.hp.learnwithus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>  {

    private Context mContext;
    private List<Home> homeList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        RelativeLayout relativelayout;



        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);

            relativelayout =(RelativeLayout) itemView.findViewById(R.id.rl);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

        }


    }



    public HomeAdapter(Context mContext, List<Home> homeList) {
        this.mContext = mContext;
        this.homeList = homeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card1, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Home home = homeList.get(position);
        holder.title.setText(home.getName());
        Glide.with(mContext).load(home.getThumbnail()).into(holder.thumbnail);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home.getName();

                switch (home.getName()){
                    case "Learn":
                    {
                        Intent intent=new Intent(mContext,Tut1Activity.class);
                        mContext.startActivity(intent);
                        break;
                    }

                    case "Challenges":
                    {
                        Intent intent=new Intent(mContext,QuizMain.class);
                        mContext.startActivity(intent);
                        break;
                    }
                    case "My Notes":
                    {
                        Intent intent=new Intent(mContext,EditorActivity.class);
                        mContext.startActivity(intent);
                        break;
                    }

                    case "Videos" :
                    {
                        Intent intent=new Intent(mContext,VideoMain.class);
                        mContext.startActivity(intent);
                        break;
                    }


                }


            }
        });



        // loading home cover using Glide library



    }

    /**
     * Showing popup menu when tapping on 3 dots
     */



    @Override
    public int getItemCount() {
        return homeList.size();
    }
}