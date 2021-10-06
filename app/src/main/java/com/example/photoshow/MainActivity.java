package com.example.photoshow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.photoshow.activity.BaseActivity;
import com.example.photoshow.activity.LoginActivity;
import com.example.photoshow.activity.RegisterActivity;

public class MainActivity extends BaseActivity {

    private Button btnLogin1;
    private Button btnRegister1;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btnLogin1 = findViewById(R.id.btn_login1);
        btnRegister1 = findViewById(R.id.btn_register1);

    }



    @Override
    protected void initData() {
        btnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(LoginActivity.class);
            }
        });

        btnRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(RegisterActivity.class);
            }
        });
    }
}