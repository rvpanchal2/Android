package com.gracepad.hh.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.gracepad.hh.fragment.TutorialFragment;
import com.gracepad.hh.object.TutorialObject;

import java.util.ArrayList;

public class TutorialAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> arrFragment = new ArrayList<>();

    public TutorialAdapter(FragmentManager fm, ArrayList<TutorialObject> arrTut) {
        super(fm);
        if (arrTut.size() > 0) {
            for (int i = 0; i < arrTut.size(); i++) {
                arrFragment.add(TutorialFragment.newInstance(arrTut.get(i)));
            }
        }
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
