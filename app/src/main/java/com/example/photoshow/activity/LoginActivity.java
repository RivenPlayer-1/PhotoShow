package com.example.photoshow.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.photoshow.R;
import com.example.photoshow.api.Api;
import com.example.photoshow.api.ApiConfig;
import com.example.photoshow.api.TtitCallback;
import com.example.photoshow.entity.LoginResponse;
import com.example.photoshow.utils.StringUtils;
import com.google.gson.Gson;

import java.util.HashMap;

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
        });
    }

    private void login(String account, String pwd) {
        if (StringUtils.isEmpty(account)) {
            showToast("请输入账号");
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("account", account);
        params.put("password", pwd);
        Api.config(ApiConfig.LOGIN, params).postRequest(this,new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
                Log.e("onSuccess", res);
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(res, LoginResponse.class);
                System.out.println(loginResponse);
                if (loginResponse.getFault().equals("true")) {
                    String token = loginResponse.getToken();
                    String userAccount = loginResponse.getUserAccount();
                    insertVal("token", token);
                    insertVal("userAccount",loginResponse.getUserAccount());
                    navigateToWithFlag(HomeActivity.class,
                            Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    showToastSync("登录成功");
                } else {
                    showToastSync("登录失败");
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}