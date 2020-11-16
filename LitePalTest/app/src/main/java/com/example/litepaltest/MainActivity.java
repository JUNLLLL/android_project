package com.example.litepaltest;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.litepaltest.database.Comment;
import com.example.litepaltest.database.Introduction;
import com.example.litepaltest.database.New;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase=(Button)findViewById(R.id.createBtn);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Createdb();
            }
        });
    }


    private void Createdb(){
        SQLiteDatabase db = LitePal.getDatabase();
        Log.d("测试", db.toString());

        Introduction introduction = new Introduction();
        introduction.setDigest("new digest444");
        introduction.setGuide("new guide444");
        introduction.save();

        Comment comment1=new Comment();
        comment1.setContent("评论1");
        comment1.save();

        Comment comment2=new Comment();
        comment2.setContent("评论2");
        comment2.save();

        New news= new New();
        news.setAuthor("Jeffrey444");
        news.setIntroduction(introduction);
        news.getCommentList().add(comment1);
        news.getCommentList().add(comment2);
        news.setContent("新闻内容444");
        news.save();

     //必须添加ture才能加载关联的数据，加载introduction
        New n=LitePal.findLast(New.class,true);
        Log.e("Jeffrey", n.toString());

        Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show();
    }
}
