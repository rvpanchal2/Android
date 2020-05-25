package com.gracepad.hh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.gracepad.hh.GracePadApplication;
import com.gracepad.hh.R;
import com.gracepad.hh.adapter.NotificationAdapter;
import com.gracepad.hh.object.NotificationObject;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    AppCompatImageView imgBack;
    RecyclerView rvResult;

    AppCompatActivity act;
    GracePadApplication application;

    private ArrayList<NotificationObject> arrImpact = new ArrayList<>();
    private NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        act = this;
        application = GracePadApplication.getInstance();

        imgBack = findViewById(R.id.img_Notification_Back);
        rvResult = findViewById(R.id.rv_Notification);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setArray();

        adapter = new NotificationAdapter(act, arrImpact);
        rvResult.setAdapter(adapter);

        adapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(act, CharityDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setArray() {
        arrImpact.add(new NotificationObject());
        arrImpact.add(new NotificationObject());
        arrImpact.add(new NotificationObject());
        arrImpact.add(new NotificationObject());
        arrImpact.add(new NotificationObject());
        arrImpact.add(new NotificationObject());
        arrImpact.add(new NotificationObject());
        arrImpact.add(new NotificationObject());
        arrImpact.add(new NotificationObject());
        arrImpact.add(new NotificationObject());
    }
}
