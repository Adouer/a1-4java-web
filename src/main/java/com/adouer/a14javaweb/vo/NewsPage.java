package com.adouer.a14javaweb.vo;


import com.adouer.a14javaweb.entity.News;

public class NewsPage extends BasePage<News>{

	//数字页码的起始序号
	private Integer begin;
	private Integer end;

	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}



}
