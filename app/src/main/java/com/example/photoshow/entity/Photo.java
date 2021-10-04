package com.example.photoshow.entity;

public class Photo {
    private String name;
    private int iamgeId;

    public Photo(String name, int iamgeId) {
        this.name = name;
        this.iamgeId = iamgeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIamgeId() {
        return iamgeId;
    }

    public void setIamgeId(int iamgeId) {
        this.iamgeId = iamgeId;}
}
