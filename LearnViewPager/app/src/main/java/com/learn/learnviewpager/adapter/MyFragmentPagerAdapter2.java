package com.learn.learnviewpager.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.learn.learnviewpager.fragment.ThreeFragment;

import java.util.ArrayList;

public class MyFragmentPagerAdapter2 extends FragmentPagerAdapter {

    private ArrayList<ThreeFragment> fragmentLists;


    public MyFragmentPagerAdapter2(FragmentManager fm, ArrayList<ThreeFragment> list) {
        super(fm);
        fragmentLists = list;

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentLists.get(position);
    }

    @Override
    public int getCount() {
        return fragmentLists.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return fragmentLists.get(position).getTitle();
    }
}
