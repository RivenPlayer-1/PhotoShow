package com.example.photoshow.utils;

public class StringUtil {
    public static boolean isEmpry(String username,String password) {
        if (username == null || username.length() <= 0) {
            return true;
        }else {
            return false;
        }
    }
}
