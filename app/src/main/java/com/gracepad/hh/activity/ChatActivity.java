package com.gracepad.hh.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gracepad.hh.GracePadApplication;
import com.gracepad.hh.R;
import com.gracepad.hh.adapter.ChatAdapter;
import com.gracepad.hh.common.NetworkUtil;
import com.gracepad.hh.object.ChatObject;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    SwipeRefreshLayout refreshLayout;
    RecyclerView rvResult;

    AppCompatActivity act;
    GracePadApplication application;

    private ArrayList<ChatObject> arrChat = new ArrayList<>();
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        act = this;
        application = GracePadApplication.getInstance();

        rvResult = findViewById(R.id.rv_Chat);
        refreshLayout = findViewById(R.id.rv_Chat_Refresh);

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

        setArray();

        adapter = new ChatAdapter(act, arrChat);
        rvResult.setAdapter(adapter);
    }

    private void setArray() {
        arrChat.add(new ChatObject());
        arrChat.add(new ChatObject());
        arrChat.add(new ChatObject());
        arrChat.add(new ChatObject());
        arrChat.add(new ChatObject());
        arrChat.add(new ChatObject());
        arrChat.add(new ChatObject());
        arrChat.add(new ChatObject());
        arrChat.add(new ChatObject());
        arrChat.add(new ChatObject());
        arrChat.add(new ChatObject());
        arrChat.add(new ChatObject());
        arrChat.add(new ChatObject());
    }
}