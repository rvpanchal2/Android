package com.gracepad.hh.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.gracepad.hh.R;
import com.gracepad.hh.common.MyUtils;
import com.gracepad.hh.common.NetworkUtil;

public class ForgotPasswordActivity extends AppCompatActivity {

    AppCompatImageView imgBack;
    AppCompatEditText edtEmail;
    AppCompatButton btnSubmit;

    AppCompatActivity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        act = this;

        imgBack = findViewById(R.id.img_ForgotPassword_Back);
        edtEmail = findViewById(R.id.edt_ForgotPassword_Email);
        btnSubmit = findViewById(R.id.btn_ForgotPassword);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()) {
                    MyUtils.showAlertDialog(act, getString(R.string.email_validation));
                    return;
                }

                if (NetworkUtil.getConnectivityStatus(act)) {
                    MyUtils.showAlertDialogWithBack(act, "Your reset password request has been sent successfully");
                } else
                    MyUtils.showAlertDialog(act, getString(R.string.network));
            }
        });
    }
}
