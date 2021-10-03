package com.example.photoshow.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.photoshow.R;
import com.example.photoshow.utils.StringUtil;

public class LoginActivity extends BaseActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

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
            navigeteTo(HomeActivity.class);
        }
    }
}