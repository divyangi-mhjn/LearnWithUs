package com.example.hp.learnwithus;
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

/**
 * Created by Belal on 2/26/2017.
 */

public class EditorList extends ArrayAdapter<Editor> {
    private Activity context;
    List<Editor> editors;
    FirebaseUser user;
    String email1;
    int flag;

    public EditorList(Activity context, List<Editor> editors) {
        super(context, R.layout.layout_editor_list, editors);
        this.context = context;
        this.editors = editors;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_editor_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.rty);


        Editor editor = editors.get(position);
        user = FirebaseAuth.getInstance().getCurrentUser();
        email1 = user.getEmail();



        if(email1.equals(editor.getEditoremail())){
            textViewName.setVisibility(View.VISIBLE);

            textViewName.setText(editor.getEditorName());
            flag=1;

        }
        else{
            textViewName.setVisibility(View.GONE);

            position++;
            flag=0;
        }



        return listViewItem;
    }
}
