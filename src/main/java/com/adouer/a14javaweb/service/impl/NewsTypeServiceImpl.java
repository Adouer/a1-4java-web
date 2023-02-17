package com.adouer.a14javaweb.service.impl;

import com.adouer.a14javaweb.dao.NewsTypeDAO;
import com.adouer.a14javaweb.dao.impl.NewsTypeDAOImpl;
import com.adouer.a14javaweb.entity.NewsType;
import com.adouer.a14javaweb.service.NewsTypeService;

import java.util.List;


public class NewsTypeServiceImpl implements NewsTypeService {

	private NewsTypeDAO newsTypeDAO = new NewsTypeDAOImpl();
	
	@Override
	public List<NewsType> getNewsTypeList() {
		return newsTypeDAO.findNewsTypeList();
	}

}
