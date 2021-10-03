package com.example.photoshow.adapter;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoshow.R;
import com.example.photoshow.entity.PhotoEntity;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mcontext;
    private List<PhotoEntity> data;

    public PhotoAdapter(Context context, List<PhotoEntity> data){
        this.mcontext = context;
        this.data = data;
    }

    //每项数据的布局
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_photo_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        PhotoEntity photoEntity = data.get(position);
        vh.flPhoto.setTop(2);
    }

    //返回数据项数目
    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private FrameLayout flPhoto;
        public ViewHolder(@NonNull  View view) {
            super(view);
            flPhoto = view.findViewById(R.id.photo_container);
        }
    }
}
