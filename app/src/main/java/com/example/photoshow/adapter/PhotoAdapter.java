package com.example.photoshow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import com.example.photoshow.R;
import com.example.photoshow.entity.Photo;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Photo> datas;
    public PhotoAdapter(Context context,List<Photo> datas){
        this.mContext = context;
        this.datas = datas;
    }

    public PhotoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setDatas(List<Photo> datas){
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //将R.layout.item_photo_layout里的组件封装成一个对象
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_photo_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //给布局组件赋值
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        //获得实体对象
        Photo photo = datas.get(position);
        vh.tvAuthor.setText(photo.getAuthor());
        vh.tvDesc.setText(photo.getDescrition());
        vh.tvDz.setText(String.valueOf(photo.getDzCount()));
        vh.tvCollect.setText(String.valueOf(photo.getCollcetCount()));
        Glide.with(mContext).load(photo.getSrc()).into(vh.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        private TextView tvAuthor;
        private TextView tvDesc;
        private TextView tvDz;
        private TextView tvCollect;
        private ImageView ivPhoto;
        public ViewHolder(@NonNull  View view) {
            super(view);
            tvAuthor = view.findViewById(R.id.author);
            tvDz = view.findViewById(R.id.dz);
            tvCollect = view.findViewById(R.id.collect);
            tvDesc = view.findViewById(R.id.describe);
            ivPhoto = view.findViewById(R.id.photoshow);
        }
    }
}

