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
import androidx.appcompat.widget.AppCompatTextView;

import com.gracepad.hh.R;
import com.gracepad.hh.common.MyUtils;
import com.gracepad.hh.common.NetworkUtil;

public class LoginActivity extends AppCompatActivity {

    AppCompatImageView imgBack;
    AppCompatEditText edtEmail, edtPassword;
    AppCompatButton btnLogin, btnFbLogin;
    AppCompatTextView txtShowPass, btnForgotPassword, txtRegister, txtRegisterT;
//    AppCompatImageView imgShowHidePass;

    AppCompatActivity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        act = this;

        imgBack = findViewById(R.id.img_Login_Back);
        edtEmail = findViewById(R.id.edt_Login_Email);
        edtPassword = findViewById(R.id.edt_Login_Password);
        btnLogin = findViewById(R.id.btn_Login);
        btnFbLogin = findViewById(R.id.btn_Login_Facebook);
        btnForgotPassword = findViewById(R.id.btn_Login_ForgotPassword);
        txtRegisterT = findViewById(R.id.txt_Login_RegisterT);
        txtRegister = findViewById(R.id.txt_Login_Register);
        txtShowPass = findViewById(R.id.txt_Login_ShowHidePassword);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        txtShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    txtShowPass.setText("Hide");

                    //TODO : Show Password
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    txtShowPass.setText("Show");

                    //TODO : Hide Password
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                edtPassword.setSelection(edtPassword.length());
            }
        });

        txtRegisterT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtRegister.callOnClick();
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()) {
                    MyUtils.showAlertDialog(act, getString(R.string.email_validation));
                    return;
                }

                if (edtPassword.length() <= 3) {
                    MyUtils.showAlertDialog(act, getString(R.string.password_validation));
                    return;
                }

                if (NetworkUtil.getConnectivityStatus(act)) {
                    Intent intent = new Intent(act, MainActivity.class);
                    startActivity(intent);
                } else
                    MyUtils.showAlertDialog(act, getString(R.string.network));
            }
        });

        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
