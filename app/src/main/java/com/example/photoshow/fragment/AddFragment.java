package com.example.photoshow.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photoshow.R;
import com.example.photoshow.activity.ImagePickerActivity;
import com.example.photoshow.adapter.ImageListAdapter;
import com.example.photoshow.adapter.ResultListAdapter;
import com.example.photoshow.entity.PhotoItem;
import com.example.photoshow.utils.PickerConfig;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment implements PickerConfig.OnImagesSelectFinishedListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String MEDIA_TYPE_PNG = "multipart/form-data";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<PhotoItem> MyphotoItems;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static AddFragment newInstance() {
//        AddFragment fragment = new AddFragment();
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    public static final String TAG = "UploadFragment";
    private static final int REQUEST_READ_SD = 1;
    public static final int MAX_IMAGES = 9;// 最大图片数量
    private static final int SPAN_COUNTS = 3;
    private RecyclerView mRecyclerView;
    private ResultListAdapter mAdapter;
    private Button btn_upload;
    private Button btn_upload_confirm;
    private TextInputEditText tidt_essay;
    private String paths[];
    ImageListAdapter getpaths=new ImageListAdapter();

    //  上下文
    Context context;
    private View rootView;

    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        context = getActivity();
        rootView = inflater.inflate(R.layout.fragment_add,
                container,false);
        checkPermission();
        initView();
        initConfig();

        return rootView;
    }



    private void initView() {
        tidt_essay = rootView.findViewById(R.id.et_essay);
        btn_upload = rootView.findViewById(R.id.btn_upload);
        btn_upload_confirm = rootView.findViewById(R.id.btn_upload_confirm);
        mRecyclerView = rootView.findViewById(R.id.rv_upload);
        mAdapter = new ResultListAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initConfig() {
        PickerConfig pickerConfig = PickerConfig.getInstance();
        pickerConfig.setImageSelectCount(MAX_IMAGES);
        pickerConfig.setOnImagesSelectFinishedListener(this);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImages(v);
            }
        });
        btn_upload_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
    }

    private void checkPermission() {
        int readExSD = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            readExSD = context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (readExSD != PackageManager.PERMISSION_GRANTED) {
            // 请求获取权限
            Log.d(TAG, "request permission...");
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_SD);
        } else {
            Log.d(TAG, "permission has granted");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_SD && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "request permission success");
        } else {
            Log.d(TAG, "request permission fail");
        }
    }

    public void pickImages(View view) {
        // 这里回传数据不采用onActivityResult方法，使用该方法传递的数据是有限的。
        // 通过设置接口，通过接口回传数据。
        startActivity(new Intent(context, ImagePickerActivity.class));

    }

    @Override
    public void onSelectFinished(final List<PhotoItem> photoItems) {
        // 所选择的数据回传到这
        /*for (PhotoItem photoItem : photoItems) {
            Log.d(TAG, "onSelectFinished:" + photoItem);
        }*/
        // 根据图片数量设置布局管理器 如果选择一张，则显示一张的尺寸，大于三种则使用
        // recyclerView+grid列表展示
        MyphotoItems = photoItems;
        System.out.println(photoItems.size());

        GridLayoutManager layoutManager;
        if (photoItems.size() < SPAN_COUNTS) {
            layoutManager = new GridLayoutManager(context, photoItems.size());
        } else
            layoutManager = new GridLayoutManager(context, SPAN_COUNTS);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter.setData(photoItems, Math.min(photoItems.size(), SPAN_COUNTS),context);
    }
    public void upload(){
        ImagePickerActivity I=new ImagePickerActivity();

//        int cacheSize = 10 * 1024 * 1024;
//        //设置超时时间及缓存
//        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .writeTimeout(20, TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS);
//
//
//        OkHttpClient mOkHttpClient=builder.build();
//
//        MultipartBody.Builder mbody=new MultipartBody.Builder().setType(MultipartBody.FORM);
//
        List<File> fileList=new ArrayList<File>();
        paths= mAdapter.getpath();
        for (String path:paths){
            fileList.add(new File(path));
        }
        System.out.println(fileList);

//        for(File file:fileList){
//            if(file.exists()){
//                Log.i("imageName:",file.getName());//经过测试，此处的名称不能相同，如果相同，只能保存最后一个图片，不知道那些同名的大神是怎么成功保存图片的。
//                mbody.addFormDataPart("image"+i,file.getName(),RequestBody.create());
//                i++;
//            }
//        }
//
//        RequestBody requestBody =mbody.build();
//        Request request = new Request.Builder()
//                .header("Authorization", "Client-ID " + "...")
//                .url("http://412691ul59.qicp.vip/user/upLoad")
//                .post(requestBody)
//                .build();
//
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.i("InfoMSG", response.body().string());
//            }
//        });

//        OkHttpClient okHttpClient=new OkHttpClient();
//            RequestBody body=new MultipartBody.Builder().setType(MultipartBody.FORM)
////                    ,RequestBody.create(MediaType.parse("multipart/form-data"),file)
//                    .addFormDataPart("id","wzn").build();
//            try {
//                ResponseBody responseBody=okHttpClient.newCall(
//                        new Request.Builder().post(body).url("http://412691ul59.qicp.vip/user/upLoad").build()).execute().body();
//            }catch (IOException e){}
        OkHttpClient client = new OkHttpClient();
//        String imgPath = getExternalCacheDir().getPath() + "/output_image.jpg";
        MediaType PNG=MediaType.parse("image/*");
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File file:fileList){
            if(file.exists()){
                builder.setType(MultipartBody.FORM).addFormDataPart("files", file.getName(),RequestBody.create(PNG,file));
                System.out.println(file.getName());
            }
        }
        RequestBody requestBody = builder.build();
//        int i=0;

        //Request.Builder reqBuilder = new Request.Builder();
        Request request = new Request.Builder()
                .url("http://412691ul59.qicp.vip/user/fileUpload?userAccount=1&desc=1")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("InfoMSG", response.body().string());
            }
        });

    }
}