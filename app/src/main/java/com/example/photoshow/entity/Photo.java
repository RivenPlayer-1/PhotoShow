package com.example.photoshow.entity;

public class Photo {
    private Integer id;
    private String phoUrl;
    private Integer phoCol;

    public Photo(Integer id, String phoUr, Integer phoCol) {
        this.id = id;
        this.phoUrl = phoUr;
        this.phoCol = phoCol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoUr() {
        return phoUrl;
    }

    public void setPhoUr(String phoUr) {
        this.phoUrl = phoUr;
    }

    public Integer getPhoCol() {
        return phoCol;
    }

    public void setPhoCol(Integer phoCol) {
        this.phoCol = phoCol;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", phoUr='" + phoUrl + '\'' +
                ", phoCol=" + phoCol +
                '}';
    }
}
