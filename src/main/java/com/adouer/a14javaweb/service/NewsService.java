package com.adouer.a14javaweb.service;

import com.adouer.a14javaweb.entity.Comment;
import com.adouer.a14javaweb.entity.News;
import com.adouer.a14javaweb.entity.User;
import com.adouer.a14javaweb.vo.NewsPage;

import java.util.List;


public interface NewsService {

	//添加新闻
	boolean addNews(News news, User user, int addNewsScore);


	//获取全部的新闻列表
	//List<News> getNewsList();

	//获取分页统计信息VO
	NewsPage getNewsPage(int currentPage, int pageSize);


	//下载新闻附件
	void downloadAttachment(User user, int newsid, int downloadscore);


	//获取新闻评论列表
	List<Comment> getCommentList(int newsid);

	//添加新闻评论
	boolean addComment(Comment comment, User user, int addCommentScore);


}
