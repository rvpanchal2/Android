package com.gracepad.hh.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.gracepad.hh.GracePadApplication;
import com.gracepad.hh.R;

public class HeroFragment extends Fragment {

    Activity act;
    GracePadApplication application;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hero, container, false);

        act = getActivity();
        application = GracePadApplication.getInstance();

        return v;
    }
}