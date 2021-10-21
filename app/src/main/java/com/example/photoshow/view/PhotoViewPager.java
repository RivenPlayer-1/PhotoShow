package com.example.photoshow.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class PhotoViewPager extends ViewPager {

    public PhotoViewPager(@NonNull Context context) {
        super(context);
    }

    public PhotoViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public boolean OnInterceptTouchEvent(MotionEvent event){
        try {
            return super.onInterceptTouchEvent( event);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
    }
}
