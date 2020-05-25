package com.gracepad.hh.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.gracepad.hh.R;
import com.gracepad.hh.common.MyUtils;
import com.gracepad.hh.object.TutorialObject;

public class TutorialFragment extends Fragment {

    TutorialObject objTutorial;

    public static TutorialFragment newInstance(TutorialObject objTutorial) {
        TutorialFragment myFragment = new TutorialFragment();

        Bundle args = new Bundle();
        args.putSerializable("objTutorial", objTutorial);
        myFragment.setArguments(args);

        return myFragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objTutorial = (TutorialObject) getArguments().getSerializable("objTutorial");
    }

    AppCompatImageView imgView;
    AppCompatTextView txtTitle, txtDescription;

    Activity act;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_tutorial, container, false);

        act = getActivity();

        imgView = v.findViewById(R.id.img_Frg_Tutorial);
        txtTitle = v.findViewById(R.id.txt_Frg_Tutorial_Title);
        txtDescription = v.findViewById(R.id.txt_Frg_Tutorial_Description);

        try {
            Glide.with(act)
                    .load(objTutorial.getImage())
//                    .apply(GlideConstant.getCenterCropOption(Constant.nWidth, Constant.nHeight))
//                    .apply(RequestOptions.centerCropTransform())
                    .into(imgView);
        } catch (IllegalArgumentException | IllegalStateException e) {
            MyUtils.Log(e.toString());
        }

        txtTitle.setText(objTutorial.getTitle());
        txtDescription.setText(objTutorial.getDescription());
        return v;
    }
}