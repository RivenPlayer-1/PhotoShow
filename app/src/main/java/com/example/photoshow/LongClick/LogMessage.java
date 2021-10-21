package com.example.photoshow.LongClick;

import android.util.Log;

import java.io.IOException;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class LogMessage {
    public static void ShowLog(String string){
        Log.v(TAG,string);
    }
    public static void ShowException(IOException ex){
        ex.printStackTrace();
    }
}