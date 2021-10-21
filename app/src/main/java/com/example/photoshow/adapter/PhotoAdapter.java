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
import com.example.photoshow.entity.Photo;

import java.util.HashMap;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static Context mContext;
    public List<Photo> datas;
    public Integer uid;

    public PhotoAdapter(Context context, List<Photo> datas){
        this.mContext = context;
        this.datas = datas;
    }

    public void setUid(Integer uid){
        this.uid = uid;
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
        boolean flaglike=photo.isFlaglike();
        boolean flagcollect=photo.isFlagcollect();
        Integer id=photo.getId();
        if(flaglike){
            //vh.tvDz.setTextColor(Color.parseColor("#E21918"));
            vh.imgDianzan.setImageResource(R.mipmap.dianzan_kuai);
        }
        if(flagcollect){
            //vh.tvCollect.setTextColor(Color.parseColor("#E21918"));
            vh.imgCollect.setImageResource(R.mipmap.shoucang);
        }
        vh.id=id;
        vh.flaglike=flaglike;
        vh.flagcollect=flagcollect;
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

    private  class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        private TextView tvAuthor;
        private TextView tvDesc;
        private TextView tvDz;
        private TextView tvCollect;
        private ImageView ivPhoto;
        private ImageView imgCollect;
        private ImageView imgDianzan;
        private boolean flaglike;
        private boolean flagcollect;
        private Integer id;


        public ViewHolder(@NonNull  View view) {
            super(view);
            tvAuthor = view.findViewById(R.id.author);
            tvDz = view.findViewById(R.id.dz);
            tvCollect = view.findViewById(R.id.collect);
            tvDesc = view.findViewById(R.id.describe);
            ivPhoto = view.findViewById(R.id.photoshow);
            ivPhoto.setOnLongClickListener(new LongClickHandler());
            imgCollect=view.findViewById(R.id.img_collect);
            imgDianzan=view.findViewById(R.id.img_like);

            imgCollect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int collectNum=Integer.parseInt(tvCollect.getText().toString());
                    if(flagcollect){
                        tvCollect.setText(String.valueOf(--collectNum));
                        imgCollect.setImageResource(R.mipmap.shoucang2);
                        updateCount(uid,id,1,flagcollect);
                    }else{
                        tvCollect.setText(String.valueOf(++collectNum));
                        imgCollect.setImageResource(R.mipmap.shoucang);
                        updateCount(uid,id,1,flagcollect);
                    }
                    flagcollect=!flagcollect;
                }
            });

            imgDianzan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int likeNum=Integer.parseInt(tvDz.getText().toString());
                    if(flaglike){
                        tvDz.setText(String.valueOf(--likeNum));
                        imgDianzan.setImageResource(R.mipmap.dianzan);
                        updateCount(uid,id,2,flaglike);
                    }else{
                        tvDz.setText(String.valueOf(++likeNum));
                        imgDianzan.setImageResource(R.mipmap.dianzan_kuai);
                        updateCount(uid,id,2,flaglike);
                    }
                    flaglike=!flaglike;
                }
            });

        }
    }

    public static void updateCount(int uid,int vid,int type,boolean flag){
        HashMap<String, Object> params = new HashMap<String, Object>();
        Api.config(ApiConfig.CHANGEDATA, params).myCollRequest2(mContext,uid,vid,flag,type,new PhotoCallBack() {
            @Override
            public void onSuccess(final String res) {
                Log.e("onSuccess", res);
//
            }

            @Override
            public void onFailure(Exception e) {

            }
        });


    }


}

