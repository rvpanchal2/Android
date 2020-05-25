package com.gracepad.hh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.gracepad.hh.R;
import com.gracepad.hh.common.MyUtils;
import com.gracepad.hh.common.NetworkUtil;

public class RegisterActivity extends AppCompatActivity {

    AppCompatImageView imgBack;
    AppCompatEditText edtName, edtEmail, edtMoNumber, edtPassword, edtConfirmPassword;
    AppCompatButton btnLogin;
    AppCompatTextView txtShowPass, txtConfirmShowPass, txtLogin, txtLoginT;
    RadioGroup rgOption;

    AppCompatActivity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        act = this;

        imgBack = findViewById(R.id.img_Register_Back);
        edtName = findViewById(R.id.edt_Register_FullName);
        edtEmail = findViewById(R.id.edt_Register_Email);
        edtMoNumber = findViewById(R.id.edt_Register_MoNumber);
        edtPassword = findViewById(R.id.edt_Register_Password);
        edtConfirmPassword = findViewById(R.id.edt_Register_ConfirmPassword);
        btnLogin = findViewById(R.id.btn_Register);
        txtLoginT = findViewById(R.id.txt_Register_LoginT);
        txtLogin = findViewById(R.id.txt_Register_Login);
        txtShowPass = findViewById(R.id.txt_Register_Password_ShowHide);
        txtConfirmShowPass = findViewById(R.id.txt_Register_ConfirmPassword_ShowHide);

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

        txtConfirmShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtConfirmPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    txtConfirmShowPass.setText("Hide");

                    //TODO : Show Password
                    edtConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    txtConfirmShowPass.setText("Show");

                    //TODO : Hide Password
                    edtConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                edtConfirmPassword.setSelection(edtConfirmPassword.length());
            }
        });

        txtLoginT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLogin.callOnClick();
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
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
