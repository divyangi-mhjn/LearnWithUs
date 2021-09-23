package com.example.hp.learnwithus;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/26/2017.
 */@IgnoreExtraProperties
public class Editor {
    private String editorId;
    private String editorName;
    private String editoremail;


    public Editor(){

    }

    public Editor(String editorId, String editorName,String editoremail ) {
        this.editorId = editorId;
        this.editorName = editorName;
        this.editoremail=editoremail;

    }

    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public String getEditoremail() {
        return editoremail;
    }

    public void setEditoremail(String editoremail) {
        this.editoremail = editoremail;
    }
}