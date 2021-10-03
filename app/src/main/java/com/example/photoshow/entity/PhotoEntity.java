package com.example.photoshow.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhotoEntity implements Serializable {
    private int id;

    @SerializedName("html")
    private String photoSrc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoSrc() {
        return photoSrc;
    }

    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }

    @Override
    public String toString() {
        return "PhotoEntity{" +
                "id=" + id +
                ", photoSrc='" + photoSrc + '\'' +
                '}';
    }
}
