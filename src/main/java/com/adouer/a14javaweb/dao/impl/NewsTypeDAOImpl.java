package com.adouer.a14javaweb.dao.impl;

import com.adouer.a14javaweb.dao.NewsTypeDAO;
import com.adouer.a14javaweb.dbutil.DBManager;
import com.adouer.a14javaweb.entity.NewsType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class NewsTypeDAOImpl implements NewsTypeDAO {

	private DBManager dbManager = DBManager.getInstance();
	
	@Override
	public List<NewsType> findNewsTypeList() {
		
		String sql = "select * from newstype";
		
		ResultSet rs = dbManager.execQuery(sql);
		
		List<NewsType> list = new ArrayList<>();
		
		try {
			
			while(rs.next()){
				
				NewsType newsType = new NewsType();
				
				newsType.setTypeid(rs.getInt(1));
				newsType.setTypename(rs.getString(2));
				
				list.add(newsType);
			}
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbManager.closeConnection();
		}
		
		return null;
	}

}
