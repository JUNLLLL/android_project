package com.example.litepaltest.database;

import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

public class New extends LitePalSupport {
    private int id;
    private String Author;
    private Introduction introduction;
    private List<Comment> commentList = new ArrayList<Comment>();

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    private String Content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Introduction getIntroduction() {
        return introduction;
    }

    public void setIntroduction(Introduction introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String toString()
    {
        return "New{" +
                "id=" + id +
                ", Author=" + Author +
                ", Introduction=" + introduction +
                ", commentList='" + commentList.toString() + '\'' +
                '}';
    }
}
