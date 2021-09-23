package com.example.hp.learnwithus;

/**
 * Created by HP on 29-10-2017.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class FavoriteList extends ArrayAdapter<Favorite> {
    private Activity context;
    List<Favorite> favorite1;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser user;
    String email1;
    int flag;


    public  FavoriteList(Activity context, List<Favorite> favorite1) {
        super(context, R.layout.activity_fav, favorite1);
        this.context = context;
        this.favorite1 = favorite1;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_fav, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.text);
        TextView textViewName2 = (TextView) listViewItem.findViewById(R.id.text2);
        TextView textViewName3 = (TextView) listViewItem.findViewById(R.id.text3);
        TextView textViewName4 = (TextView) listViewItem.findViewById(R.id.text4);



        Favorite favorite = favorite1.get(position);
        user = FirebaseAuth.getInstance().getCurrentUser();
        email1 = user.getEmail();

        if(email1.equals(favorite.getEmail())){
            textViewName.setVisibility(View.VISIBLE);
            textViewName2.setVisibility(View.VISIBLE);
            textViewName3.setVisibility(View.VISIBLE);
            textViewName4.setVisibility(View.VISIBLE);

            textViewName.setText(favorite.getName());
            textViewName2.setText(favorite.getName2());
            textViewName3.setText(favorite.getName3());
            textViewName4.setText(favorite.getName4());


            }
        else{
            textViewName.setVisibility(View.GONE);
            textViewName2.setVisibility(View.GONE);
            textViewName3.setVisibility(View.GONE);
            textViewName4.setVisibility(View.GONE);

            position++;

        }


        return listViewItem;
    }
}
