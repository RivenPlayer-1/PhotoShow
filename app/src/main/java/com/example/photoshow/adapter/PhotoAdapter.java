package com.example.photoshow.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import com.example.photoshow.Helper.LongClickHandler;
import com.example.photoshow.R;
import com.example.photoshow.activity.HomeActivity;
import com.example.photoshow.api.Api;
import com.example.photoshow.api.ApiConfig;
import com.example.photoshow.api.PhotoCallBack;
import com.example.photoshow.api.TtitCallback;
import com.example.photoshow.entity.BaseResponse;
import com.example.photoshow.entity.LoginResponse;
import com.example.photoshow.entity.Photo;
import com.example.photoshow.entity.PhotoRespnse;
import com.example.photoshow.fragment.BaseFragment;
import com.google.gson.Gson;
import com.example.photoshow.activity.BaseActivity;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static Context mContext;
    public List<Photo> datas;

    public PhotoAdapter(Context context, List<Photo> datas){
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

    private static class ViewHolder extends RecyclerView.ViewHolder{
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
                        updateCount(1,id,1,flagcollect);
                    }else{
                        tvCollect.setText(String.valueOf(++collectNum));
                        imgCollect.setImageResource(R.mipmap.shoucang);
                        updateCount(1,id,1,flagcollect);
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
                        updateCount(1,id,2,flaglike);
                    }else{
                        tvDz.setText(String.valueOf(++likeNum));
                        imgDianzan.setImageResource(R.mipmap.dianzan_kuai);
                        updateCount(1,id,2,flaglike);
                    }
                    flaglike=!flaglike;
                }
            });

        }
    }

    private static void updateCount(int uid,int vid,int type,boolean flag){
        HashMap<String, Object> params = new HashMap<String, Object>();
        Api.config(ApiConfig.CHANGEDATA, params).myCollRequest2(mContext,uid,vid,flag,type,new PhotoCallBack() {
            @Override
            public void onSuccess(final String res) {
                Log.e("onSuccess", res);
                Gson gson = new Gson();
                PhotoRespnse photoRespnse = gson.fromJson(res, PhotoRespnse.class);
                System.out.println("000000000000000000000000000"+photoRespnse);
                /*if (photoRespnse.getCode().equals("0")) {

                }*/
            }

            @Override
            public void onFailure(Exception e) {

            }
        });


    }


}

