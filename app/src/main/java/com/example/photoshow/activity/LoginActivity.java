package com.example.photoshow.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.photoshow.R;
import com.example.photoshow.utils.Constants;
import com.example.photoshow.utils.HttpUtils;
import com.example.photoshow.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    private boolean password_currect = false;
    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        etUsername = findViewById(R.id.login_username);
        etPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btn_login2);
    }

    @Override
    protected void initData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                login(username,password);
            }
        });    }

    private void login(String username, String password){
        if (StringUtil.isEmpry(username,password)){
            showToast("请输入账号");
        }
        else if(StringUtil.isEmpry(username,password)){
            showToast("请输入密码");
        }
        else {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String result = HttpUtils.getJsonContent(Constants.SERVER_URL +"login?account="+username+"&password="+password);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        if (jsonObject.getBoolean("result") == true){
                            password_currect = true;
                            System.out.println(password_currect);
                        }else {
                            password_currect = false;
                            System.out.println(password_currect);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            try {
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if (password_currect){
                navigeteTo(HomeActivity.class);
            }else {
                Toast.makeText(LoginActivity.this,"账号信息有误",Toast.LENGTH_SHORT).show();
            }
        }
    }
}