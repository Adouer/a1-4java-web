package com.adouer.a14javaweb.dao;

import com.adouer.a14javaweb.entity.Comment;
import com.adouer.a14javaweb.entity.News;

import java.util.List;


public interface NewsDAO {

    //插入新闻
    boolean insertNews(News news);


    //查询分页的新闻列表
    List<News> findNewsList(int currentPage, int pageSize);

    //查询总记录数
    int findTotalCount();


    //更新附件的下载次数
    boolean updateDownloadcount(int newsid);


    //查询新闻评论列表
    List<Comment> findCommentList(int newsid);

    //插入新闻评论
    boolean insertComment(Comment comment);


}
