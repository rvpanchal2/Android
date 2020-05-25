package com.gracepad.hh.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.gracepad.hh.fragment.ImpactMadeFragment;
import com.gracepad.hh.fragment.ImpactMyFragment;
import com.gracepad.hh.object.ProfileObject;

import java.util.ArrayList;

public class ImpactPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> arrFragmentTitle = new ArrayList<>();
    private ArrayList<Fragment> arrFragment = new ArrayList<>();

    public ImpactPagerAdapter(FragmentManager fm, ProfileObject object) {
        super(fm);
        arrFragmentTitle.add("Made an Impact");
        arrFragment.add(ImpactMadeFragment.newInstance(object));
        arrFragmentTitle.add("My Impact");
        arrFragment.add(ImpactMyFragment.newInstance(object));
    }

    @Override
    public Fragment getItem(int position) {
        return arrFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrFragmentTitle.get(position);
    }
}