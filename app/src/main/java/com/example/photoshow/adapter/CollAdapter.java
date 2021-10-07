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
import com.example.photoshow.entity.Coll;

import java.util.List;

public class CollAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Coll> datas;
    public CollAdapter(Context context,List<Coll> datas){
        this.mContext = context;
        this.datas = datas;
    }

    public CollAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setDatas(List<Coll> datas){
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //将R.layout.item_photo_layout里的组件封装成一个对象
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_collection,parent,false);
        CollAdapter.ViewHolder viewHolder = new CollAdapter.ViewHolder(view);
        return viewHolder;
    }

    //给布局组件赋值
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CollAdapter.ViewHolder vh = (CollAdapter.ViewHolder) holder;
        //获得实体对象
        System.out.println("============================"+datas);
        Coll coll = datas.get(position);
        Glide.with(mContext).load(coll.getSrc()).into(vh.collIvPhoto);
        vh.collTvAuthor.setText(coll.getAuthor());
        vh.collTvCollect.setText(String.valueOf(coll.getCollcetCount()));
        vh.collTvDesc.setText(coll.getDescrition());

//        vh.tvAuthor.setText(photo.getAuthor());
//        vh.tvDesc.setText(photo.getDescrition());
//        vh.tvDz.setText(String.valueOf(photo.getDzCount()));
//        vh.tvCollect.setText(String.valueOf(photo.getCollcetCount()));
//        Glide.with(mContext).load(photo.getSrc()).into(vh.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

     private static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        private TextView collTvAuthor;
        private TextView collTvDesc;
        private TextView collTvCollect;
        private ImageView collIvPhoto;
        public ViewHolder(@NonNull  View view) {
            super(view);
            collTvAuthor = view.findViewById(R.id.coll_author);
            collTvCollect = view.findViewById(R.id.coll_collect);
            collTvDesc = view.findViewById(R.id.coll_describe);
            collIvPhoto = view.findViewById(R.id.coll_photoshow);
        }
    }
}
