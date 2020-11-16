package com.learn.learnviewpager.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentLists;
    private ArrayList<String> titleLists;


    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list, ArrayList<String> titleLists) {
        super(fm);
        fragmentLists = list;
        this.titleLists = titleLists;

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
    public CharSequence getPageTitle(int position) {
        return titleLists.get(position);
    }
}
