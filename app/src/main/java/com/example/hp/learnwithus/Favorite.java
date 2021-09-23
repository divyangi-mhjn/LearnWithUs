package com.example.hp.learnwithus;

import java.util.ArrayList;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/26/2017.
 */@IgnoreExtraProperties

public class Favorite {
    private String name;
    private String id;
    private String email;
    private String name2;
    private String name3;
    private  String name4;

    public Favorite(){

        //this constructor is required
    }

    public Favorite(String name,String name2,String name3,String name4,String id,String email) {
        this.name = name;
        this.name2 = name2;
        this.name3 = name3;
        this.name4 = name4;
        this.id=id;
        this.email=email;
}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }
}
