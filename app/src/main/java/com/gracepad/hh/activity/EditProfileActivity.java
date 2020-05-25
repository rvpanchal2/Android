package com.gracepad.hh.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.gracepad.hh.R;
import com.gracepad.hh.common.MyUtils;
import com.gracepad.hh.common.NetworkUtil;

public class EditProfileActivity extends AppCompatActivity {

    AppCompatImageView imgBack;
    AppCompatEditText edtName, edtEmail, edtMoNumber, edtAddress, edtMessage;
    AppCompatButton btnLogin;
    RadioGroup rgOption;

    AppCompatActivity act;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        act = this;

        imgBack = findViewById(R.id.img_EditProfile_Back);
        edtName = findViewById(R.id.edt_EditProfile_FullName);
        edtEmail = findViewById(R.id.edt_EditProfile_Email);
        edtMoNumber = findViewById(R.id.edt_EditProfile_MoNumber);
        edtAddress = findViewById(R.id.edt_EditProfile_Address);
        edtMessage = findViewById(R.id.edt_EditProfile_Status);
        btnLogin = findViewById(R.id.btn_EditProfile);

        edtAddress.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (edtAddress.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtName.length() <= 2) {
                    MyUtils.showAlertDialog(act, getString(R.string.name_validation));
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()) {
                    MyUtils.showAlertDialog(act, getString(R.string.email_validation));
                    return;
                }

                if (edtMoNumber.length() <= 9) {
                    MyUtils.showAlertDialog(act, getString(R.string.number_validation));
                    return;
                }

                if (NetworkUtil.getConnectivityStatus(act)) {
                    Intent intent = new Intent(act, MainActivity.class);
                    startActivity(intent);
                } else
                    MyUtils.showAlertDialog(act, getString(R.string.network));
            }
        });
    }
}
