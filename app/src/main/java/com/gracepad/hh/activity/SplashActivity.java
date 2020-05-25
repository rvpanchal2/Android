package com.gracepad.hh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.gracepad.hh.R;
import com.gracepad.hh.common.Constant;

public class SplashActivity extends AppCompatActivity {

    AppCompatActivity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        act = this;
        Constant.setHeightWidth(act);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(act, TutorialActivity.class);
//                Intent intent = new Intent(act, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}