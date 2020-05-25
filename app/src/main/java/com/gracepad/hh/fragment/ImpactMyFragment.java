package com.gracepad.hh.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gracepad.hh.GracePadApplication;
import com.gracepad.hh.R;
import com.gracepad.hh.activity.CharityDetailActivity;
import com.gracepad.hh.adapter.ImpactAdapter;
import com.gracepad.hh.common.NetworkUtil;
import com.gracepad.hh.common.StoreUserData;
import com.gracepad.hh.object.CharityObject;
import com.gracepad.hh.object.CharityRequestObject;
import com.gracepad.hh.object.ProfileObject;

import java.util.ArrayList;

public class ImpactMyFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types and number of parameters
    public static ImpactMyFragment newInstance(ProfileObject object) {
        ImpactMyFragment fragment = new ImpactMyFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, object);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            objProfile = (ProfileObject) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView rvItem;

    private Activity act;
    private GracePadApplication application;

    private ProfileObject objProfile;

    private ArrayList<CharityObject> arrImpact = new ArrayList<>();
    private ImpactAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_impact_my, container, false);

        act = getActivity();
        application = GracePadApplication.getInstance();
        objProfile = new StoreUserData().getUsers(act);

        refreshLayout = v.findViewById(R.id.refresh_Impact_My);
        rvItem = v.findViewById(R.id.rv_Impact_My);

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

        adapter = new ImpactAdapter(act, arrImpact);
        rvItem.setAdapter(adapter);

        adapter.setOnItemClickListener(new ImpactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(act, CharityDetailActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    private void setArray() {
        arrImpact.add(getObject());
        arrImpact.add(getObject());
    }

    private CharityObject getObject() {
        CharityObject object = new CharityObject();
        ArrayList<CharityRequestObject> arr = new ArrayList<>();
        arr.add(new CharityRequestObject());
        arr.add(new CharityRequestObject());
        arr.add(new CharityRequestObject());

        object.setArrCharityRequest(arr);
        return object;
    }
}