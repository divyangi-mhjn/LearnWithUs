package com.example.hp.learnwithus;

public class Quiz {
    private String name2;
    private int thumbnail2;

    public Quiz() {
    }

    public Quiz(String name2 , int thumbnail2 ) {
        this.name2 = name2;
        this.thumbnail2 = thumbnail2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
    public  int getThumbnail2()
    {
        return thumbnail2;
    }
    public void setThumbnail2(int thumbnail2) {
        this.thumbnail2 = thumbnail2;
    }



}