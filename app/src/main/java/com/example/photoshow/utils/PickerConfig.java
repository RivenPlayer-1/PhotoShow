package com.example.photoshow.utils;

import com.example.photoshow.entity.PhotoItem;

import java.util.List;

public class PickerConfig {

    private int imageSelectCount = 1;
    private OnImagesSelectFinishedListener mSelectFinishedListener;
    private static PickerConfig sPickerConfig;

    private PickerConfig() {}

    // 单例模式
    public static PickerConfig getInstance() {
        if (sPickerConfig == null) {
            sPickerConfig = new PickerConfig();
        }
        return sPickerConfig;
    }
    //暴露接口
    public interface OnImagesSelectFinishedListener {
        void onSelectFinished(List<PhotoItem> photoItems);
    }
    
    public void setOnImagesSelectFinishedListener(OnImagesSelectFinishedListener selectFinishedListener) {
        this.mSelectFinishedListener = selectFinishedListener;
    }

    public OnImagesSelectFinishedListener getSelectFinishedListener() {
        return mSelectFinishedListener;
    }
    
    public int getImageSelectCount() {
        return imageSelectCount;
    }

    public void setImageSelectCount(int imageSelectCount) {
        this.imageSelectCount = imageSelectCount;
    }
}
