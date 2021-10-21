package com.example.photoshow.utils;

import com.example.photoshow.entity.PhotoItem;

import java.util.List;

public class ImageUtil {

    public static String[] getPaths(List<PhotoItem> photoItemList){
        String[] filePaths = new String[photoItemList.size()];
        for (int i=0;i<photoItemList.size();i++) {
            filePaths[i] = photoItemList.get(i).getPath();
        }
        return filePaths;
    }
}
