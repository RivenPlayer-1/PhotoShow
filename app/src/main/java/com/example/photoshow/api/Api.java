package com.example.photoshow.api;

import android.util.Log;

import com.example.photoshow.entity.PhotoEntity;
import com.example.photoshow.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import javax.security.auth.callback.Callback;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api {
    private OkHttpClient client = new OkHttpClient();
    private PhotoCallBack callBack;
    public void getRequest(final PhotoCallBack callback) throws IOException {
        String url = Constants.REQUEST_URL;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Failure",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                callback.onSuccess(result);
            }
        });
//        Response response = client.newCall(request).execute();
//        String respnseData = response.body().string();
//        paresJSONWithGSON(respnseData);
    }

    private void paresJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        List<PhotoEntity> list = gson.fromJson(jsonData,new TypeToken<List<PhotoEntity>>(){}.getType());
    }
}
