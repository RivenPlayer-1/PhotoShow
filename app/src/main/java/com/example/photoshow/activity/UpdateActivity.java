package com.example.photoshow.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.photoshow.R;
import com.example.photoshow.api.Api;
import com.example.photoshow.api.ApiConfig;
import com.example.photoshow.api.PhotoCallBack;
import com.example.photoshow.api.TtitCallback;
import com.example.photoshow.utils.StringUtils;

import java.util.HashMap;

public class UpdateActivity extends BaseActivity{
    private EditText save1;
    private EditText save2;
    private Button bt_save;
    public Integer uid1=Integer.valueOf(LoginActivity.uid);
    @Override
    protected int initLayout() {
        return R.layout.activity_update;
    }

    @Override
    protected void initView() {
        save1=findViewById(R.id.new_save1);
        save2=findViewById(R.id.new_save2);
        bt_save=findViewById(R.id.bt_save);

    }
    @Override
    protected void initData() {
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sa1=save1.getText().toString();
                String sa2=save2.getText().toString();
                if(sa1.equals(sa2)){
                    update(uid1,sa2);
                    finish();
                }else{
                    showToast("两次密码不一致");
                }

            }
        });
    }
    private void update(Integer id,String new_save){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("userAccount", id);
        params.put("password", new_save);
        System.out.println(params);
        Api.config(ApiConfig.CHANGEUESER, params).getRequest(this,id,new PhotoCallBack() {
            @Override
            public void onSuccess(final String res) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("修改成功");
                    }
                });
            }
            @Override
            public void onFailure(Exception e) {
                Log.e("onFailure", e.toString());
            }
        });




    }
}
