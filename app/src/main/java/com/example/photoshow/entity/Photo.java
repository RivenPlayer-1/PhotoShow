package com.example.photoshow.entity;

import android.net.Uri;

public class Photo {
    private Integer id;
    private String author;
    private Integer dzCount;
    private Integer collcetCount;
    private String src;
    private String descrition;
    private String flaglike;
    private String flagcollect;

    public boolean isFlaglike() {
        if (getFlaglike().equals("false")){
            return false;
        }else {
            return true;
        }
    }

    public String getFlaglike() {
        return flaglike;
    }

    public void setFlaglike(String flaglike) {
        this.flaglike = flaglike;
    }
    public boolean isFlagcollect() {
        if (flagcollect.equals("false")){
            return false;
        }else {
            return true;
        }
    }
    public String getFlagcollect() {
        return flagcollect;
    }

    public void setFlagcollect(String flagcollect) {
        this.flagcollect = flagcollect;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getDzCount() {
        return dzCount;
    }

    public void setDzCount(Integer dzCount) {
        this.dzCount = dzCount;
    }

    public Integer getCollcetCount() {
        return collcetCount;
    }

    public void setCollcetCount(Integer collcetCount) {
        this.collcetCount = collcetCount;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }


    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", dzCount=" + dzCount +
                ", collcetCount=" + collcetCount +
                ", src='" + src + '\'' +
                ", descrition='" + descrition + '\'' +
                ", flaglike='" + flaglike + '\'' +
                ", flagcollect='" + flagcollect + '\'' +
                '}';
    }
}
