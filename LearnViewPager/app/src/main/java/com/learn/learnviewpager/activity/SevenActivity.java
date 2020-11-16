package com.learn.learnviewpager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.learn.learnviewpager.R;
import com.learn.learnviewpager.adapter.MyFragmentPagerAdapter2;
import com.learn.learnviewpager.adapter.MyPagerAdapter;
import com.learn.learnviewpager.fragment.ThreeFragment;

import java.util.ArrayList;

public class SevenActivity extends AppCompatActivity {
    private ArrayList<View> views = new ArrayList<View>();
    private ViewPager vpager_seven;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven);
        initViews();
    }

    private  void  initViews(){
        View view = getLayoutInflater().inflate(R.layout.cardview_item,null,false);
        view.findViewById(R.id.action_image).setBackgroundResource(R.drawable.imgae1);
        View view1 = getLayoutInflater().inflate(R.layout.cardview_item,null,false);
        view1.findViewById(R.id.action_image).setBackgroundResource(R.drawable.image2);
        View view2 = getLayoutInflater().inflate(R.layout.cardview_item,null,false);
        view2.findViewById(R.id.action_image).setBackgroundResource(R.drawable.image3);
        View view3 = getLayoutInflater().inflate(R.layout.cardview_item,null,false);
        view3.findViewById(R.id.action_image).setBackgroundResource(R.drawable.image4);
        views.add(view);
        views.add(view1);
        views.add(view2);
        views.add(view3);

        vpager_seven = (ViewPager)findViewById(R.id.viewpager);
        vpager_seven.setAdapter(new MyPagerAdapter(views));
        vpager_seven.setPageMargin(20);//Page之间缩进50，作用是让页面之间空隙更大一点，根据自己需要而定。
        vpager_seven.setOffscreenPageLimit(3);//设置缓存的页面数量

        vpager_seven.setPageTransformer(false, new LoopTransformer());
    }

    //添加了透明度和缩放两种效果
    public class LoopTransformer implements ViewPager.PageTransformer {

        private static final float MIN_ALPHA = 0.5f;
        private static final float MIN_SCALE = 0.9f;
        @Override
            public void transformPage(View view, float position)
            {
                if (position < -1)
                {
                    view.setAlpha(MIN_ALPHA);
                    setScale(view,MIN_SCALE);
                } else if (position <= 1)
                { // [-1,1]

                    if (position < 0) //[0，-1]
                    {
                        float factor = MIN_ALPHA + (1 - MIN_ALPHA) * (1 + position);
                        float scaleValue = MIN_SCALE + (1 - MIN_SCALE) * (1 + position);
                        view.setAlpha(factor);
                        setScale(view,scaleValue);
                    } else//[1，0]
                    {
                        float factor = MIN_ALPHA + (1 - MIN_ALPHA) * (1 - position);
                        view.setAlpha(factor);
                        float scaleValue = MIN_SCALE + (1 - MIN_SCALE) * (1 - position);
                        setScale(view,scaleValue);
                    }
                } else
                { // (1,+Infinity]
                    view.setAlpha(MIN_ALPHA);
                    setScale(view,MIN_SCALE);
                }
            }

            private void setScale(View view,float value){
                view.setScaleX(value);
                view.setScaleY(value);
            }
    }


}
