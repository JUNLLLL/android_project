package com.learn.learnviewpager.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.learn.learnviewpager.R;
import com.learn.learnviewpager.adapter.MyImageViewPager;

import java.util.ArrayList;
import java.util.List;

public class SixActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private  ViewPager viewPager;
    private  TextView title;
    //存放指示点的线性布局
    private  LinearLayout dotGroup;

   //数据
    private int[] imageUrl =new int[] {R.drawable.imgae1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
    private List<ImageView> imageList = new ArrayList<ImageView>();
    private String[] imageDescArrs = new String[]{"第一张", "第二张", "第三张", "第四张"};

    private int previousPosition = 0; //默认为0

     private Handler handler = new Handler();

     //= new Handler() {
        //        @Override
        //        public void handleMessage(Message msg) {
        //            super.handleMessage(msg);
        //            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        //        }
        //    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);
        viewPager = findViewById(R.id.viewPager);
        title = findViewById(R.id.title);
        dotGroup = findViewById(R.id.dot_group);
        initView();
    }

    private void initView() {
        initViewPagerData();
        viewPager.setAdapter(new MyImageViewPager(imageList));

        //设置当前viewPager要显示的第几个条目
        int item = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageList.size());
        viewPager.setCurrentItem(item);

        //把第一个小圆点设置成白色，显示第一个TExtView内容
        dotGroup.getChildAt(previousPosition).setEnabled(true);
        title.setText(imageDescArrs[previousPosition]);
        //设置viewPager滑动监听事件
        viewPager.addOnPageChangeListener(this);

        //region 第二种暂停播放方式，不会松手时间没有归零
        //        viewPager.setOnTouchListener(new View.OnTouchListener() {
        //            @Override
        //            public boolean onTouch(View view, MotionEvent motionEvent) {
        //                switch (motionEvent.getAction()) {
        //                    case MotionEvent.ACTION_DOWN:
        //                        Toast.makeText(SixActivity.this, "Down", Toast.LENGTH_SHORT).show();
        //                        isSwitchPager = false;
        //                        break;
        //                    case MotionEvent.ACTION_UP:
        //                        Toast.makeText(SixActivity.this, "Up", Toast.LENGTH_SHORT).show();
        //                        isSwitchPager = true;
        //                        break;
        //                }
        //                return false;
        //            }
        //        });

        //根据isLoop设置是否轮播
        //        new Timer().schedule(new TimerTask() {
        //            @Override
        //            public void run() {
        //                if(isSwitchPager){ //如果isLoop = true 才进行轮播
        //                    handler.sendMessage(new Message());
        //                }
        //            }
        //        }, 3000, 3000);//这里定义了轮播图切换的间隔时间
        //endregion

        autoScroll(viewPager,5000,imageList.size());
    }

    //初始化数据
    private void initViewPagerData() {

        ImageView im;
        View dotView;
        for (int i = 0; i < imageUrl.length; i++) {
            im = new ImageView(this);
            Glide.with(this).load(imageUrl[i]).centerCrop().into(im);
            imageList.add(im);
            //准备小圆点数据
            dotView = new View(getApplicationContext());
            dotView.setBackgroundResource(R.drawable.selector_dot);

            //设置小圆点宽和高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            //设置每个小圆点之间的距离
            if (i != 0) {
                params.leftMargin = 30;
            }
            dotView.setLayoutParams(params);
            //设置小圆点状态
            dotView.setEnabled(false);
            //把dotView加入到线性布局中
            dotGroup.addView(dotView);
        }

    }


    private void autoScroll(final ViewPager viewPager,final  int pauseTime, final int size) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取当前的轮播的位置
                int currentItem = viewPager.getCurrentItem();
                //从当前的图切换到另一张通过(currentItem + 1)就可以实现
                viewPager.setCurrentItem(currentItem + 1);
                //通过mHandler请求延迟3秒
                handler.postDelayed(this, pauseTime);
                //调用触摸滑动事件方法
                viewPagerOnTouch(viewPager, pauseTime, size);
            }

        }, pauseTime);
    }

    private void viewPagerOnTouch(final ViewPager viewPager, final int pauseTime, final int size) {
        //通过mViewPager去设置触摸滑动的点击事件
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        handler.removeMessages(0);
                        //移除回调函数和消息
                    case MotionEvent.ACTION_DOWN:
                        handler.removeCallbacksAndMessages(null);
                        break;
                        //松开手，开启自动滑动
                    case MotionEvent.ACTION_UP:
                        autoScroll(viewPager, pauseTime, size);
                        break;
                }
                return false;
            }
        });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int newPostion = position % imageList.size();
        //取出postion的位置小圆点设置为true
        dotGroup.getChildAt(newPostion).setEnabled(true);
        //把一个小圆点设置为false
        dotGroup.getChildAt(previousPosition).setEnabled(false);
        title.setText(imageDescArrs[newPostion]);
        previousPosition = newPostion;
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
