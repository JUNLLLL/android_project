package com.learn.learnviewpager.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.learn.learnviewpager.R;
import com.learn.learnviewpager.adapter.MyFragmentPagerAdapter2;
import com.learn.learnviewpager.fragment.ThreeFragment;

import java.util.ArrayList;

public class FiveActivity extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener {

    private ArrayList<ThreeFragment> fragments = new ArrayList<ThreeFragment>();
    private MyFragmentPagerAdapter2 mAdapter;
    private ViewPager vpager_five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        initViews();

    }

    private void initViews() {

        fragments.add(new ThreeFragment("个性推荐"));
        fragments.add(new ThreeFragment("歌单"));
        fragments.add(new ThreeFragment("主播电台"));
        fragments.add(new ThreeFragment("排行榜"));
        fragments.add(new ThreeFragment("流行"));
        fragments.add(new ThreeFragment("古典"));

        mAdapter = new MyFragmentPagerAdapter2(getSupportFragmentManager(),fragments);


        vpager_five = (ViewPager) findViewById(R.id.vewpager);
        vpager_five.setAdapter(mAdapter);

        TabLayout  mTabLayout =  findViewById(R.id.tab_layout2);
//        mTabLayout.addTab(mTabLayout.newTab().setText("个性推荐"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("歌单"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("主播电台"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("排行榜"));
        // 使用这个setText会失效，所以需要
        mTabLayout.setupWithViewPager(vpager_five);
        mTabLayout.addOnTabSelectedListener(this);
}

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
