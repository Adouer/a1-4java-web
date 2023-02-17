package com.adouer.a14javaweb.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

	private Integer commentid;
	private String content;
	private Integer score;
	private Date pubtime;

	private News news;   //多对一关联
	private User user;   //多对一关联

	public Integer getCommentid() {
		return commentid;
	}
	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Date getPubtime() {
		return pubtime;
	}
	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


}
