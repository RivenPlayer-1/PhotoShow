package com.example.photoshow.entity;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    /**
     * msg : success
     * code : 0
     * expire : 604800
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2IiwiaWF0IjoxNTkyNDg2OTQzLCJleHAiOjE1OTMwOTE3NDN9.f5sxyG60GyDlj0FcZEmPAADiLHX_pATrvicxbADqvRqYurYQC5s0KAjw5XgHS4gpk-qUSwWtcJpY_nJjYf_2Dw
     */
    private String userAccount;
    private String fault;
    private String token;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserId() {
        return userAccount;
    }

    public void setUserId(String userId) {
        this.userAccount = userId;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "fault='" + fault + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
