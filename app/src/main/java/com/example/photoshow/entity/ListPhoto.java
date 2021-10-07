package com.example.photoshow.entity;

import java.io.Serializable;
import java.util.List;

public class ListPhoto {

        private List<Photo> photos;


        public List<Photo> getPhotos() {
            return photos;
        }

        public void setPhotos(List<Photo> photos) {
            this.photos = photos;
        }

        public ListPhoto(List<Photo> photos) {
            this.photos = photos;
        }

        @Override
        public String toString() {
            return "PhotoRespnse{" +
                    "photos=" + photos +
                    '}';
        }


}
