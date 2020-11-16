package com.learn.learnviewpager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.learn.learnviewpager.activity.FiveActivity;
import com.learn.learnviewpager.activity.FourActivity;
import com.learn.learnviewpager.activity.OneActivity;
import com.learn.learnviewpager.activity.SevenActivity;
import com.learn.learnviewpager.activity.SixActivity;
import com.learn.learnviewpager.activity.ThreeActivity;
import com.learn.learnviewpager.activity.TwoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        Button  btn_one = (Button) findViewById(R.id.btn_one);
        Button   btn_two = (Button) findViewById(R.id.btn_two);
        Button  btn_three = (Button) findViewById(R.id.btn_three);
        Button   btn_four = (Button) findViewById(R.id.btn_four);
        Button   btn_five = (Button) findViewById(R.id.btn_five);
        Button   btn_six = (Button) findViewById(R.id.btn_six);
        Button   btn_seven = (Button) findViewById(R.id.btn_seven);
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);
        btn_six.setOnClickListener(this);
        btn_seven.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                startActivity(new Intent(this, OneActivity.class));
                break;
            case R.id.btn_two:
                startActivity(new Intent(this, TwoActivity.class));
                break;
            case R.id.btn_three:
                startActivity(new Intent(this, ThreeActivity.class));
                break;
            case R.id.btn_four:
                startActivity(new Intent(this, FourActivity.class));
                break;
            case R.id.btn_five:
                startActivity(new Intent(this, FiveActivity.class));
                break;
            case R.id.btn_six:
                startActivity(new Intent(this, SixActivity.class));
                break;
                case R.id.btn_seven:
                startActivity(new Intent(this, SevenActivity.class));
                break;
        }
    }


}
