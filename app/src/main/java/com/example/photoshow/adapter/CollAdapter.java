package com.example.photoshow.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photoshow.LongClick.LongClickHandler;
import com.example.photoshow.R;
import com.example.photoshow.api.Api;
import com.example.photoshow.api.ApiConfig;
import com.example.photoshow.api.PhotoCallBack;
import com.example.photoshow.entity.Coll;

import java.util.HashMap;
import java.util.List;

public class CollAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static Context mContext;
    private List<Coll> datas;
    public static Integer uid;
    public CollAdapter(Context context,List<Coll> datas){
        this.mContext = context;
        this.datas = datas;
    }
    public void setUid(Integer uid){
        this.uid = uid;
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
        Integer id=coll.getId();
        vh.id=id;
        vh.collTvAuthor.setText(coll.getAuthor());
        vh.collTvCollect.setText(String.valueOf(coll.getCollcetCount()));
        vh.collTvDesc.setText(coll.getDescrition());
        vh.imgCollect.setImageResource(R.mipmap.shoucang);
        Glide.with(mContext).load(coll.getSrc()).into(vh.collIvPhoto);
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
         private ImageView imgCollect;
         private Integer id;
        public ViewHolder(@NonNull  View view) {
            super(view);
            collTvAuthor = view.findViewById(R.id.coll_author);
            collTvCollect = view.findViewById(R.id.coll_collect);
            collTvDesc = view.findViewById(R.id.coll_describe);
            collIvPhoto = view.findViewById(R.id.coll_photoshow);
            imgCollect=view.findViewById(R.id.collIma);
            collIvPhoto.setOnLongClickListener(new LongClickHandler());
            imgCollect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int collectNum=Integer.parseInt(collTvCollect.getText().toString());
                    collTvCollect.setText(String.valueOf(--collectNum));
                        imgCollect.setImageResource(R.mipmap.shoucang2);
                        update(uid,id,1,true);
                }
            });
        }
    }

    public static void update(int uid,int vid,int type,boolean flag){
        HashMap<String, Object> params = new HashMap<String, Object>();
        Api.config(ApiConfig.CHANGEDATA, params).myCollRequest2(mContext,uid,vid,flag,type,new PhotoCallBack() {
            @Override
            public void onSuccess(final String res) {
                Log.e("onSuccess", res);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });


    }
}
