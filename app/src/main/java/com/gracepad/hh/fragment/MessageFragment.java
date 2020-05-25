package com.gracepad.hh.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gracepad.hh.GracePadApplication;
import com.gracepad.hh.R;
import com.gracepad.hh.activity.ChatActivity;
import com.gracepad.hh.adapter.MessageAdapter;
import com.gracepad.hh.object.ProfileObject;

import java.util.ArrayList;

public class MessageFragment extends Fragment {

    private RecyclerView rvMessage;

    private Activity act;
    private GracePadApplication application;

    private ArrayList<ProfileObject> arrMessage = new ArrayList<>();
    private MessageAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message, container, false);

//        ConstantStatusBar.setStatusBarGradiant(getParentFragment().getActivity());
        act = getActivity();
        application = GracePadApplication.getInstance();

        rvMessage = v.findViewById(R.id.rv_Message);

        adapter = new MessageAdapter(act, arrMessage);
        rvMessage.setAdapter(adapter);

        setArray();

        adapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(act, ChatActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    private void setArray() {
        arrMessage.add(new ProfileObject());
        arrMessage.add(new ProfileObject());
        arrMessage.add(new ProfileObject());
        arrMessage.add(new ProfileObject());
        arrMessage.add(new ProfileObject());
        arrMessage.add(new ProfileObject());
        arrMessage.add(new ProfileObject());
        arrMessage.add(new ProfileObject());
        arrMessage.add(new ProfileObject());
        arrMessage.add(new ProfileObject());
        arrMessage.add(new ProfileObject());
    }
}