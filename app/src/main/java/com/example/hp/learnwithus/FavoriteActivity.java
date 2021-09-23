package com.example.hp.learnwithus;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.hp.learnwithus.R.id.listViewFavorite;
import static com.example.hp.learnwithus.R.id.tv;

public class FavoriteActivity extends AppCompatActivity {
    private Activity context;
    ArrayList<Favorite> favorite;
    DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser user;
     String email1;
    TextView text;
    ListView listViewFavorite;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseDatabase.getInstance().getReference("favorite");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         email1 = user.getEmail();
        text=(TextView)findViewById(R.id.text);
        TextView t=(TextView)findViewById(R.id.t1);
        listViewFavorite = (ListView) findViewById(R.id.listViewFavorite);
        favorite = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(FavoriteActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        listViewFavorite.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Favorite editor1 = favorite.get(i);
                showDeleteDialog(editor1.getId(), editor1.getName());
                return true;
            }
        });
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //attaching value event listener
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                  favorite.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    //getting artist
                    Favorite favorite1 = postSnapshot.getValue(Favorite.class);
                    //adding artist to the list
                    favorite.add(favorite1);
                }
                //creating adapter
                FavoriteList favoriteAdapter = new FavoriteList(FavoriteActivity.this, favorite);




                //attaching adapter to the listview
                listViewFavorite.setAdapter(favoriteAdapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //update editor


    //delete editor
    private boolean deleteArtist(String id) {
        //getting the specified artist reference


        //getting the tracks reference for the specified artist
        DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference("favorite").child(id);

        //removing all tracks
        drTracks.removeValue();
        Toast.makeText(getApplicationContext(), "My Favorites Deleted", Toast.LENGTH_LONG).show();

        return true;
    }

    //dialog box
    private void showDeleteDialog(final String id, String name) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_favorite, null);
        dialogBuilder.setView(dialogView);

        final TextView tv = (TextView) dialogView.findViewById(R.id.editTextName);


        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteArtist);

        dialogBuilder.setTitle(name);
        final AlertDialog b = dialogBuilder.create();
        b.show();





        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteArtist(id);
                b.dismiss();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }








}
