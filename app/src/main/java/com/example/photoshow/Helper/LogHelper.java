package com.example.photoshow.Helper;

import android.util.Log;

import java.io.IOException;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class LogHelper {
    public static void ShowLog(String string){
        Log.v(TAG,string);
    }
    public static void ShowException(IOException ex){
        ex.printStackTrace();
    }
}