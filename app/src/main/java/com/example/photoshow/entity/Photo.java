package com.example.photoshow.entity;

public class Photo {
    private Integer id;
    private String author;
    private Integer dzCount;
    private Integer collcetCount;
    private Integer src;
    private String descrition;
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

    public Integer getSrc() {
        return src;
    }

    public void setSrc(Integer src) {
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
                ", dzCount='" + dzCount + '\'' +
                ", collcetCount='" + collcetCount + '\'' +
                ", src='" + src + '\'' +
                ", descrition='" + descrition + '\'' +
                '}';
    }
}
