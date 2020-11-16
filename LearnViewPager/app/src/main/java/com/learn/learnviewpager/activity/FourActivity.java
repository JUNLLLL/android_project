package com.learn.learnviewpager.activity;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.learn.learnviewpager.R;
import com.learn.learnviewpager.adapter.MyFragmentPagerAdapter;
import com.learn.learnviewpager.fragment.OneFragment;
import com.learn.learnviewpager.fragment.TwoFragment;

import java.util.ArrayList;

public class FourActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {


    private ViewPager vpager_five;
    private ImageView img_cursor;
    private TextView tv_one;
    private TextView tv_two;


    private ArrayList<Fragment> aList = new ArrayList<Fragment>();;
    private ArrayList<String> sList = new ArrayList<String>();;
    private MyFragmentPagerAdapter mAdapter;

    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        initViews();

    }

    private void initViews() {
        vpager_five = (ViewPager) findViewById(R.id.vpager_five);
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        img_cursor = (ImageView) findViewById(R.id.img_cursor);

        //下划线动画的相关设置：
        bmpWidth = BitmapFactory.decodeResource(getResources(), R.mipmap.line).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 2 - bmpWidth) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        img_cursor.setImageMatrix(matrix);// 设置动画初始位置
        //移动的距离
        one = offset * 2 + bmpWidth;// 移动一页的偏移量,比如1->2,或者2->3
        two = one * 2;// 移动两页的偏移量,比如1直接跳3


        aList.add(new OneFragment());
        aList.add(new TwoFragment());
        sList.add("第一个");
        sList.add("第二个");

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),aList,sList);
        vpager_five.setAdapter(mAdapter);
        vpager_five.setCurrentItem(0);

        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);

        vpager_five.addOnPageChangeListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_one:
                vpager_five.setCurrentItem(0);
                break;
            case R.id.tv_two:
                vpager_five.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Animation animation = null;
        switch (position) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
        }
        currIndex = position;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        img_cursor.startAnimation(animation);//开始动画
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
