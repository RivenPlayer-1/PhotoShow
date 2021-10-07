package com.example.photoshow.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.photoshow.R;
import com.example.photoshow.activity.LoginActivity;
import com.example.photoshow.adapter.PhotoAdapter;
import com.example.photoshow.api.Api;
import com.example.photoshow.api.ApiConfig;
import com.example.photoshow.api.PhotoCallBack;
import com.example.photoshow.entity.Photo;
import com.example.photoshow.entity.PhotoRespnse;
import com.example.photoshow.utils.StringUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private List<Photo> photoList = new ArrayList<>();

    private PhotoAdapter photoAdapter;
    List<Photo> datas;
    private SwipeRefreshLayout swipeRefreshLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_home,container,false);
            recyclerView = v.findViewById(R.id.recycle_view);
            return v;
//        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        photoAdapter = new PhotoAdapter(getActivity());
        //线性管理器
        recyclerView.setLayoutManager(linearLayoutManager);
        String token = getStringFromSp("token");
        if (!StringUtils.isEmpty(token)){
            HashMap<String,Object> params = new HashMap<>();
            params.put("token",token);
            Api.config(ApiConfig.PHOTO_LIST,params).getRequest(getActivity(),new PhotoCallBack() {
                @Override
                public void onSuccess(String res) {
                    PhotoRespnse respnse = new Gson().fromJson(res,PhotoRespnse.class);
                    datas = respnse.getPhotos();

//                    PhotoAdapter adapter = new PhotoAdapter(getActivity(),datas);

                    handler.sendEmptyMessage(0);
//                    recyclerView.setAdapter(photoAdapter);
//                    showToastSync(res);
                }
                @Override
                public void onFailure(Exception e) {
                }
            });
        }else {
            navigeteTo(LoginActivity.class);
        }
        getPhotoList();
    }


    private void getPhotoList(){

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    photoAdapter.setDatas(datas);
                    photoAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(photoAdapter);
                    break;
            }
        }
    };
}