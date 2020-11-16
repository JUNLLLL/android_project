package com.learn.learnviewpager.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.learn.learnviewpager.R;
import com.learn.learnviewpager.adapter.MyPagerAdapter2;

import java.util.ArrayList;

/**
 * Created by Jay on 2015/10/8 0008.
 */
public class TwoActivity extends AppCompatActivity {

    private ViewPager vpager_three;
    private ArrayList<View> aList;
    private ArrayList<String> sList;
    private MyPagerAdapter2 mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        vpager_three = (ViewPager) findViewById(R.id.vpager_three);
        aList = new ArrayList<View>();
        LayoutInflater li = getLayoutInflater();
        aList.add(li.inflate(R.layout.view_one, null, false));
        aList.add(li.inflate(R.layout.view_two, null, false));
        aList.add(li.inflate(R.layout.view_three, null, false));
        sList = new ArrayList<String>();
        sList.add("橘黄");
        sList.add("淡黄");
        sList.add("浅棕");
        mAdapter = new MyPagerAdapter2(aList, sList);
        vpager_three.setAdapter(mAdapter);

    }
}
