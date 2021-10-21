package com.example.photoshow.entity;

import java.io.Serializable;

public class Coll implements Serializable {
    private Integer id;
    private String src;
    private String author;
    private Integer collcetCount;
    private String descrition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCollcetCount() {
        return collcetCount;
    }

    public void setCollcetCount(Integer collcetCount) {
        this.collcetCount = collcetCount;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public Coll(String src, String author, Integer collcetCount, String descrition) {
        this.src = src;
        this.author = author;
        this.collcetCount = collcetCount;
        this.descrition = descrition;
    }

    public Coll() {
    }

    @Override
    public String toString() {
        return "Coll{" +
                "id=" + id +
                ", src='" + src + '\'' +
                ", author='" + author + '\'' +
                ", collcetCount=" + collcetCount +
                ", descrition='" + descrition + '\'' +
                '}';
    }
}

