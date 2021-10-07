package com.example.photoshow.entity;

import java.util.List;

public class CollectionResponse {
    private List<Coll> Collection;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Coll> getCollection() {
        return Collection;
    }

    public void setCollection(List<Coll> collection) {
        Collection = collection;
    }

    @Override
    public String toString() {
        return "CollectionResponse{" +
                "Collection=" + Collection +
                ", code='" + code + '\'' +
                '}';
    }
}
