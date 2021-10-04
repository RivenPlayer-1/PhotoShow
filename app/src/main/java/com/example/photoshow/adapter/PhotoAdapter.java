package com.example.photoshow.adapter;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import com.example.photoshow.R;
import com.example.photoshow.entity.Photo;
import com.example.photoshow.entity.PhotoEntity;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private Context mContext;

    private List<Photo> mPhotoList;

    public PhotoAdapter(FragmentActivity activity, List<PhotoEntity> photoEntities) {
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView photoImage;
        TextView photoName;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            photoImage = (ImageView)  itemView.findViewById(R.id.photo_image);
            photoName = (TextView) itemView.findViewById(R.id.photo_name);
        }
    }

    public PhotoAdapter(List<Photo> photoList){
        mPhotoList = photoList;
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_photo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  PhotoAdapter.ViewHolder holder, int position) {
        Photo photo = mPhotoList.get(position);
        holder.photoName.setText(photo.getName());
        Glide.with(mContext).load(photo.getIamgeId()).into(holder.photoImage);
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }
}

