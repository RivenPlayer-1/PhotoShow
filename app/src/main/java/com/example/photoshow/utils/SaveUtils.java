package com.example.photoshow.utils;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.example.photoshow.LongClick.LogMessage;

import java.io.IOException;
import java.io.OutputStream;

public class SaveUtils {
    public static boolean SaveJpg(ImageView view) {
        try{
            Drawable drawable = view.getDrawable();
            if (drawable == null) {
                return false;
            }
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            Uri dataUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            Uri fileUri = view.getContext().getContentResolver().insert(dataUri, values);
            if(fileUri == null){
                LogMessage.ShowLog("fileUri == null");
                return false;
            }
            OutputStream outStream = view.getContext().getContentResolver().openOutputStream(fileUri);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
            view.getContext().sendBroadcast(new Intent("com.android.camera.NEW_PICTURE", fileUri));
            LogMessage.ShowLog("保存图片到相册完毕...");
            return true;

        }
        catch (IOException ex) {
            LogMessage.ShowException(ex);
        }
        return false;
    }

}
