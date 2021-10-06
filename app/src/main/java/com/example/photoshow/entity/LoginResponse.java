package com.example.photoshow.entity;

public class LoginResponse {

    /**
     * msg : success
     * code : 0
     * expire : 604800
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2IiwiaWF0IjoxNTkyNDg2OTQzLCJleHAiOjE1OTMwOTE3NDN9.f5sxyG60GyDlj0FcZEmPAADiLHX_pATrvicxbADqvRqYurYQC5s0KAjw5XgHS4gpk-qUSwWtcJpY_nJjYf_2Dw
     */

    private String fault;
    private String token;

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
}
