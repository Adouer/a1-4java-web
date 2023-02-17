package com.adouer.a14javaweb.dao;

import com.adouer.a14javaweb.entity.NewsType;

import java.util.List;


public interface NewsTypeDAO {

	//获取全部的新闻类型列表
	List<NewsType> findNewsTypeList();
}
