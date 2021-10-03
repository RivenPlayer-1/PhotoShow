package com.example.photoshow.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.photoshow.R;
import com.example.photoshow.adapter.PhotoAdapter;
import com.example.photoshow.entity.PhotoEntity;
import com.example.photoshow.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotoFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    public PhotoFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PhotoFragment newInstance(String param1, String param2) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
        View v = inflater.inflate(R.layout.fragment_my, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //选择垂直排列
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //为recyclerView指定布局管理器
        recyclerView.setLayoutManager(linearLayoutManager);
        try {
            getPhotoList();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getPhotoList() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constants.REQUEST_URL)
                .build();
        Response response = client.newCall(request).execute();
        String responseData = response.body().string();
        List<PhotoEntity> photoEntities = parseJSONWithGSON(responseData);
        PhotoAdapter photoAdapter = new PhotoAdapter(getActivity(),photoEntities);
        recyclerView.setAdapter(photoAdapter);
    }

    private List<PhotoEntity> parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        List<PhotoEntity> list = gson.fromJson(jsonData,new TypeToken<List<PhotoEntity>>(){}.getType());
        return list;
    }
}