package com.example.photoshow.LongClick;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.photoshow.utils.SaveUtils;

public class LongClickHandler implements View.OnLongClickListener{
    final String[] items = new String[] { "保存图片"};
    @Override
    public boolean onLongClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LogMessage.ShowLog("LongClickHandler");
                boolean bRet = SaveUtils.SaveJpg((ImageView) view);
                if (bRet) {
                    Toast.makeText(view.getContext(), "图片保存成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "图片保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
        return true;
    }
}
