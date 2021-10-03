package com.example.photoshow.api;

public interface PhotoCallBack {
    void onSuccess(String res);

    void onFailure(Exception e);
}
