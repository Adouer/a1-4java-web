package com.adouer.a14javaweb.entity;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {

	private Integer newsid;
	private String title;
	private String content;
	private Date pubtime;

	//private Integer typeid;
	private NewsType newsType;   //多对一

	//private Integer userid;
	private User user;   //多对一

	private String attachment;
	private String truename;
	private Integer downloadscore;
	private Integer downloadcount;


	public Integer getNewsid() {
		return newsid;
	}
	public void setNewsid(Integer newsid) {
		this.newsid = newsid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPubtime() {
		return pubtime;
	}
	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}


	public NewsType getNewsType() {
		return newsType;
	}
	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public Integer getDownloadscore() {
		return downloadscore;
	}
	public void setDownloadscore(Integer downloadscore) {
		this.downloadscore = downloadscore;
	}
	public Integer getDownloadcount() {
		return downloadcount;
	}
	public void setDownloadcount(Integer downloadcount) {
		this.downloadcount = downloadcount;
	}


}
