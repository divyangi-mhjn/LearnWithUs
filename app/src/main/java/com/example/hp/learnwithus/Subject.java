package com.example.hp.learnwithus;

public class Subject {
    private String name1;
    private int thumbnail1;

    public Subject() {
    }

    public Subject(String name1 , int thumbnail1 ) {
        this.name1 = name1;
        this.thumbnail1 = thumbnail1;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }
    public  int getThumbnail1()
    {
        return thumbnail1;
    }
    public void setThumbnail1(int thumbnail1) {
        this.thumbnail1 = thumbnail1;
    }



}