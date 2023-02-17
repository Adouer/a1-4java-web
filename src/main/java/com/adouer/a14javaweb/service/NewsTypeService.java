package com.adouer.a14javaweb.service;

import com.adouer.a14javaweb.entity.NewsType;

import java.util.List;


public interface NewsTypeService {

	//获取全部的新闻类型列表
	List<NewsType> getNewsTypeList();
}
