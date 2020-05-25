package com.gracepad.hh.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.gracepad.hh.R;

public class SettingActivity extends AppCompatActivity {

    AppCompatImageView imgBack;
    LinearLayout lvChangePassword, lvAccountSetting, lvPrivacy;

    AppCompatActivity act;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        act = this;

        imgBack = findViewById(R.id.img_Setting_Back);
        lvChangePassword = findViewById(R.id.layout_Setting_ChangePassword);
        lvAccountSetting = findViewById(R.id.layout_Setting_Account);
        lvPrivacy = findViewById(R.id.layout_Setting_Privacy);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        lvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
