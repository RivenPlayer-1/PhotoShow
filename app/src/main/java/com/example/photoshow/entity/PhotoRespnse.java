package com.example.photoshow.entity;

import java.io.Serializable;
import java.util.List;

public class PhotoRespnse implements Serializable {
    private String code;
    private List<Photo> photos;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Photo> getPhotos() {
            return photos;
        }

        public void setPhotos(List<Photo> photos) {
            this.photos = photos;
        }

        public PhotoRespnse(List<Photo> photos) {
            this.photos = photos;
        }

    @Override
    public String toString() {
        return "PhotoRespnse{" +
                "photos=" + photos +
                ", code='" + code + '\'' +
                '}';
    }

}
