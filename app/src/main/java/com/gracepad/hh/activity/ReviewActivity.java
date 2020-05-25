package com.gracepad.hh.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gracepad.hh.GracePadApplication;
import com.gracepad.hh.R;
import com.gracepad.hh.adapter.ReviewAdapter;
import com.gracepad.hh.common.NetworkUtil;
import com.gracepad.hh.object.ReviewObject;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    AppCompatImageView imgBack;
    SwipeRefreshLayout refreshLayout;
    RecyclerView rvResult;

    AppCompatActivity act;
    GracePadApplication application;

    private ArrayList<ReviewObject> arrReview = new ArrayList<>();
    private ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        act = this;
        application = GracePadApplication.getInstance();

        imgBack = findViewById(R.id.img_Review_Back);
        refreshLayout = findViewById(R.id.refresh_Review);
        rvResult = findViewById(R.id.rv_Review);

        refreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkUtil.getConnectivityStatus(act)) {

                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setArray();

        adapter = new ReviewAdapter(act, arrReview);
        rvResult.setAdapter(adapter);
    }

    private void setArray() {
        arrReview.add(new ReviewObject());
        arrReview.add(new ReviewObject());
        arrReview.add(new ReviewObject());
        arrReview.add(new ReviewObject());
        arrReview.add(new ReviewObject());
        arrReview.add(new ReviewObject());
        arrReview.add(new ReviewObject());
        arrReview.add(new ReviewObject());
        arrReview.add(new ReviewObject());
        arrReview.add(new ReviewObject());
        arrReview.add(new ReviewObject());
        arrReview.add(new ReviewObject());
    }
}
