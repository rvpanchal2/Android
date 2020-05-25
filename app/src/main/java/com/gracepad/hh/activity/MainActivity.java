package com.gracepad.hh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.gracepad.hh.GracePadApplication;
import com.gracepad.hh.R;
import com.gracepad.hh.common.Constant;
import com.gracepad.hh.common.MyUtils;
import com.gracepad.hh.fragment.CreateFragment;
import com.gracepad.hh.fragment.HeroFragment;
import com.gracepad.hh.fragment.HomeFragment;
import com.gracepad.hh.fragment.ImpactFragment;
import com.gracepad.hh.fragment.MessageFragment;
import com.gracepad.hh.views.NonSwipeableViewPager;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    NonSwipeableViewPager viewPager;
    TabLayout tabLayout;
    AppCompatImageView imgCenter;

    AppCompatActivity act;
    GracePadApplication application;

    int nLastPos = 0;
    boolean isLogout = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        act = this;
//        ConstantStatusBar.setStatusBarGradiant(act);
        Constant.setHeightWidth(act);
        application = GracePadApplication.getInstance();
        application.getObserver().addObserver(this);

        viewPager = findViewById(R.id.ViewPager_Main);
        tabLayout = findViewById(R.id.tabs_Main);
        imgCenter = findViewById(R.id.img_Main_Center);

        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
//        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 2) {
                    imgCenter.callOnClick();
                } else {
                    nLastPos = tab.getPosition();
                }

                MyUtils.Log("Last Pos : " + nLastPos);
                viewPager.setCurrentItem(nLastPos, false);
                tabLayout.getTabAt(nLastPos).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        imgCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act, CreateHelpActivity.class);
                startActivity(intent);
            }
        });
        setupTabIcons();
    }

    private void setupTabIcons() {
        String[] tabIconsNames = {
                "Home", "Be a Hero", "Create Help", "Message", "My Impact"
        };

        int[] tabIcons = {
                R.drawable.tab_home,
                R.drawable.tab_hero,
                R.drawable.tab_create,
                R.drawable.tab_chat,
                R.drawable.tab_impect
        };

        for (int i = 0; i < tabIcons.length; i++) {
            tabLayout.addTab(setupTabLayout(tabIconsNames[i], tabIcons[i]));
        }
    }

    public TabLayout.Tab setupTabLayout(String strTitle, int imageId) {
        View view = LayoutInflater.from(act).inflate(R.layout.row_home_tab, null);

        AppCompatTextView tab = view.findViewById(R.id.txt_Tab_Home);
        tab.setText(strTitle);

        AppCompatImageView img = view.findViewById(R.id.img_Tab_Home);
        img.setImageResource(imageId);

        return tabLayout.newTab().setCustomView(view);
    }

    ArrayList<Fragment> arrFragment = new ArrayList<>();

    private class PagerAdapter extends FragmentStatePagerAdapter {

        PagerAdapter(FragmentManager fm) {
            super(fm);
            arrFragment.add(new HomeFragment());
            arrFragment.add(new HeroFragment());
            arrFragment.add(new CreateFragment());
            arrFragment.add(new MessageFragment());
            arrFragment.add(new ImpactFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return arrFragment.get(position);
        }

        @Override
        public int getCount() {
            return arrFragment.size();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("isLogout", isLogout);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}