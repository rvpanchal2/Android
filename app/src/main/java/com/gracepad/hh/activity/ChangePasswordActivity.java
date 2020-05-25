package com.gracepad.hh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.gracepad.hh.R;
import com.gracepad.hh.common.MyUtils;
import com.gracepad.hh.common.NetworkUtil;

public class ChangePasswordActivity extends AppCompatActivity {

    AppCompatImageView imgBack, imgCurrentPassword, imgPassword, imgConfirmPassword;
    AppCompatEditText edtCurrentPassword, edtPassword, edtConfirmPassword;
    AppCompatButton btnSubmit, btnCancel;

    AppCompatActivity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        act = this;

        imgBack = findViewById(R.id.img_Register_Back);
        edtCurrentPassword = findViewById(R.id.edt_ChangePassword_Current);
        edtPassword = findViewById(R.id.edt_ChangePassword_New);
        edtConfirmPassword = findViewById(R.id.edt_ChangePassword_Verify);
        imgCurrentPassword = findViewById(R.id.img_ChangePassword_Current_ShowHide);
        imgPassword = findViewById(R.id.img_ChangePassword_New_ShowHide);
        imgConfirmPassword = findViewById(R.id.img_ChangePassword_Verify_ShowHide);
        btnSubmit = findViewById(R.id.btn_ChangePassword_Submit);
        btnCancel = findViewById(R.id.btn_ChangePassword_Cancel);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imgCurrentPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    imgCurrentPassword.setImageResource(R.drawable.ic_show_password);

                    //TODO : Show Password
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    imgCurrentPassword.setImageResource(R.drawable.ic_hide_password);

                    //TODO : Hide Password
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                edtPassword.setSelection(edtPassword.length());
            }
        });

        imgPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    imgPassword.setImageResource(R.drawable.ic_show_password);

                    //TODO : Show Password
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    imgPassword.setImageResource(R.drawable.ic_hide_password);

                    //TODO : Hide Password
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                edtPassword.setSelection(edtPassword.length());
            }
        });

        imgConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtConfirmPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    imgConfirmPassword.setImageResource(R.drawable.ic_show_password);

                    //TODO : Show Password
                    edtConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    imgConfirmPassword.setImageResource(R.drawable.ic_hide_password);

                    //TODO : Hide Password
                    edtConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                edtConfirmPassword.setSelection(edtConfirmPassword.length());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtConfirmPassword.length() <= 3) {
                    MyUtils.showAlertDialog(act, getString(R.string.current_password_validation));
                    return;
                }

                if (edtPassword.length() <= 3) {
                    MyUtils.showAlertDialog(act, getString(R.string.password_validation));
                    return;
                }

                if (!edtConfirmPassword.getText().toString().equals(edtPassword.getText().toString())) {
                    MyUtils.showAlertDialog(act, getString(R.string.confirm_password_validation));
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
