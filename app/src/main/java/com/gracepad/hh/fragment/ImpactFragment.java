package com.gracepad.hh.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gracepad.hh.GracePadApplication;
import com.gracepad.hh.R;
import com.gracepad.hh.activity.EditProfileActivity;
import com.gracepad.hh.activity.SettingActivity;
import com.gracepad.hh.adapter.ImpactPagerAdapter;
import com.gracepad.hh.common.StoreUserData;
import com.gracepad.hh.object.ProfileObject;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

public class ImpactFragment extends Fragment {

    private AppCompatButton btnEditProfile, btnMade, btnMy;
    private ViewPager vpDetail;
    private AppCompatImageView imgSetting;

    private Activity act;
    private GracePadApplication application;

    private ProfileObject objProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_impact, container, false);

        act = getActivity();
        application = GracePadApplication.getInstance();
        objProfile = new StoreUserData().getUsers(act);

        vpDetail = v.findViewById(R.id.vp_Impact);
        btnEditProfile = v.findViewById(R.id.btn_Impact_EditProfile);
        btnMade = v.findViewById(R.id.btn_Impact_Made);
        btnMy = v.findViewById(R.id.btn_Impact_My);
        imgSetting = v.findViewById(R.id.img_Impact_Setting);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act, SettingActivity.class);
                startActivity(intent);
            }
        });

        btnMade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMade.setTypeface(GracePadApplication.tazMedium);
                btnMy.setTypeface(GracePadApplication.tazRegular);
                btnMade.setTextColor(ContextCompat.getColor(act, R.color.theme_color));
                btnMy.setTextColor(ContextCompat.getColor(act, R.color.txt_sub_color));
                btnMade.setBackgroundResource(R.drawable.rounded_corner_button_trans);
                btnMy.setBackgroundResource(R.color.transparent);
                if (vpDetail.getCurrentItem() != 0)
                    vpDetail.setCurrentItem(0);
            }
        });

        btnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMade.setTypeface(GracePadApplication.tazRegular);
                btnMy.setTypeface(GracePadApplication.tazMedium);
                btnMy.setTextColor(ContextCompat.getColor(act, R.color.theme_color));
                btnMade.setTextColor(ContextCompat.getColor(act, R.color.txt_sub_color));
                btnMy.setBackgroundResource(R.drawable.rounded_corner_button_trans);
                btnMade.setBackgroundResource(R.color.transparent);
                if (vpDetail.getCurrentItem() != 1)
                    vpDetail.setCurrentItem(1);
            }
        });

        ImpactPagerAdapter pageAdapter = new ImpactPagerAdapter(getFragmentManager(), objProfile);
        vpDetail.setAdapter(pageAdapter);

        vpDetail.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    btnMade.callOnClick();
                } else {
                    btnMy.callOnClick();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return v;
    }
}