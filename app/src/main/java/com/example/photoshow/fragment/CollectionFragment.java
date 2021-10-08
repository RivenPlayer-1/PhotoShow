package com.example.photoshow.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.photoshow.R;
import com.example.photoshow.activity.LoginActivity;
import com.example.photoshow.adapter.CollAdapter;
import com.example.photoshow.adapter.PhotoAdapter;
import com.example.photoshow.api.Api;
import com.example.photoshow.api.ApiConfig;
import com.example.photoshow.api.PhotoCallBack;
import com.example.photoshow.entity.Coll;
import com.example.photoshow.entity.CollectionResponse;
import com.example.photoshow.entity.PhotoRespnse;
import com.example.photoshow.utils.StringUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private CollAdapter collAdapter;
    private List<Coll> datas;

    public CollectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment CollectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CollectionFragment newInstance() {
        CollectionFragment fragment = new CollectionFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_collection,container,false);
        recyclerView = v.findViewById(R.id.recycle_view2);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        collAdapter = new CollAdapter(getActivity());
        //线性管理器
        recyclerView.setLayoutManager(linearLayoutManager);
        String token = getStringFromSp("token");
        if (!StringUtils.isEmpty(token)){
            HashMap<String,Object> params = new HashMap<>();
            params.put("token",token);
            Api.config(ApiConfig.COLLECT,params).myCollRequest(getActivity(),new PhotoCallBack() {
                @Override
                public void onSuccess(String res) {
//                    System.out.println("CCCCCCCCCCCC+++++++++______"+res);
                    CollectionResponse respnse = new Gson().fromJson(res, CollectionResponse.class);
                    datas = respnse.getCollection();
//                    System.out.println("dddddddddddddddd________"+datas);
//                    PhotoAdapter adapter = new PhotoAdapter(getActivity(),datas);

                    handler.sendEmptyMessage(0);
//                    recyclerView.setAdapter(photoAdapter);
//                    showToastSync(res);
                }
                @Override
                public void onFailure(Exception e) {
                    System.out.println("dddddddddddd+++++++++______");
                }
            });
        }else {
            navigeteTo(LoginActivity.class);
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    collAdapter.setDatas(datas);
                    collAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(collAdapter);
                    break;
            }
        }
    };
}