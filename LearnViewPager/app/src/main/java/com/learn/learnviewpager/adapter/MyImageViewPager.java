package com.learn.learnviewpager.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * author : Jeffrey
 * date   : 2019/9/2222:25
 * desc   :
 */
public class MyImageViewPager extends PagerAdapter {
    private List<ImageView> imageList;

    public MyImageViewPager(List<ImageView> imageList) {
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }


    //初始化每个条目要显示的内容
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int newPostion = position % imageList.size();
        //获取到条目要显示的内容imageView
        ImageView img = imageList.get(newPostion);
        container.addView(img);
        return img;
    }

    //是否复用当前view
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //销毁条目
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }

}
