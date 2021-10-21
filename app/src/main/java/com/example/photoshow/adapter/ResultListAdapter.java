package com.example.photoshow.adapter;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photoshow.R;
import com.example.photoshow.entity.PhotoItem;
import com.example.photoshow.utils.ImageUtil;
import com.example.photoshow.utils.SizeUtil;

import java.util.ArrayList;
import java.util.List;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.InnerHolder> {

    public static final String TAG = "ResultListAdapter";
    //上下文
    private Context context;

    private List<PhotoItem> mPhotoItems = new ArrayList<>();
    public static String[] paths;
    private int spanCount;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_upload_photo, viewGroup, false);
        final  InnerHolder innerHolder = new InnerHolder(itemView);
        return innerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder innerHolder,final int i) {
        View itemView = innerHolder.itemView;
        // 因为RecyclerView复用的问题，当条目个数比较上一次小的时候，是不会走onCreateViewHolder的，但肯定会走
        // onBindViewHolder,所以把屏幕适配的代码挪到这里来
        final Point point = SizeUtil.getScreenSize(itemView.getContext());
        Log.d(TAG, "screen width --> "+point.x);
        Log.d(TAG, "screen height --> "+point.y);
        Log.d(TAG, "span count --> " + spanCount);
        // 让单个条目宽高一致
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                point.x/spanCount, point.x/spanCount);
        itemView.setLayoutParams(layoutParams);
        if(innerHolder.ivDelete != null){
            innerHolder.ivDelete.setTag(i);
            innerHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();

                    mPhotoItems.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        ImageView imageItem = itemView.findViewById(R.id.iv_images_upload);

        // 1. 实现Glide图片加载框架加载图片
        Glide.with(imageItem.getContext()).load(mPhotoItems.get(i).getPath()).into(imageItem);

    }
    public String[] getpath(){
        String[] filePaths = ImageUtil.getPaths(mPhotoItems);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+filePaths.toString());
        return filePaths;
    }

    @Override
    public int getItemCount() {
        return mPhotoItems.size();
    }

    public void setData(List<PhotoItem> photoItems, int spanCount, Context context) {
        this.context = context;
        this.spanCount = spanCount;
        mPhotoItems.clear();
        mPhotoItems.addAll(photoItems);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        ImageView ivDelete;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ivDelete = itemView.findViewById(R.id.bt_remove);
        }
    }
}