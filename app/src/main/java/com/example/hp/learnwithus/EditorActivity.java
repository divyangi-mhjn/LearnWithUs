package com.example.hp.learnwithus;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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

public class EditorActivity extends AppCompatActivity {

    //we will use these constants later to pass the artist name and id to another activity
    public static final String ARTIST_NAME = "com.example.hp.learnwithus.artistname";
    public static final String ARTIST_ID = "com.example.hp.learnwithus.artistid";

    //view objects
    EditText editTextName;

    Button buttonAddArtist;
    ListView listViewArtists;

    String email;


    //a list to store all the artist from firebase database
    List<Editor> editor;

    //our database reference object
    DatabaseReference databaseArtists;
    FirebaseUser user;

    @Override
    protected void onStart() {
        super.onStart();

        //attaching value event listener
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                editor.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Editor editor1 = postSnapshot.getValue(Editor.class);
                    //adding artist to the list
                    editor.add(editor1);
                }

                //creating adapter
                EditorList artistAdapter = new EditorList(EditorActivity.this, editor);
                //attaching adapter to the listview
                listViewArtists.setAdapter(artistAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();

        //getting the reference of editors node

        databaseArtists = FirebaseDatabase.getInstance().getReference("editor");


        //getting views
        editTextName = (EditText) findViewById(R.id.editTextName);

        listViewArtists = (ListView) findViewById(R.id.listViewArtists);

        buttonAddArtist = (Button) findViewById(R.id.buttonAddArtist);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(EditorActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });


        //list to store editors
        editor = new ArrayList<>();


        //adding an onclicklistener to button
        buttonAddArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                //the method is defined below
                //this method is actually performing the write operation
                addArtist();
            }
        });

        // when list item clicked
        listViewArtists.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Editor editor1 = editor.get(i);
                showUpdateDeleteDialog(editor1.getEditorId(), editor1.getEditorName());
                return true;
            }
        });

    }
    /*
    * This method is saving a new artist to the
    * Firebase Realtime Database
    * */
    private void addArtist() {
        //getting the values to save
        String name = editTextName.getText().toString().trim();
        String el=email.toString() ;


        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Editor
            String id = databaseArtists.push().getKey();

            //creating an Editor Object
            Editor editor = new Editor(id, name,el);

            //Saving the Editor
            databaseArtists.child(id).setValue(editor);

            //setting edittext to blank again
            editTextName.setText("");

            //displaying a success toast
            Toast.makeText(this, "Editor added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter your notes", Toast.LENGTH_LONG).show();
        }
    }

    //update editor
    private boolean updateArtist(String id, String name, String email) {
        String el=email.toString() ;
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("editor").child(id);

        //updating artist
        Editor artist = new Editor(id, name, el);
        dR.setValue(artist);
        Toast.makeText(getApplicationContext(), "Notes Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    //delete editor
    private boolean deleteArtist(String id) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("artists").child(id);

        //removing artist
        dR.removeValue();

        //getting the tracks reference for the specified artist
        DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference("editor").child(id);

        //removing all tracks
        drTracks.removeValue();
        Toast.makeText(getApplicationContext(), "My Notes Deleted", Toast.LENGTH_LONG).show();

        return true;
    }

    //dialog box
    private void showUpdateDeleteDialog(final String editorId, String editorName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);

        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateArtist);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteArtist);

        dialogBuilder.setTitle(editorName);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();

                if (!TextUtils.isEmpty(name)) {
                    updateArtist(editorId, name,email);
                    b.dismiss();
                }
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteArtist(editorId);
                b.dismiss();
            }
        });
    }
    @Override
    public void onBackPressed() {
       finish();
    }



}